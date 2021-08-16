package day_0816;

import java.util.Arrays;

public class Subset {
	static int N;
	static int[] in, out;
	public static void main(String[] args) {
		N = 5;
		
		in = new int[] {1,2,3,4,5};
		out = new int[N];
		
		for (int i = 0, end = 1<<N; i < end; i++) {
			for (int j = 0; j < N; j++) {
				if((i&1<<j)!=0) out[j] = in[j];
			}
			System.out.println(Arrays.toString(out));
			Arrays.fill(out, 0);
		}

	}

}
