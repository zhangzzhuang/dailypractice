package com.example.sortagorithm;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author zhangzhuang
 * @since 2021-08-12
 */
@Component
public class TestDemo implements ApplicationRunner {

	public static void main(String[] args) {


		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("a");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("a5000");
			}
		}, "a");

		Thread b = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					a.join();
					System.out.println("b");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "b");

		Thread c = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					b.join();
					System.out.println("c");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "c");

		b.start();
		c.start();
		a.start();

//
//		String s = "basi";
//		int hash = s.hashCode();
//		int hash16 = hash >>> 16;
//		int key = hash ^ hash16;
//
//		System.out.println(hash);
//		System.out.println(hash16);
//		System.out.println(key);
//
//		System.out.println(hash(s));
//
//
//		System.out.println((16-1) & hash);
//
		int money = 1000;
		int num = 10;

		System.out.println(redPacket(900, 10));

//		Map<String, String> map = new HashMap<>();
//		map.put(null,null);
//		map.put(null,"null;");
//		System.out.println(map.size());
//		System.out.println(map.get(null));
//		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
//		linkedHashMap.put(null,null);
//		System.out.println(linkedHashMap.size());
//
//		TreeMap treeMap = new TreeMap<>();
//		treeMap.put(null,null);
//		System.out.println(treeMap);


	}


	static final int hash(Object key) {
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}


	static int redPacket(int limit, int bound) {
		int res = random.nextInt(limit - bound) + bound;
		return res;
	}

	static Random random = new Random();

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("----ApplicationRunner-----" + args);
	}
}
