package com.example.diyredis.controller;

import com.example.diyredis.util.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author zhangzhuang
 * @since 2021-01-21
 */

@Slf4j
@RequestMapping("/lua")
@RestController
public class LuaController {

	@RequestMapping("del")
	public boolean releaseDistributedLock(@RequestParam("lockKey") String lockKey, @RequestParam("reqId") String reqId) {

		Jedis jedis = JedisUtil.getJedis();
		String script = "if redis.call('get',KEYS[1]) == ARGV[1] " +
				"then return redis.call('del',KEYS[1]) " +
				"else return 0 end";
		int result = (int) jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(reqId));
		if (result == 1) {
			return true;
		}
		return false;
	}



}
