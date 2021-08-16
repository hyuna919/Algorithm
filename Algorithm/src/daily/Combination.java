package daily;

import java.util.Arrays;

public class Combination {

	static int n ,r, nums[], inputs[], cnt;
	public static void main(String[] args) {
		n=5;
		r = 4;
		inputs = new int[]{1,2,3,4,5};
		nums = new int[r];
		
		comb(n,r);
	}
	
	private static void comb(int n, int r) {
		if(r==0) {
			System.out.println(Arrays.toString(nums));
			return;
		}
		
		if(n<r) return;
		
		nums[r-1] = inputs[n-1];
		comb(n-1, r-1);
		comb(n-1, r);
	}

}
