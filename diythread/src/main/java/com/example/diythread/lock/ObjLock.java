package com.example.diythread.lock;

/**
 * @author zhangzhuang
 * @since 2021-03-04
 */
public class ObjLock {

	public synchronized void say() {
		System.out.println("对象锁");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void say(String s) {
		synchronized (this){
			System.out.println("对象锁");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
