package com.example.diythread.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;


/**
 * @author zhangzhuang
 * @since 2021-02-25
 * CountDownLatch是基于aqs的cas操作，state共享变量和LockSupport类实现的
 */
public class CDLDemo {


	public static void main(String[] args) throws InterruptedException {
		CountDownLatch count = new CountDownLatch(3);

		new Thread(()->{
			count.countDown();
		}).start();


		count.await();
	}
}
