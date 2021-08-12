package daily;

import java.util.Arrays;

public class Combination {

	static int n ,r, numbers[], inputs[], cnt;
	public static void main(String[] args) {
		n=3;
		r=2;
		inputs = new int[] {1,3,5};
		numbers = new int[r];
		
		comb(n,r);
		System.out.println(cnt);
	}
	
	private static void comb(int n, int r) {
		if(r==0) {
			cnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		if(n<r) return;
		
		numbers[r-1] = inputs[n-1];
		comb(n-1,r-1);
		comb(n-1,r);
		
	}

}
