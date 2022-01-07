package com.example.diythread.threadpool;

/**
 * @author zhangzhuang
 * @since 2021-03-25
 */
public class Test {


	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			ThreadPoolUtil.execute(() -> {
				System.out.println("do something.........");
			});
		}
		ThreadPoolUtil.shutdown();
	}
}
