package com.example.diythread.synchronizedDemo;

import java.util.Queue;

/**
 * @author zhangzhuang
 * @since 2021-02-23
 */
public class Consumer implements Runnable {

	private Queue<String> msg;

	private int maxSize;


	public Consumer(Queue<String> msg, int maxSize) {
		this.msg = msg;
		this.maxSize = maxSize;
	}


	@Override
	public void run() {
		int i = 0;
		while (true) {
			try {
				i++;
				synchronized (msg){
					while (msg.size() == maxSize) {
						System.out.println("生产者队列满了，先等待");
						msg.wait();//阻塞线程并释放锁
					}
					Thread.sleep(1000);
					System.out.println("生产消息" + i);
					msg.add("生产者生产的消息内容" + i);
					msg.notify();//唤醒阻塞状态下的线程
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
