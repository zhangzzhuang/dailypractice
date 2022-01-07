package com.example.sortagorithm.biosocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangzhuang
 * @since 2021-09-03
 */
public class BIOServerSocket {

	static ExecutorService executorService = Executors.newFixedThreadPool(10);


	public static void main(String[] args) {

		ServerSocket server = null;
		try {
			server = new ServerSocket(8080);
			System.out.println("启动serverSocket，开始监听8080端口");
			while (true) {
				//阻塞等待一个客户端连接，返回的是客户端的连接信息
				Socket accept = server.accept();
				System.out.println("客户端口:" + accept.getInetAddress() + accept.getPort());

				//异步io
				executorService.submit(new SocketThread(accept));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
