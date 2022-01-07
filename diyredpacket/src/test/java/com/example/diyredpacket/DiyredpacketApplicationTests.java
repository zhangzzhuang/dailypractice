package com.example.diyredpacket;

import com.alibaba.druid.filter.config.ConfigTools;
import com.example.diyredpacket.service.GrabRedPacket;


import com.example.diyredpacket.service.GrabRedPacketLUA;
import com.example.diyredpacket.util.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;


import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiyredpacketApplication.class)
public class DiyredpacketApplicationTests {

	@Autowired
	GrabRedPacket grabRedPacket;

	@Test
	public void contextLoads() {
	}



	@Test
	public void druidDecrypt() {
		System.out.println("测试开始-----------");

		String pwd = "123456";
		try {
			ConfigTools.main(new String[]{pwd});
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("测试结束-----------");

//		测试开始-----------
//				privateKey:MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAx8M/+y6mNP2kIkUMhFDEbw5RPLd/HA77b1GBNdtn2El7wbPoKQa11kjSQvmJB38+RNbGhWgPjlTNTCj2wl7MyQIDAQABAkEAvBg4DX6eV7UgsHgN0iTu4Zc8nQw27qeYzveSz7B5NmhHrfkMtnfxQbQl3M5i5UxeFtjxx3rypTJ7WE+wFOvYhQIhAPRf9SmYYtnF/nxHfaov+Xo2uzW3sJgMTLFS3hFUeur/AiEA0UP/TxFS7XfTuUGYwOuHl/4ysfG38HHLPXx8FViZsDcCIQDjnw3ItLw+hIHKWPzgLNd/0sMGAhdnSO2Wrc1S7xGfGQIgQeb3HVBoXE6TR9FHqOHDxvMPxghIlBlSFx//97z6UYsCIGQBlQB6z3m2ViB+/ygG6dPwk6bqbLOtY0yQjFVPU/5H
//		publicKey:MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMfDP/supjT9pCJFDIRQxG8OUTy3fxwO+29RgTXbZ9hJe8Gz6CkGtdZI0kL5iQd/PkTWxoVoD45UzUwo9sJezMkCAwEAAQ==
//				password:dXf3OvSljIolWdIcReMEgZIN/gNW2J05QdJM6IwmKoUpGPliVq+bG9XLw3IVaUCoTbwVc4hi5VThbMgKjoi9Pw==
//				测试结束-----------
	}

}
