package day_0816;

import java.lang.reflect.Array;
import java.util.Arrays;

public class NextPermutation {
	static int N = 4;
	public static void main(String[] args) {
		int[] input = {1,2,3,4,5};
		Arrays.sort(input);
		
		do {
			System.out.println(Arrays.toString(input));
		}while(np(input));
		
	}
	private static boolean np(int[] nums) {
		
		// 꼭대기 찾기
		int i = N;
		while(i>0 && nums[i-1]>=nums[i]) --i;
		
		// 꼭대기가 없다-> 제일 큰수 됐다.
		if(i==0) return false;
		
		
		// 교환 값
		int j = N;
		while(nums[i-1] >= nums[j]) --j;
		
		swap(nums, i-1, j);
		
		// 정렬
		int k = N;
		while(i<k){
			swap(nums, i++,k--);
		}
		
		return true;
		
	}
	
	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

}
