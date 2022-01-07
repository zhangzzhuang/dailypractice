package com.example.diyrabbitmq.ttl;

import com.example.diyrabbitmq.util.ResourceUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhuang
 * @since 2020-10-29
 * 消息生产者，通过ttl测试死信队列
 * 是Time To Live的缩写, 也就是生存时间
 * 从消息入队列开始计算, 只要超过了队列的超时时间配置, 那么消息会自动清除
 */
public class TTLProducer {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();

		factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

		Connection conn = factory.newConnection();

		Channel channel = conn.createChannel();

		String msg = "Hello , Rabbit MQ, DLX MSG";

		Map<String, Object> map = new HashMap<>();
		map.put("x-message-ttl", 6000);

		channel.queueDeclare("TEST_TTL_QUEUE", false, false, false, map);

		AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
				.deliveryMode(2)  //2代表持久化消息
				.contentEncoding("UTF-8")
				.expiration("1000") // TTL
				.build();

		channel.basicPublish("", "TEST_TTL_QUEUE", properties, msg.getBytes());

		channel.close();
		conn.close();
	}
}
