package com.example.sortagorithm.sort;


/**
 * @author zhangzhuang
 * @since 2020-12-07
 */
public class QuickSort {

	public static void quickSort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	public static void sort(int[] arr, int start, int end) {
		if (start >= end) return;
		int left = start;
		int right = end;
		int pivot = arr[start];
		while (left <= right) {
			while (left <= right && arr[left] < pivot) {
				left++;
			}
			while (left <= right && arr[right] > pivot) {
				right--;
			}
			if (left <= right) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
		}

		sort(arr, start, right);
		sort(arr, left, end);
	}


	public static void main(String[] args) {
		int[] a = {1, 9, 9, 2, 2, 5, 3};
		quickSort(a);

		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}



	}
}
