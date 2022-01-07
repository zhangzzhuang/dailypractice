package com.example.diythread.jdk8asyn;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangzhuang
 * @since 2021-12-20
 */
public class CompleteExample {

	static class ClientThread implements Runnable{

		private CompletableFuture completableFuture;

		public ClientThread(CompletableFuture completableFuture){
			this.completableFuture = completableFuture;
		}

		@Override
		public void run() {

			try {
				System.out.println(Thread.currentThread().getName()+":"+completableFuture.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		CompletableFuture cf = new CompletableFuture<>();

		new Thread(new ClientThread(cf),"t1").start();

		new Thread(new ClientThread(cf),"t2").start();

		cf.complete("finish");

	}
}
