package com.example.diythread.jdk8asyn;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhuang
 * @since 2021-12-20
 */
public class FutureCallableExample {

	static class CalculationCallable implements Callable<Integer> {

		private int x;
		private int y;

		CalculationCallable(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public Integer call() throws Exception {
			System.out.println("begin call: " + new Date());
			TimeUnit.SECONDS.sleep(5);
			return x + y;
		}
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CalculationCallable calculationCallable = new CalculationCallable(1,2);
		FutureTask<Integer> futureTask = new FutureTask<>(calculationCallable);//本质上还是一个线程
		new Thread(futureTask).start();
		System.out.println("begin execute futuretask: " + new Date());
		Integer rs = futureTask.get();//阻塞主线程
		System.out.println("result: " + rs + " ");
		System.out.println("end execute futuretask: " + new Date());
	}

}
