package com.example.diythread.lock;

import java.time.LocalTime;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhangzhuang
 * @since 2021-03-04
 *  * 多线程练习
 *  * 原子性，可见性，有序性
 */
public class Threads {

	private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

	public static int counter = 0;

	public void inc() {
		rwLock.writeLock().lock();
		try {
			counter += 1;
			System.out.println(Thread.currentThread().getName() + ":" + counter);
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	public void getCount() {
		rwLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + ":" + counter);
		} finally {
			rwLock.readLock().unlock();
		}
	}


	private static Integer num = 0;
	public static void main(String[] args) throws InterruptedException {

//		/**
		System.out.println("主线程开始🏊‍");

		Thread thread1 = new Thread(() -> {
			try {
				System.out.println("thread1开始🏊‍");
				Thread.sleep(2000);
				synchronized (Threads.class){
					num++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread1.start();

		Thread thread2 = new Thread(() -> {
			try {
				System.out.println("thread2开始🏊‍");
				Thread.sleep(2000);
				synchronized (Threads.class){
					num++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread2.start();

		Thread thread3 = new Thread(() -> {
			try {
				System.out.println("thread3开始🏊‍");
				Thread.sleep(3000);
				synchronized (Threads.class){
					num++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread3.start();


		for (; ; ) {
			Thread.sleep(200);
			System.out.println(num);
			if (num == 3)
				break;
		}

		System.out.println("主线程开始🏊‍");
//		*/
	}



}

/**
 //让出时间片
 Thread.yield();
 */

/**
 //join方法，主线程将等待调用join方法的线程结束后才能继续执行
 System.out.println("main thread start");
 try {
 Thread thread = new Thread(new myRunnable());
 thread.start();
 thread.join();
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
 System.out.println("main thread end");
 */

/**
 //interrupt方法，中断线程
 try {
 Thread t = new interruptThread();
 t.start();

 Thread.sleep(1000);
 t.interrupt();// 中断t线程
 t.join();// 等待t线程结束
 System.out.println(Thread.currentThread().getName());
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
 */


/**
 //volatile关键字的目的是告诉虚拟机：
 //每次访问变量时，总是获取主内存的最新值；
 //每次修改变量后，立刻回写到主内存。
 //解决了可见性的问题

 try {
 HelloThread h = new HelloThread();
 h.start();
 Thread.sleep(10);
 h.running = false;
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
 */

/**
 //守护线程
 //守护线程是指为其他线程服务的线程。当线程只剩下守护线程的时候,JVM就会退出；补充一点如果还有其他的任意一个线程还在，JVM就不会退出。
 TimerThread timerThread = new TimerThread();
 timerThread.setDaemon(true);
 timerThread.start();
 System.out.println("main: wait 3 sec...");
 try {
 Thread.sleep(3000);
 System.out.println(Thread.currentThread().getName());
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
 System.out.println("main: end.");
 */

//线程同步
//synchronized,通过加锁的方式,建立临界区
//		AddThread add = new AddThread();
//		DecThread dec = new DecThread();
//		add.start();
//		dec.start();
//		try {
//			add.join();
//			dec.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(Counter.count);


/**
 Threads threads = new Threads();
 for (int i = 0; i < 100; i++) {
 //读线程
 if (i == 50){
 Thread.sleep(1000);
 }
 new Thread(() -> threads.getCount()).start();
 }

 new Thread(() -> threads.inc(),"Write1").start();

 new Thread(() -> threads.inc(),"Write2").start();
 */


/**
 Thread thread = new Thread(() -> {
 LockSupport.park();
 System.out.println(Thread.currentThread().getName() + "被唤醒");
 });
 thread.start();
 try {
 Thread.sleep(3000);
 System.out.println(Thread.currentThread().getState()+Thread.currentThread().getName());
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
 LockSupport.unpark(thread);
 System.out.println("end");
 */


class Counter {
	public static final Object lock = new Object();
	public static int count = 0;

}


class AddThread extends Thread {
	public void run() {
		for (int i = 0; i < 10000; i++) {
			synchronized (Counter.lock) {
				Counter.count += 1;
			}
		}
	}
}

class DecThread extends Thread {
	public void run() {
		for (int i = 0; i < 10000; i++) {
			synchronized (Counter.lock) {
				Counter.count -= 1;
			}
		}
	}
}

class TimerThread extends Thread {
	@Override
	public void run() {
		while (true) {
			System.out.println(LocalTime.now());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}

class HelloThread extends Thread {
	public volatile boolean running = true;

	public void run() {
		int n = 0;
		while (running) {
			n++;
			System.out.println(n + " hello!");
		}
		System.out.println("end!");
	}
}

class interruptThread extends Thread {

	@Override
	public void run() {
		int n = 0;
		while (!isInterrupted()) {
			n++;
			System.out.println(n + " hello!");
		}
	}
}

class myThread extends Thread {

	@Override
	public void run() {
		System.out.println("start Thread by start()");
	}
}

class myRunnable implements Runnable {
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			System.out.println("start Thread by runnable");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
