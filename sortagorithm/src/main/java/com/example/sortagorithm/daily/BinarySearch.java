package com.example.sortagorithm.daily;

/**
 * @author zhangzhuang
 * @since 2021-07-14
 */
public class BinarySearch {


	public static int left_bound(int[] nums ,int target){
		if (nums == null || nums.length == 0){
			return -1;
		}
		int left = 0;
		int right = nums.length;

		while (left < right){
			int mid = left + (right - left) / 2;
			if (nums[mid] == target){
				right = mid;
			}else if (nums[mid] > target){
				right = mid - 1;
			}else if (nums[mid] < target){
				left = mid + 1;
			}
		}

		return left;

	}

	public static int right_bound(int[] nums ,int target){
		if (nums == null || nums.length == 0){
			return -1;
		}
		int left = 0;
		int right = nums.length;
		while (left < right){
			int mid = left + (right - left) / 2;
			if (nums[mid] == target){
				left = mid + 1;
			}else if (nums[mid] < target){
				left = mid + 1;
			}else if (nums[mid] > target){
				right = mid;
			}
		}
		return left - 1;

	}

	public static void main(String[] args) {
		int[] arr = {1,2,3,3,3,5,7};

		System.out.println(right_bound(arr,2));
	}
}
