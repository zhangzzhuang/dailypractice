package com.example.diyrabbitmq.simple;

import com.example.diyrabbitmq.util.RabbitMQUtil;
import com.example.diyrabbitmq.util.ResourceUtil;
import com.rabbitmq.client.Channel;


import java.time.LocalDateTime;



/**
 * @author zhangzhuang
 * @since 2020-10-28
 */
public class MyProducer {

	private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

	public static void main(String[] args) throws Exception {

		RabbitMQUtil mq = new RabbitMQUtil(ResourceUtil.getKey("rabbitmq.uri"));

		Channel channel = mq.getChannel();

		// 发送消息
		String msg = "Hello , Rabbit MQ___" + LocalDateTime.now();

		// String exchange, String routingKey, BasicProperties props, byte[] body
		channel.basicPublish(EXCHANGE_NAME,"simple.best",null,msg.getBytes());

		mq.close();
	}
}
