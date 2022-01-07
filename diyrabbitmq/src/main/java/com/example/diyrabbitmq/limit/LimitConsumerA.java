package com.example.diyrabbitmq.limit;

import com.example.diyrabbitmq.util.ResourceUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhuang
 * @since 2020-10-29
 * 消息消费者A，测试消费端限流，先启动
 */
public class LimitConsumerA {

	private final static String QUEUE_NAME = "TEST_LIMIT_QUEUE";

	public static void main(String[] args) throws Exception{

		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

		// 建立连接
		Connection conn = factory.newConnection();
		// 创建消息通道
		final Channel channel = conn.createChannel();

		channel.queueDeclare(QUEUE_NAME,false,false,false,null);
		System.out.println("ConsumerA  Waiting for message....");

		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body,"UTF-8");

				System.out.println("ConsumerA Received message : '" + msg + "'" );
//				long deliveryTag, boolean multiple
				channel.basicAck(envelope.getDeliveryTag(),true);

			}
		};
		channel.basicQos(3);
		channel.basicConsume(QUEUE_NAME,false,consumer);
	}
}
