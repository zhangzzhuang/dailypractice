package com.example.diythread.lock;

/**
 * @author zhangzhuang
 * @since 2021-03-04
 */
public class ClassLock {


	public synchronized static void write() {
		System.out.println("类锁write");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void write(String s) {
		synchronized (ClassLock.class){
			System.out.println("类锁");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
