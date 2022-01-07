package com.example.jdk;

/**
 * @author zhangzhuang
 * @since 2021-09-28
 */
public class DeadLock {

	public static void main(String[] args) {

		final Object a = new Object();
		final Object b = new Object();

		new Thread(() -> {
			synchronized (a) {
				System.out.println("thread0 get lock a ");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread0 to get lock b");
				synchronized (b) {
					System.out.println("thread0 get lock b ");
				}
			}
		}, "thread0").start();

		new Thread(() -> {
			synchronized (b) {
				System.out.println("thread1 get lock b ");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread1 to get lock a");

				synchronized (a) {
					System.out.println("thread1 get lock a ");
				}
			}
		}, "thread1").start();
	}
}
