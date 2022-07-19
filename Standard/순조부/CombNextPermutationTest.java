package 순조부;

import java.util.Arrays;

public class CombNextPermutationTest {
	static int N=3, R=3;
	static int[] numbers;
	static boolean[] isSelected;
	
	 public static void main(String[] args) {
		
		 int[] input = {7,1,4,2,3};
		 int N = input.length;
		 int R = 3;
		 
		 int [] p = new int[N];
		 
		 // 뒤쪽부터 R개만큼 1 채우기
		 int cnt = 0;
		 while(++cnt<=R) p[N-cnt] =1;
		 
		 // 순열 사용
		 do {
			 for (int i = 0; i < N; i++) {
				if(p[i]==1) System.out.print(input[i]+" ");
			}
			 System.out.println();
		 }while(np(p));
	}
	 
	 // 다음 큰 순열이 있으면 true 없으면 false
	 private static boolean np(int[] nums) { 
		 
		 int N = nums.length;
		 
		 // 1. 꼭대기i를 찾는다. 꼭대기를 통해 교환위치i-1 찾기
		 int i =N-1;
		 while(i>0 && nums[i-1]>=nums[i]) --i;
		 	 
		 if(i==0) return false;	// 꼭대기 찾아서 반복 벗어난 후
		 
		 // 2. i-1과 교환할 최대값 찾기...뒤에서 부터 순회
		 int j = N-1;
		 while(nums[i-1]>=nums[j]) --j;
		 
		 // 3. 위치 교환
		 swap(nums, i-1,j);
		 
		 // 4. 꼭대기i부터 끝까지가 현재 내림차순으로 되어있어서 이걸 오름차순으로 바꿔준다
		 int k = N-1;
		 while(i<k) {
			 swap(nums,i++,k--);
		 }
		 
		 return true;
	}
	 
	 
	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	 
}
