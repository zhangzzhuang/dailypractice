package com.example.diythread.CountDownLatchDemo;

import java.util.concurrent.Semaphore;


/**
 * @author zhangzhuang
 * @since 2021-02-25
 */
public class SemaphoreDemo {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(5);

		for (int i= 0; i < 10; i++) {
			new Car(semaphore,i).start();
		}

	}

	static class Car extends Thread{
		Semaphore semaphore;
		int num;

		public Car(Semaphore semaphore ,int num){
			this.num = num;
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			try {
				semaphore.acquire();//获取令牌，没拿到令牌会阻塞
				System.out.println("第"+ num + "线程占用一个令牌");
				Thread.sleep(2000);
				System.out.println("第"+ num + "线程释放一个令牌");
				System.out.println("");
				semaphore.release();//释放令牌
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
