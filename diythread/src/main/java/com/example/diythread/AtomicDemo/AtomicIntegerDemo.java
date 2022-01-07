package com.example.diythread.AtomicDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangzhuang
 * @since 2021-02-25
 * 通过unsafe类和cas操作实现
 */
public class AtomicIntegerDemo {

	private static AtomicInteger atomicInteger = new AtomicInteger(0);


	private static void incr() {
		atomicInteger.getAndIncrement();
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			new Thread(() -> AtomicIntegerDemo.incr()).start();
		}
		Thread.sleep(5000);
		System.out.println(atomicInteger.get());
	}
}
