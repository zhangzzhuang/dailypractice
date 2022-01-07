package com.example.sortagorithm.daily;

/**
 * @author zhangzhuang
 * @since 2021-07-12
 */
public class Solution {

	public static boolean findNumberIn2DArray(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return false;
		}
		int rows = matrix.length;
		int columns = matrix[0].length;
		for (int row = 0; row < rows; row++) {
			if (matrix[row][0] > target) {
				continue;
			}
			int start = 0;
			int end = columns - 1;
			while (start <= end) {
				int mid = start + (end - start) / 2;
				if (matrix[row][mid] == target) {
					return true;
				} else if (matrix[row][mid] < target) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}
		}
		return false;
	}


	public static void main(String[] args) {

//		//二分查找
//		int[][] arr = new int[][]{{-5}};
//		System.out.println(findNumberIn2DArray(arr, -5));
//		int[] arr = {8,10,3,20,12,4,10,8,4,0,5,17,7,20,3};
//		exchange(arr);
		int[] nums = {5,7,7,8,8,10};
		System.out.println(search(nums,8));
	}

	public static int search(int[] nums, int target) {
		//搜索右边界right
		int i = 0 , j = nums.length - 1;
		while(i <= j){
			int m = i + (j - i) / 2;
			if(nums[m] == target){
				i = m + 1;
			} else if(nums[m] < target){
				i = m + 1;
			} else if(nums[m] > target){
				j = m - 1;
			}
		}

		int right = i;
		//若数组中没有target ,则提前返回
		if( j >= 0 && nums[j] != target) return 0;
		//搜索左边界right
		i = 0; j = nums.length - 1;
		while(i <= j){
			int m = i + ( j -i ) / 2;
			if(nums[m] < target){
				i = m + 1;
			} else if(nums[m] == target){
				j = m - 1;
			} else if(nums[m] > target){
				j = m - 1;
			}
		}
		int left = j;
		return right - left - 1;
	}


	public static int[] exchange(int[] nums) {
		if (nums == null || nums.length == 0) {
			return nums;
		}
		int length = nums.length;
		int right = length - 1;
		for (int i = 0; i < length && i < right; i++) {
			if (nums[i] % 2 == 0) {
				int temp = nums[i];
				while (nums[right] % 2 == 0 && right > i) {
					right--;
				}
				nums[i] = nums[right];
				nums[right] = temp;
			}
		}
		return nums;
	}

	public static int[] printNumbers(int n) {

		if (n < 1){
			return new int[0];
		}
		int length = (int) Math.pow(10,n) - 1;

		int[] arr = new int[length];
		for (int i = 0 ; i < length ; i++){
			arr[i] = i + 1;
		}

		return arr;
	}
}
