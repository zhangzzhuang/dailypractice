package com.example.sortagorithm.daily;


import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

/**
 * @author zhangzhuang
 * @since 2021-07-08
 */
public class ListUnion {

	public static Set<Integer> union(int[] a, int[] b) {
		Set<Integer> set = new HashSet<>();
		int left = 0;
		int right = 0;

		while (left < a.length && right < b.length) {
			if (a[left] < b[right]) {
				set.add(a[left]);
				left++;
			} else if (a[left] == b[right]) {
				set.add(a[left]);
				left++;
				right++;
			} else {
				set.add(b[right]);
				right++;
			}
		}
		while (left < a.length) {
			set.add(a[left]);
			left++;
		}
		while (right < b.length) {
			set.add(b[right]);
			right++;
		}
		return set;
	}



	public static void main(String[] args) {
//		int[] a = {2, 1, 3};
//		int[] b = {2, 3, 4};
//
//		System.out.println(union(a, b));

		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		Random random = new Random(10);
		for (int i = 0; i < 25; i++) {
			int temp = random.nextInt(10);
			minHeap.offer(temp);
		}

		for (int i = 0; i < 5; i++) {
			System.out.println(minHeap.poll());
		}

	}


}
