package com.example.sortagorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author zhangzhuang
 * @since 2021-07-05
 */
public class MergeSort {


	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		int[] temp = new int[arr.length];
		sort(arr, 0, arr.length - 1, temp);
	}

	private static void sort(int[] arr, int start, int end, int[] temp) {
		if (start >= end) {
			return;
		}
		int mid = (start + end) / 2;
		sort(arr, start, mid, temp);
		sort(arr, mid + 1, end, temp);
		merge(arr, start, mid, end, temp);
	}

	private static void merge(int[] arr, int start, int mid, int end, int[] temp) {
		int left = start;
		int right = mid + 1;
		int index = start;
		while (left <= mid && right <= end) {
			if (arr[left] < arr[right]) {
				temp[index++] = arr[left++];
			} else {
				temp[index++] = arr[right++];
			}
		}
		while (left <= mid) {
			temp[index++] = arr[left++];
		}
		while (right <= end) {
			temp[index++] = arr[right++];
		}
		for (index = start; index <= end ;index++){
			arr[index] = temp[index];
		}
	}


	public static void main(String[] args) {
		int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
		mergeSort(arr);

		System.out.println(Arrays.toString(arr));

		System.out.println("----"+( 7 / 2));

	}
}
