package com.example.sortagorithm.sort;

import java.util.PrimitiveIterator;

/**
 * @author zhangzhuang
 * @since 2020-12-04
 */
public class InsertAndSelectSort {


	public static void insertSort(int[] arr) {
		if (arr == null || arr.length == 0) return;
		int insertNode;
		int j;
		for (int i = 1; i < arr.length; i++) {
			insertNode = arr[i];
			j = i - 1;
			while (j >= 0 && insertNode < arr[j]) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = insertNode;
		}
	}


	public static void selectSort(int[] arr) {
		//每次只交换一次位置
		if (arr == null || arr.length == 0) return;
		for (int i = 0; i < arr.length; i++) {
			int pos = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[pos] > arr[j]) {
					pos = j;
				}
			}
			if (pos != i) {
				int temp = arr[i];
				arr[i] = arr[pos];
				arr[pos] = temp;
			}
		}
	}





	public static void main(String[] args) {
		int[] a = {1, 9, 4, 2, 2, 5, 3};
		selectSort(a);

		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}


	}
}
