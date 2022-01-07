package com.example.diyrabbitmq;

import com.example.diyrabbitmq.util.ResourceUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;



/**
 * @author zhangzhuang
 * @since 2020-10-28
 * 删除交换机和队列
 */
public class DeleteTestAllData {


	public static void main(String[] args) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();

		factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

		Connection conn = factory.newConnection();

		Channel channel = conn.createChannel();

		String[] queueNames = {};

		String[] exchangeNames = {"SIMPLE_EXCHANGE"};

		if (queueNames.length > 0) {
			for (String queue : queueNames) {
				channel.queueDelete(queue);
			}
		}

		if (exchangeNames.length > 0) {
			for (String exchange : exchangeNames) {
				channel.exchangeDelete(exchange);
			}
		}


		channel.close();
		conn.close();


	}
}
