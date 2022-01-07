package com.example.diyrabbitmq.dlx;

import com.example.diyrabbitmq.util.ResourceUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhuang
 * @since 2020-10-28
 * 使用延时插件实现的消息投递-生产者
 * 必须要在服务端安装rabbitmq-delayed-message-exchange插件
 * 先启动消费者
 */
public class DelayPluginProducer {

	public static void main(String[] args) throws Exception{

		ConnectionFactory factory = new ConnectionFactory();

		factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

		Connection conn = factory.newConnection();

		Channel channel = conn.createChannel();

		// 延时投递，比如延时1分钟
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND,+10);
		Date delayTime = calendar.getTime();

		// 定时投递，把这个值替换delayTime即可
		// Date exactDelayTime = new Date("2020/11/11,00:00:00");
		// 延迟的间隔时间，目标时刻减去当前时刻
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String msg = "发送时间：" + sf.format(now) + "，投递时间：" + sf.format(delayTime);

		Map<String, Object> headers = new HashMap<>();
		headers.put("x-delay",delayTime.getTime() - now.getTime());

		AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().headers(headers).build();

		// String exchange, String routingKey, BasicProperties props, byte[] body
		channel.basicPublish("DELAY_EXCHANGE","DELAY_KEY",props,msg.getBytes());
		System.out.println("发送时间：" +sf.format(new Date()));
		channel.close();
		conn.close();
	}
}
