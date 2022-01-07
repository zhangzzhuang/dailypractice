package com.example.diythread.jdk8asyn;

import com.example.diythread.threadpool.ThreadPoolUtil;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author zhangzhuang
 * @since 2021-12-20
 */
public class CompletableFutureExample {

	public static void main(String[] args) throws InterruptedException {

/*
		CompletableFuture cf = CompletableFuture.runAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ": 异步执行一个任务 ！！！");
		});

		System.out.println(cf.get());//阻塞

*/

/*
		CompletableFuture cf1 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return "Hello result";
		});

		System.out.println(cf1.get());

		System.out.println("阻塞了吗");//阻塞了


*/

/*
		CompletableFuture cf2 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return "Hello result";
		});

		System.out.println(cf2.thenAccept(rs -> System.out.println(rs)));

		System.out.println("阻塞了吗");//没有阻塞，没有返回值是因为supplyAsync()使用的默认的ForkJoinPool来创建线程，这样创建的线程都是守护线程。
		当唯一运行的线程都是守护线程时，Java 虚拟机退出，该线程停止执行，所以才没有输出结果
*/

/*
		CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello result";
		}, ThreadPoolUtil.executor).thenAcceptAsync(System.out::println);

		System.out.println("阻塞了吗");//没有阻塞，有返回值

 */

/*
		CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello result";
		});

		System.out.println(cf.thenAccept(System.out::println));

		System.out.println("阻塞了吗");
		//没有阻塞，没有返回值是因为supplyAsync()使用的默认的ForkJoinPool来创建线程，这样创建的线程都是守护线程。
		//当唯一运行的线程都是守护线程时，Java 虚拟机退出，该线程停止执行，所以才没有输出结果
 */

/*
		ExecutorService executor = Executors.newFixedThreadPool(3);

		for (int i = 0; i < 5; i++) {
			CompletableFuture cf = CompletableFuture.supplyAsync(() -> {

				System.out.println(Thread.currentThread().getName());

				System.out.println("start to execute supplyAsync" + now());

				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("end to execute supplyAsync" + now());

				return "Hello result" + now();
			}, executor).thenAcceptAsync((rs) -> {

				System.out.println(Thread.currentThread().getName());

				System.out.println("start to execute thenAccept" + now());

				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("end to execute thenAccept" + now());
				System.out.println(rs + now());
			});

		}

		System.out.println("异步了吗" + now());

 */

/*
		CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureExample::fetchPrice);

		cf.thenAccept((rs) -> {
			System.out.println("price: " + rs);
		});

		cf.exceptionally((e) -> {
			e.printStackTrace();
			return null;
		});

		Thread.sleep(200);

 */

		CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> queryCode("中国石油"));

		CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync(CompletableFutureExample::fetchPrice);

		cfFetch.thenAccept( (rs) -> System.out.println("price: " + rs));

		Thread.sleep(20000);
	}

	static Double fetchPrice() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (Math.random() < 0.3) {
			throw new RuntimeException("fetch price failed!");
		}
		return 5 + Math.random() * 20;
	}

	static String queryCode(String name) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		return "601857";
	}

	static Double fetchPrice(String code) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		return 5 + Math.random() * 20;
	}

	static String now() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
		return "   	" + sf.format(date);

	}
}
