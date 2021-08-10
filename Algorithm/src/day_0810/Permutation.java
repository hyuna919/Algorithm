package day_0810;

import java.util.Arrays;

public class Permutation {
	static int n, r, cnt;
	static int [] numbers, inputs;
	
	public static void main(String[] args) {
		n=3;
		r=3;
		inputs = new int[]{1,2,3};
		numbers = new int[n];
		
		permutation(0,0);
	}
	
	public static void permutation(int cnt, int flag) {
		if(cnt==r) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for (int i = 1;i<=n; i++) {
			if((flag & 1<<i)==0) {
				numbers[cnt] = inputs[i-1];
				permutation(cnt+1, (flag|1<<i));
			}
		}
	}
}
