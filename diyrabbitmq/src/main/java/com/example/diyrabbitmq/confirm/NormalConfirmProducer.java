package com.example.diyrabbitmq.confirm;

import com.example.diyrabbitmq.util.ResourceUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author zhangzhuang
 * @since 2020-10-29
 * 普通确认模式
 */
public class NormalConfirmProducer {

	private final static String QUEUE_NAME = "ORIGIN_QUEUE";

	public static void main(String[] args) throws Exception {


		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

		// 建立连接
		Connection conn = factory.newConnection();
		// 创建消息通道
		Channel channel = conn.createChannel();

		String msg = "Hello world, Rabbit MQ ,Normal Confirm";


		// 声明队列（默认交换机AMQP default，Direct）
		// String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		// 开启发送方确认模式
		channel.confirmSelect();

		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

		if (channel.waitForConfirms()) {
			System.out.println("消息发送成功");
		} else {
			System.out.println("消息发送失败");
		}

		channel.close();
		conn.close();
	}
}
