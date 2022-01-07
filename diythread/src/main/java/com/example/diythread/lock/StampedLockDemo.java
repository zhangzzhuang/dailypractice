package com.example.diythread.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author zhangzhuang
 * @since 2021-03-04
 */
public class StampedLockDemo {

	private final StampedLock stampedLock = new StampedLock();

	private double x;
	private double y;

	public void move(double deltaX, double deltaY) {
		long stamped = stampedLock.writeLock(); //获取写锁
		try {
			x += deltaX;
			y += deltaY;
		} finally {
			stampedLock.unlockWrite(stamped);
		}
	}

	public double distanceFromOrigin() {
		long stamp = stampedLock.tryOptimisticRead();//获得一个乐观的读锁
		// 注意下面两行代码不是原子操作
		// 假设x,y = (100,200)
		double currentX = x;
		// 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
		double currentY = y;
		// 此处已读取到y，如果没有写入，读取是正确的(100,200)
		// 如果有写入，读取是错误的(100,400)
		if (!stampedLock.validate(stamp)) { // 通过返回版本号,检查乐观读写锁发生重新赋锁后是否有其他写锁发生,有值
			stamp = stampedLock.readLock();
			try {
				currentX = x;
				currentY = y;
			} finally {
				stampedLock.unlockRead(stamp);
			}
		}

		return currentX + currentY;
	}

	public static void main(String[] args) throws InterruptedException {
		StampedLockDemo point = new StampedLockDemo();

		for (int i = 0; i < 100; i++) {
			new Thread(() -> System.out.println(Thread.currentThread().getName() +"-"+ point.distanceFromOrigin())).start();
			Thread.sleep(10);
		}

		System.out.println("move start");
		new Thread(() -> point.move(10000, 300), "move").start();
	}
}
