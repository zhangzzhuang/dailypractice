package com.example.diyredpacket.util;

import com.example.diyredpacket.config.JedisSentinelPoolDoubleCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @author zhangzhuang
 * @since 2021-03-15
 */
@Component
public class JedisUtil {

	@Autowired
	RedisProperties redisProperties;


	public Jedis getJedis(){

		JedisSentinelPool pool = JedisSentinelPoolDoubleCheck.getJedisPoolConfig(redisProperties);
		return pool.getResource();
	}

}
