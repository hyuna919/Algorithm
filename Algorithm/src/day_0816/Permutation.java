package day_0816;

import java.util.Arrays;

public class Permutation {
	static int N, R, flag;
	static int[] in, out;
	
	public static void main(String[] args) {
		N = 5;
		R = 5;
		
		in = new int[] {1,2,3,4,5};
		out = new int[R];
		
		perm(0,0);

	}

	private static void perm(int now, int flag) {
		if(now==R) {
			System.out.println(Arrays.toString(out));
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if((flag&1<<i)==0) {
				out[now] = in[i];
				perm(now+1, flag|1<<i);
			}
		}
		
		
	}

}
