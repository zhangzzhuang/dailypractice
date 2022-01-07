package com.example.diythread.PriorityQueueDemo;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * @author zhangzhuang
 * @since 2021-04-06
 */
public class PriorityQueueTest {

	public static void main(String[] args) {
		Queue<Integer> integerQueue = new PriorityQueue<>(7);
		Random random = new Random();
		for (int i = 0; i < 7; i++) {
			integerQueue.add(random.nextInt(100));
		}
		for (int i = 0; i < 7; i++) {
			Integer in = integerQueue.poll();
			System.out.println("Processing Integer:"+in);
		}
		System.out.println("");
		Queue<Customer> customerQueue = new PriorityQueue<>(7,idComparator);
		addDataToQueue(customerQueue);
		pollDataFromQueue(customerQueue);


	}

	public static Comparator<Customer> idComparator = new Comparator<Customer>() {
		@Override
		public int compare(Customer o1, Customer o2) {
			return o2.getId() - o1.getId();
		}
	};

	private static void addDataToQueue(Queue<Customer> queue){
		Random random = new Random();
		for (int i = 0; i < 7; i++) {
			int id = random.nextInt(100);
			queue.add(new Customer(id,"Priority"+id));
		}
	}

	private static void pollDataFromQueue(Queue<Customer> queue){
		while (true){
			Customer customer = queue.poll();
			if (customer == null)break;
			System.out.println("Processing Customer with ID=" + customer.getId());
		}
	}
}
