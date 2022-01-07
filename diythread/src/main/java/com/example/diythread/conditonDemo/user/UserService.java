package com.example.diythread.conditonDemo.user;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author zhangzhuang
 * @since 2021-02-24
 * 通过Condition和ReentrantLock实现
 */
public class UserService {


	private final ExecutorService executorService = Executors.newCachedThreadPool();

	ArrayBlockingQueue<User> arrayBlockingQueue = new ArrayBlockingQueue(10);

	volatile boolean isFinish = false;


	{
		init();
	}


	public void init() {

		executorService.execute(() -> {
			while (!isFinish) {
				// TODO: 2021/2/24 消费者消费
				try {
					User user = arrayBlockingQueue.take();
					System.out.println("堵这了吗");
					System.out.println("发送积分给:" + user);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
	}


	public boolean register() {
		User u = new User("zhangsan");
		addUser(u);
		//发送积分
		// TODO: 2021/2/24 生产者生产
		try {
			arrayBlockingQueue.put(u);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	private void addUser(User user) {
		System.out.println("添加用户:" + user);
	}

	private void shutdown() {
		isFinish = true;
		System.out.println("shutdown");
	}

	public static void main(String[] args) {
		UserService service = new UserService();
		service.register();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		service.shutdown();

		service.register();

		service.register();

		service.register();
	}

}
