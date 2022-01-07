package com.example.diythread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangzhuang
 * @since 2021-03-25
 */
public class MyThreadFactory implements ThreadFactory {

	private final ThreadGroup group;
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;

	public MyThreadFactory(String whatFeatureOfGroup) {
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		this.namePrefix = "From MyThreadFactory's " + whatFeatureOfGroup + "-thread-";
	}

	@Override
	public Thread newThread(Runnable r) {

		String name = namePrefix + threadNumber.getAndIncrement();
		Thread t = new Thread(group, r, name, 0);
		//守护线程
		//守护线程是指为其他线程服务的线程。当线程只剩下守护线程的时候,JVM就会退出；补充一点如果还有其他的任意一个线程还在，JVM就不会退出
		if (t.isDaemon())
			t.setDaemon(true);
		//线程优先级
		if (t.getPriority() != Thread.NORM_PRIORITY)
			t.setPriority(Thread.NORM_PRIORITY);
		/**
		 * 处理未捕捉的异常
		 */
		t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("do something to handle uncaughtException");
			}
		});
		System.out.println(t.getName());

		return t;
	}

}
