package com.example.jdk;

/**
 * @author zhangzhuang
 * @since 2021-09-28
 *
 * jps
 * jstack pid
 */
public class Threadjstack {


	public static void main(String[] args) throws InterruptedException {

		Thread t = new Thread(()->{
			System.out.println("start");
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"thread0");
		t.start();
		t.join();
		System.out.println(Thread.currentThread().getName()+"end");
	}
}
