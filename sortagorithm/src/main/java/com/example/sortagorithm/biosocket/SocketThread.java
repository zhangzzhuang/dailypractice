package com.example.sortagorithm.biosocket;

import java.io.*;
import java.net.Socket;

/**
 * @author zhangzhuang
 * @since 2021-09-03
 */
public class SocketThread implements Runnable {

	private Socket socket;

	public SocketThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			//input也是阻塞的
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str = bufferedReader.readLine();
			System.out.println("收到客户端消息:" + str);

			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bufferedWriter.write("服务端已收到\n");
			bufferedWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
