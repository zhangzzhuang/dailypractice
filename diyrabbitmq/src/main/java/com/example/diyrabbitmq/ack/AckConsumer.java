package com.example.diyrabbitmq.ack;

import com.example.diyrabbitmq.util.ResourceUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author zhangzhuang
 * @since 2020-10-29
 */
public class AckConsumer {

	private final static String QUEUE_NAME = "TEST_ACK_QUEUE";

	public static void main(String[] args) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();

		factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

		Connection conn = factory.newConnection();

		final Channel channel = conn.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		System.out.println(" Waiting for message....");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "UTF-8");

				if (msg.contains("1")) {
					//拒绝消息 long deliveryTag, boolean requeue
					// requeue：是否重新入队列，true：是；false：直接丢弃，相当于告诉队列可以直接删除掉
					// TODO 如果只有这一个消费者，requeue 为true 的时候会造成消息重复消费
					channel.basicReject(envelope.getDeliveryTag(), false);
				} else if (msg.contains("2")) {
					// 批量拒绝 long deliveryTag, boolean multiple, boolean requeue
					// requeue：是否重新入队列
					// TODO 如果只有这一个消费者，requeue 为true 的时候会造成消息重复消费
					channel.basicNack(envelope.getDeliveryTag(), true, false);
				} else {
					// 手工应答
					// 如果不应答，队列中的消息会一直存在，重新连接的时候会重复消费
					channel.basicAck(envelope.getDeliveryTag(), true);
					System.out.println("Received message : " + msg + "deliveryTag : " + envelope.getDeliveryTag());
				}

			}
		};
		// 开始获取消息，注意这里开启了手工应答
		channel.basicConsume(QUEUE_NAME, false, consumer);
	}
}
