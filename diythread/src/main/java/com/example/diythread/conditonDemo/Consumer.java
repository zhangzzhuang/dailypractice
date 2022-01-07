package com.example.diythread.conditonDemo;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zhangzhuang
 * @since 2021-02-23
 * Condition的await和signal基于LockSupport实现，LockSupport的park和unpark使用到了unsafe类
 */
public class Consumer implements Runnable {

	private Queue<String> msg;

	private int maxSize;


	Lock lock;

	Condition condition;

	public Consumer(Queue<String> msg, int maxSize, Lock lock, Condition condition) {
		this.msg = msg;
		this.maxSize = maxSize;
		this.lock = lock;
		this.condition = condition;
	}


	@Override
	public void run() {
		int i = 0;
		while (true) {
			try {
				lock.lock();
				i++;

				while (msg.size() == maxSize) {
					System.out.println("生产者队列满了，先等待");
					condition.await();//阻塞线程并释放锁
				}
				Thread.sleep(1000);
				System.out.println("生产消息" + i);
				msg.add("生产者生产的消息内容" + i);
				condition.signal();//唤醒阻塞状态下的线程
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
