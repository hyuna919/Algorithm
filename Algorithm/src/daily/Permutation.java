package daily;

import java.util.Arrays;

public class Permutation {
	static int n=3, r=3;
	static int [] inputs , nums;
	public static void main(String[] args) {
		inputs = new int[] {1,3,5};
		nums = new int[r];
		
		permutation(0,0);
		
	}
	
	public static void permutation(int cnt, int flag) {
		if(cnt==r) {
			System.out.println(Arrays.toString(nums));
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if((flag& 1<<i)!=0) continue;
			nums[cnt] = inputs[i];
			permutation(cnt+1, flag|1<<i);
		}
	}
}
