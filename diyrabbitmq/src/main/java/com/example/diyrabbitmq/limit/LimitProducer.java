package com.example.diyrabbitmq.limit;

import com.example.diyrabbitmq.util.ResourceUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Queue;

/**
 * @author zhangzhuang
 * @since 2020-10-29
 * 用于测试消费者限流
 */
public class LimitProducer {

	private final static String QUEUE_NAME = "TEST_LIMIT_QUEUE";

	public static void main(String[] args) throws Exception {


		ConnectionFactory factory = new ConnectionFactory();

		factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

		Connection conn = factory.newConnection();

		Channel channel = conn.createChannel();

		String msg = "a limit message ";

		for (int i = 0; i < 10000; i++) {
			channel.basicPublish("", QUEUE_NAME,null ,(msg + i).getBytes());

		}

		channel.close();
		conn.close();
	}

}
