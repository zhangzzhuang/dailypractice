package com.example.diythread.synchronizedDemo;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zhangzhuang
 * @since 2021-02-23
 */
public class Producer implements Runnable {


	private Queue<String> msg;

	private int maxSize;


	public Producer(Queue<String> msg, int maxSize) {
		this.msg = msg;
		this.maxSize = maxSize;

	}

	@Override
	public void run() {
		while (true) {
			try {
				synchronized (msg){
					while (msg.isEmpty()) {
						System.out.println("消费者队列空了，先等待");
						msg.wait();//阻塞线程并释放锁
					}
					Thread.sleep(1000);
					System.out.println("消费消息" + msg.remove());
					msg.notify();//唤醒阻塞状态下的线程
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
