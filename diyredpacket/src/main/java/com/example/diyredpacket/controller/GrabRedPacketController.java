package com.example.diyredpacket.controller;

import com.example.diyredpacket.dto.RedisDTO;
import com.example.diyredpacket.service.GrabRedPacket;
import com.example.diyredpacket.service.GrabRedPacketLUA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author zhangzhuang
 * @since 2021-03-06
 */
@RestController
public class GrabRedPacketController {

	@Autowired
	GrabRedPacketLUA grabRedPacketLUA;
	@Autowired
	GrabRedPacket grabRedPacket;

	@RequestMapping(value = "/grabRedPacketByRedis")
	public Map<String, Object> grabRedPacketByRedis(@RequestParam("redPacketId")Long redPacketId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		long userId = new Random().nextLong();
		Long result = grabRedPacketLUA.grabRedPacketByRedis(redPacketId, userId);
		boolean flag = result > 0;
		resultMap.put("result", flag);
		resultMap.put("message", flag ? "抢红包成功" : "抢红包失败");
		return resultMap;
	}

	@RequestMapping(value = "/grabRedPacket")
	public Map<String, Object> grabRedPacket(@RequestParam("redPacketId") Long redPacketId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		long userId = new Random().nextLong();
		String result = grabRedPacket.grabRedPacketForVersion(redPacketId, userId);
		resultMap.put("result", result);

		return resultMap;
	}

	@RequestMapping(value = "/setFSTRedis")
	public void testSFSTRedis(@RequestBody @Valid RedisDTO dto) {
		grabRedPacketLUA.setFSTRedis(dto);
	}

	@RequestMapping(value = "/getFSTRedis")
	public Object testGFSTRedis(@RequestParam("key") String key) {
		return grabRedPacketLUA.getFSTRedis(key);
	}

	@RequestMapping(value = "/testJedis")
	public void testJedis() {

		grabRedPacketLUA.testJedis();
	}
}
