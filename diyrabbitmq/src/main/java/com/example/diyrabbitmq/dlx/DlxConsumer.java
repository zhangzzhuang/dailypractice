package com.example.diyrabbitmq.dlx;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhuang
 * @since 2020-10-28
 * 消息消费者，由于执行消费的代码注释掉了，
 * 10秒钟后，消息会从正常队列 ORI_USE_QUEUE 到达死信交换机 DEAD_LETTER_EXCHANGE ，然后路由到死信队列 DEAD_LETTER_QUEUE
 */
public class DlxConsumer {

	public static void main(String[] args)  throws Exception{
		ConnectionFactory factory = new ConnectionFactory();

		Connection conn = factory.newConnection();

		Channel channel = conn.createChannel();

		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange","DEAD_LETTER_EXCHANGE");

		// 声明队列（默认交换机AMQP default，Direct）
		// String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
		channel.queueDeclare("ORI_USE_QUEUE",true,false,false,arguments);

		//声明死信交换机
		// String exchange, String type, boolean durable, boolean autoDelete, Map<String, Object> arguments
		channel.exchangeDeclare("DEAD_LETTER_EXCHANGE","topic",true,false,null);

		//声明死信队列
		channel.queueDeclare("DEAD_LETTER_QUEUE",true,false,false,null);

		// 绑定，此处 Dead letter routing key 设置为 #
		channel.queueBind("DEAD_LETTER_QUEUE","DEAD_LETTER_EXCHANGE","#");

		System.out.println(" Waiting for message....");

		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body,"UTF-8");
				System.out.println("Received message : '" + msg + "'");
			}
		};

		// 开始获取消息
		// String queue, boolean autoAck, Consumer callback
//		channel.basicConsume("DEAD_LETTER_QUEUE",true,consumer);
	}


}
