package com.example.diyredis.controller;

import com.example.diyredis.util.LettuceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangzhuang
 * @since 2021-01-20
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

	private static int ExpireTime = 60;

	@Resource
	private LettuceUtil lettuceUtil;


	@RequestMapping("set")
	public boolean set(@RequestParam("key") String key, @RequestParam("value") String value){

		lettuceUtil.hset("red_packet_5","stock",20000);
		lettuceUtil.hset("red_packet_5","unit_amount",10);
		return true;

	}

	@RequestMapping("get")
	public Object get(@RequestParam("key") String key){

		return lettuceUtil.get(key);
	}

	@RequestMapping("expire")
	public boolean expire(@RequestParam("key") String key){

		return lettuceUtil.expire(key,ExpireTime);
	}
}
