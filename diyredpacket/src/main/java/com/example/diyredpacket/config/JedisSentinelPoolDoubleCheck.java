package com.example.diyredpacket.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhangzhuang
 * @since 2021-03-15
 * 双锁机制，安全且在多线程情况下能保持高性能
 */
public class JedisSentinelPoolDoubleCheck {


	private static volatile JedisSentinelPool jedisSentinelPool = null;

	private JedisSentinelPoolDoubleCheck() {}

	public static JedisSentinelPool getJedisPoolConfig(RedisProperties redisProperties) {
		if (null == jedisSentinelPool) {
			synchronized (JedisSentinelPoolDoubleCheck.class) {
				if (null == jedisSentinelPool) {
					GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
					poolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
					poolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
					poolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
					poolConfig.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
					poolConfig.setTestOnBorrow(true);
					//切割集群节点
					List<String> sNodes = redisProperties.getSentinel().getNodes();
					Set<String> nodes = new HashSet<>();
					sNodes.forEach(address -> nodes.add(address));
					jedisSentinelPool = new JedisSentinelPool(redisProperties.getSentinel().getMaster(), nodes, poolConfig);
				}
			}
		}
		return jedisSentinelPool;
	}

}
