package com.example.diyredpacket.service;

import com.example.diyredpacket.dao.TRedPacketMapper;
import com.example.diyredpacket.dao.TUserRedPacketMapper;
import com.example.diyredpacket.entity.TRedPacket;
import com.example.diyredpacket.entity.TUserRedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author zhangzhuang
 * @since 2020-11-20
 */
@Service
public class GrabRedPacket {

	@Autowired
	TRedPacketMapper tRedPacketMapper;
	@Autowired
	TUserRedPacketMapper tUserRedPacketMapper;

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public String grabRedPacketForVersion(Long redPacketId, Long userId) {
		//获取红包信息
		TRedPacket redPacket = tRedPacketMapper.selectByPrimaryKey(redPacketId);
		if (redPacket != null && redPacket.getStock() > 0) {
			int update = tUserRedPacketMapper.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
			//如果本次没有更新成功，说明其它线程已经修改了version，本次抢红包失败
			if (update == 0) {
				return "抢红包失败";
			}
			TUserRedPacket userRedPacket = new TUserRedPacket();
			userRedPacket.setRedPacketId(redPacketId);
			userRedPacket.setUserId(userId);
			userRedPacket.setAmount(redPacket.getUnitAmount());
			userRedPacket.setNote("抢红包" + redPacketId);
			//插入抢红包信息
			if (tUserRedPacketMapper.insert(userRedPacket) < 0) return "抢红包插入数据失败";
		}
		return "抢红包成功";
	}

	//100mill之内重试
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public String grabRedPacketForVersionRetryInMills(Long redPacketId, Long userId){
		long start = System.currentTimeMillis();
		while (true){
			long end = System.currentTimeMillis();
			if (end -start > 100){
				return "抢红包失败";
			}
			TRedPacket redPacket = tRedPacketMapper.selectByPrimaryKey(redPacketId);
			if (redPacket != null && redPacket.getStock() > 0){
				int update = tUserRedPacketMapper.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
				if (update == 0){
					continue;
				}
				TUserRedPacket userRedPacket = new TUserRedPacket();
				userRedPacket.setRedPacketId(redPacketId);
				userRedPacket.setUserId(userId);
				userRedPacket.setAmount(redPacket.getUnitAmount());
				userRedPacket.setNote("抢红包" + redPacketId);
				//插入抢红包信息
				if (tUserRedPacketMapper.insert(userRedPacket) < 0){
					return "抢红包插入数据失败";
				} else {
					return "抢红包成功";
				}
			} else {
				return "抢红包失败";
			}
		}

	}

	//3次重试机会
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public String grabRedPacketForVersionThereRetry(Long redPacketId, Long userId){
		for (int i = 0 ; i < 3 ; i++){
			TRedPacket redPacket = tRedPacketMapper.selectByPrimaryKey(redPacketId);
			if (redPacket != null && redPacket.getStock() > 0){
				int update = tUserRedPacketMapper.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
				if (update == 0){
					continue;
				}
				TUserRedPacket userRedPacket = new TUserRedPacket();
				userRedPacket.setRedPacketId(redPacketId);
				userRedPacket.setUserId(userId);
				userRedPacket.setAmount(redPacket.getUnitAmount());
				userRedPacket.setNote("抢红包" + redPacketId);
				//插入抢红包信息
				if (tUserRedPacketMapper.insert(userRedPacket) < 0){
					return "抢红包插入数据失败";
				} else {
					return "抢红包成功";
				}
			} else {
				return "抢红包失败";
			}
		}

		return "抢红包失败";
	}




}
