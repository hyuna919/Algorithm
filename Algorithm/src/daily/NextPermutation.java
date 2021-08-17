package daily;
import java.util.Arrays;

public class NextPermutation {
	static int N=4-1;
	static int[] input;
	
	
	 public static void main(String[] args) {
		 input = new int[] {1,2,3,4};
		 
		 do {
			 System.out.println(Arrays.toString(input));
		 }while(np(input));
	 }
	 
	 // 다음 큰 순열이 있으면 true 없으면 false
	 private static boolean np(int[] nums) {
		 // 꼭대기찾기
		 int i = N;
		 while(i>0 && nums[i-1]>nums[i]) i--;
		 
		 if(i==0) return false;
		 // 뒤에서 바꿀거 찾기
		 int j = N;
		 while(nums[i-1] >= nums[j]) j--;
		 swap(nums,i-1,j);
		 // 뒤에 정렬
		 int k = N;
		 while(i<k) swap(nums,i++,k--);
		 
		 return true;
	}
	 
	 
	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	 
}
