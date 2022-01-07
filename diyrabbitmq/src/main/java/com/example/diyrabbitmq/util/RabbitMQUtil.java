package com.example.diyrabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import java.util.concurrent.TimeoutException;

/**
 * @author zhangzhuang
 * @since 2020-10-29
 */
public class RabbitMQUtil {

	private Channel channel;
	private ConnectionFactory factory;
	private Connection coon;


	public RabbitMQUtil(String uri) {
		try {
			this.factory = new ConnectionFactory();
			this.factory.setUri(uri);
			this.coon = factory.newConnection();
			this.channel = coon.createChannel();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// 关闭连接的方法
	public void close() {
		try {
			if (this.channel != null) {
				this.channel.close();
			}
			if (this.coon != null) {
				this.coon.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	public Channel getChannel() throws IOException {
		if (this.channel == null){
			this.channel = coon.createChannel();
		}
		return this.channel;
	}

}
