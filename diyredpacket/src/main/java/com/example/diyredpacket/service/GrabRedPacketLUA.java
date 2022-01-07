package com.example.diyredpacket.service;

import com.example.diyredpacket.dao.TRedPacketMapper;
import com.example.diyredpacket.dao.TUserRedPacketMapper;
import com.example.diyredpacket.dto.RedisDTO;
import com.example.diyredpacket.entity.TUserRedPacket;
import com.example.diyredpacket.util.JedisUtil;
import com.example.diyredpacket.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;


import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzhuang
 * @since 2021-03-06
 */

@Service
public class GrabRedPacketLUA {

	@Autowired
	TRedPacketMapper tRedPacketMapper;
	@Autowired
	TUserRedPacketMapper tUserRedPacketMapper;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	JedisUtil jedisUtil;
	private static final String PREFIX = "red_packet_list_";

	// 每次取出1000条，避免一次取出消耗太多内存
	private static final int TIME_SIZE = 1000;

	@Autowired
	private DataSource datasource = null; // 数据源


	String script = "local listKey = 'red_packet_list_'..KEYS[1] \n"
			+ "local redPacket = 'red_packet_'..KEYS[1] \n"
			+ "local stock = tonumber(redis.call('hget',redPacket,'stock')) \n"
			+ "if stock <= 0 then return 0 end \n"
			+ "stock = stock - 1 \n"
			+ "redis.call('hset',redPacket,'stock',tostring(stock)) \n"
			+ "redis.call('rpush',listKey,ARGV[1]) \n"
			+ "if stock == 0 then return 2 end \n"
			+ "return 1";
	String sha1 = null;


	public Long grabRedPacketByRedis(Long redPacketId, Long userId) {
		// 当前抢红包用户和日期信息
		String args = userId + "_" + System.currentTimeMillis();

		Long result = null;
		// 获取底层Redis操作对象
		Jedis jedis = jedisUtil.getJedis();
		try {
			// 如果脚本没有加载过，那么进行加载，这样就会返回一个sha1编码
			if (sha1 == null) {
				sha1 = jedis.scriptLoad(script);
			}
			// 执行脚本，返回结果
			Object res = jedis.evalsha(sha1, 1, String.valueOf(redPacketId) , args);
			result = (Long) res;
			// 返回2时为最后一个红包，此时将抢红包信息通过异步保存到数据库中
			if (result == 1) {
				// 获取单个小红包金额
				String unitAmountStr = jedis.hget("red_packet_" + redPacketId, "unit_amount");
				// 触发保存数据库操作
				BigDecimal unitAmount = new BigDecimal(unitAmountStr.toString());
				System.err.println("thread_name    =    " + Thread.currentThread().getName());
				this.saveUserRedPacketByRedis(redPacketId, unitAmount);
			}
		} finally {
			if (jedis != null && jedis.isConnected()) {
				jedis.close();
			}
		}
		return result;
	}

	@Async("taskExecutor")
	public void saveUserRedPacketByRedis(Long redPacketId, BigDecimal unitAmount) {
		System.err.println("开始保存数据");
		Long start = System.currentTimeMillis();
		// 获取列表操作对象
		BoundListOperations ops = stringRedisTemplate.boundListOps(PREFIX + redPacketId);
		Long size = ops.size();
		long times = size % TIME_SIZE == 0 ? size / TIME_SIZE : size / TIME_SIZE + 1;
		int count = 0;
		List<TUserRedPacket> userRedPacketList = new ArrayList<TUserRedPacket>(TIME_SIZE);
		for (int i = 0; i < times; i++) {
			// 获取至多TIME_SIZE个抢红包信息
			List<Object> userIdList = null;
			if (i == 0) {
				userIdList = ops.range(i * TIME_SIZE, (i + 1) * TIME_SIZE);
			} else {
				userIdList = ops.range(i * TIME_SIZE, (i + 1) * TIME_SIZE);
			}
			userRedPacketList.clear();
			// 保存红包信息
			for (int j = 0; j < userIdList.size(); j++) {
				String args = userIdList.get(j).toString();
				String[] arr = args.split("_");
				String userIdStr = arr[0];
				String timeStr = arr[1];
				Long userId = Long.parseLong(userIdStr);
				Long time = Long.parseLong(timeStr);
				// 生成抢红包信息
				TUserRedPacket UserRedPacket = new TUserRedPacket();
				UserRedPacket.setRedPacketId(redPacketId);
				UserRedPacket.setUserId(userId);
				UserRedPacket.setAmount(unitAmount);
				UserRedPacket.setGrabTime(new Timestamp(time));
				UserRedPacket.setNote("抢红包 " + redPacketId);
				userRedPacketList.add(UserRedPacket);
			}
			// 插入抢红包信息
			count += executeBatch(userRedPacketList);
		}
		// 删除Redis列表
		stringRedisTemplate.delete(PREFIX + redPacketId);
		Long end = System.currentTimeMillis();
		System.err.println("保存数据结束，耗时" + (end - start) + "毫秒，共" + count + "条记录被保存。");
	}

	/**
	 * 使用JDBC批量处理Redis缓存数据.
	 *
	 * @param userRedPacketList --抢红包列表 @return抢红包插入数量.
	 */
	private int executeBatch(List<TUserRedPacket> userRedPacketList) {
		Connection conn = null;
		Statement stmt = null;
		int[] count = null;
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for (TUserRedPacket userRedPacket : userRedPacketList) {
				String sql1 = "update T_RED_PACKET set stock = stock-1 where id=" + userRedPacket.getRedPacketId() + ";";
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String sql2 = "insert into T_USER_RED_PACKET (red_packet_id,user_id," + "amount, grab_time, note)"
						+ " values (" + userRedPacket.getRedPacketId() + ", " + userRedPacket.getUserId() + ", "
						+ userRedPacket.getAmount() + ", " + "'" + df.format(userRedPacket.getGrabTime()) + "'" + ", '"
						+ userRedPacket.getNote() + "')" + ";";
				stmt.addBatch(sql1);
				stmt.addBatch(sql2);
			}
			// 执行批量
			count = stmt.executeBatch();
			// 提交事务
			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException("抢红包批量执行程序错误");
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 返冋插入抢红包数据记录
		return count.length / 2;
	}

	public String setFSTRedis(RedisDTO dto) {
		redisUtil.set(dto.getKey(), dto.getValue());
		return "success";
	}

	public Object getFSTRedis(String key) {
		return redisUtil.get(key);
	}

	public void testJedis() {

		for (int i = 0; i < 2; i++) {

			int finalI = i;
			new Thread(() -> {
				for (int j = 0; j < 10; j++) {
					stringRedisTemplate.opsForValue().set("a" + finalI, String.valueOf(finalI));
					System.out.println("a" + finalI + " = " + stringRedisTemplate.opsForValue().get("a" + finalI));
				}
			}).start();
		}
	}
}
