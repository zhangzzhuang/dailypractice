package com.example.diythread.conditonDemo;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zhangzhuang
 * @since 2021-02-23
 * Condition的await和signal方法基于aqs的condition单向链表，cas操作和LockSupport类实现的，而LockSupport的park和unpark又是基于unsafe类实现的
 */
public class Producer implements Runnable {


	private Queue<String> msg;

	private int maxSize;


	Lock lock;

	Condition condition;


	public Producer(Queue<String> msg, int maxSize, Lock lock, Condition condition) {
		this.msg = msg;
		this.maxSize = maxSize;
		this.lock = lock;
		this.condition = condition;
	}

	@Override
	public void run() {
		while (true) {
			try {
				lock.lock();
				while (msg.isEmpty()) {
					System.out.println("消费者队列空了，先等待");
					condition.await();//阻塞线程并释放锁
				}
				Thread.sleep(1000);
				System.out.println("消费消息" + msg.remove());
				condition.signal();//唤醒阻塞状态下的线程
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
	}
}
