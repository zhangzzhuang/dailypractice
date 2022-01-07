package com.example.diyrabbitmq.dlx;

import com.example.diyrabbitmq.util.ResourceUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.time.LocalDateTime;

/**
 * @author zhangzhuang
 * @since 2020-10-28
 * 消息生产者，通过ttl（过期时间）测试死信队列
 */
public class DlxProducer {

	public static void main(String[] args) throws Exception{
		ConnectionFactory factory = new ConnectionFactory();

		factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));
		Connection conn = factory.newConnection();

		Channel channel = conn.createChannel();

		String msg = "Hello, Rabbit MQ, DLX MSG_____" + LocalDateTime.now();

		// 对每条消息设置过期时间
		AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
				.deliveryMode(2)
				.contentEncoding("UTF-8")
				.expiration("10000")
				.build();

		// String exchange, String routingKey, BasicProperties props, byte[] body
		channel.basicPublish("","ORI_USE_QUEUE",properties,msg.getBytes());

		channel.close();
		conn.close();
	}
}
