package com.example.diythread.threadpool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author zhangzhuang
 * @since 2021-03-25
 */
public class ThreadPoolUtil {

	static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolUtil.class);

	private static final int DEFAULT_MAX_CONCURRENT = Runtime.getRuntime().availableProcessors() * 2;

	private static final String THREAD_POOL_NAME = "ThreadPoolUtil";


	private static final int DEFAULT_SIZE = 500;

	private static final long DEFAULT_KEEP_ALIVE = 60L;

	private static ExecutorService executor;

	private static final ThreadFactory FACTORY = new MyThreadFactory(THREAD_POOL_NAME);

	private static BlockingQueue<Runnable> executeQueue = new ArrayBlockingQueue<>(DEFAULT_SIZE);

	static {
		try {

			executor = new ThreadPoolExecutor(DEFAULT_MAX_CONCURRENT, DEFAULT_MAX_CONCURRENT + 2, DEFAULT_KEEP_ALIVE,
					TimeUnit.SECONDS, executeQueue, FACTORY);

			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				@Override
				public void run() {
					LOGGER.info("MyThreadPool shutting down.");
					//停止接收外部submit的任务,并继续执行当前任务和队列中的任务，等待所有任务执行完才会关闭执行器
					executor.shutdown();

					try {
						//设定关闭超时时间，设置时间内关闭返回true，反之返回false
						if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
							LOGGER.error("MyThreadPool shutdown immediately due to wait timeout.");
							//忽略队列里等待的任务，关闭执行器
							executor.shutdownNow();
						}
					} catch (InterruptedException e) {
						LOGGER.error("MyThreadPool shutdown interrupted.");
						executor.shutdownNow();
					}

					LOGGER.info("MyThreadPool shutdown complete.");
				}
			}));
		} catch (Exception e) {
			LOGGER.error("MyThreadPool init error.", e);
			throw new ExceptionInInitializerError(e);
		}
	}

	private ThreadPoolUtil() {
	}

	public static boolean execute(Runnable task) {

		try {
			executor.execute(task);
		} catch (RejectedExecutionException e) {
			LOGGER.error("Task executing was rejected.", e);
			return false;
		}

		return true;
	}

	public static <T> Future<T> submitTask(Callable<T> task) {

		try {
			return executor.submit(task);
		} catch (RejectedExecutionException e) {
			LOGGER.error("Task executing was rejected.", e);
			throw new UnsupportedOperationException("Unable to submit the task, rejected.", e);
		}
	}

	public static void shutdown(){
		executor.shutdownNow();

	}



}
