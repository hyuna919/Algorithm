package day_0810;

import java.util.Arrays;

public class Combination {
	static int n, r;
	static int [] numbers, inputs = {1,2,3,4,5};
	public static void main(String[] args) {
		n = 5;
		r=3;
		numbers = new int[r];
		comb(n,r);
	}
	
	public static void comb(int n, int r) {
		if(r==0) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		if(n<r) return;
		
		numbers[r-1] = inputs[n-1];
		
		comb(n-1, r-1);
		comb(n-1,r);
	}

}
