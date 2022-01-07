package com.example.diythread;

import com.example.diythread.synchronizedDemo.Consumer;
import com.example.diythread.synchronizedDemo.Producer;
import com.example.diythread.threadpool.ThreadPoolUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
public class DiythreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiythreadApplication.class, args);

//		Queue<String> queue = new LinkedList<>();
//		Lock lock = new ReentrantLock();//重入锁
//		Condition condition = lock.newCondition();
//		int maxSize = 5;
//
//		Producer producer = new Producer(queue,maxSize,lock,condition);
//		Consumer consumer = new Consumer(queue,maxSize,lock,condition);
//
//		Thread t1 = new Thread(producer);
//		Thread t2 = new Thread(consumer);
//
//		t2.start();
//		t1.start();

		Queue<String> queue = new LinkedList<>();
		int maxSize = 5;

		com.example.diythread.synchronizedDemo.Producer producer = new Producer(queue,maxSize);
		com.example.diythread.synchronizedDemo.Consumer consumer = new Consumer(queue,maxSize);

		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);

		t2.start();
		t1.start();



		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
		try {
			blockingQueue.put("zhangsan");
			blockingQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}



	}

}
