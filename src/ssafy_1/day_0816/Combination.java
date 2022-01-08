package day_0816;

import java.util.Arrays;

public class Combination {
	static int N, R;
	static int [] in, out;
	public static void main(String[] args) {
		N = 5;
		R= 3;
		in = new int[] {1,2,3,4,5};
		out = new int[R];
		
		comb(0,0);
		
		
	}
	private static void comb(int now, int start) {
		if(now==R) {
			System.out.println(Arrays.toString(out));
			return;
		}
		
		if(start>=N) return;
		
		out[now] = in[start];
		comb(now+1, start+1);
		comb(now, start+1);
		
	}

	
}
