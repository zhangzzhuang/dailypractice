package com.example.diyrabbitmq.dlx;

import com.example.diyrabbitmq.util.ResourceUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhuang
 * @since 2020-10-28
 * 使用延时插件实现的消息投递-消费者
 * 必须要在服务端安装rabbitmq-delayed-message-exchange插件
 * 先启动消费者
 */
public class DelayPluginConsumer {

	public static void main(String[] args) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();

		factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

		Connection conn = factory.newConnection();

		Channel channel = conn.createChannel();

		// 声明x-delayed-message类型的exchange
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("x-delayed-type", "direct");

		// 声明交换机
		// String exchange, String type, boolean durable, boolean autoDelete, Map<String, Object> arguments
		channel.exchangeDeclare("DELAY_EXCHANGE", "x-delayed-message", true, false, map);

		// 声明队列
		channel.queueDeclare("DELAY_QUEUE",true,false,false,null);

		// 绑定交换机与队列
		channel.queueBind("DELAY_QUEUE","DELAY_EXCHANGE","DELAY_KEY");

		System.out.println(" Waiting for message....");

		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body,"UTF-8");
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				System.out.println("收到消息：[" + msg + "]\n接收时间：" +sf.format(new Date()));
			}
		};

		channel.basicConsume("DELAY_QUEUE",true,consumer);
	}
}
