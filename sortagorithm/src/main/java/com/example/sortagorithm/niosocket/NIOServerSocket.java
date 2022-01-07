package com.example.sortagorithm.niosocket;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhangzhuang
 * @since 2021-09-03
 * channel
 * buffer
 * selector
 */
@EnableConfigurationProperties
public class NIOServerSocket {

	public static void main(String[] args) {

		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			//设置连接非阻塞,在没有accept时，线程也不会阻塞
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.socket().bind(new InetSocketAddress(8080));
			while (true) {
				SocketChannel socketChannel = serverSocketChannel.accept();
				socketChannel.configureBlocking(false);
				System.out.println("在没有accept时，线程也不会阻塞");
				if (socketChannel != null){
					ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
					socketChannel.read(byteBuffer);
					System.out.println(new String(byteBuffer.array()));
					byteBuffer.flip();//反转
					socketChannel.write(byteBuffer);
				} else {
					Thread.sleep(1000);
					System.out.println("连接未就绪");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
