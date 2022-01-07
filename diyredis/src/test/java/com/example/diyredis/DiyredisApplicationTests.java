package com.example.diyredis;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;


@SpringBootTest(classes = DiyredisApplication.class)
public class DiyredisApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void jedisTset() {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.set("name0002", "lisi");
	}

	@Test
	public void JsonTest(){

	}

}
