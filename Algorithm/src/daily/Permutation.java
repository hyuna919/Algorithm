package daily;

import java.util.Arrays;

public class Permutation {
	static int n=3, r=3;
	static int [] inputs , nums;
	public static void main(String[] args) {
		nums = new int[r];
		inputs = new int[] {1,2,3};
		
		permutation(0,0);
		
		
	}
	
	public static void permutation(int cnt, int flag) {
		if(cnt==r) {
			System.out.println(Arrays.toString(nums));
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if((flag& 1<<i)==0) {
				nums[cnt] = inputs[i];
				permutation(cnt+1, flag|1<<i);
			}
		}
	}
}
