package com.example.diyrabbitmq.limit;

import com.example.diyrabbitmq.util.ResourceUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhuang
 * @since 2020-10-29
 * 消息消费者B，测试消费端限流，先启动
 */
public class LimitConsumerB {

	private final static String QUEUE_NAME = "TEST_LIMIT_QUEUE";

	public static void main(String[] args) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

		// 建立连接
		Connection conn = factory.newConnection();

		// 创建消息通道
		final Channel channel = conn.createChannel();

		// 声明队列（默认交换机AMQP default，Direct）
		// String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		System.out.println("ConsumerB  Waiting for message....");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "UTF-8");
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("ConsumerB Received message : '" + msg + "'");
//				long deliveryTag, boolean multiple
				channel.basicAck(envelope.getDeliveryTag(), true);

			}
		};
		// 非自动确认消息的前提下，如果一定数目的消息（通过基于consume或者channel设置Qos的值）未被确认前，不进行消费新的消息。
		// 因为ConsumerB的处理速率很慢，收到3条消息后都没有发送ACK，队列不会再发送消息给ConsumerB
		channel.basicQos(3);
		channel.basicConsume(QUEUE_NAME, false, consumer);
	}
}
