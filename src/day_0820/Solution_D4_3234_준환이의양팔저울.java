package day_0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3234_준환이의양팔저울 {
	static int T, N, cnt;
	static int[] input, nums;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		T = Integer.parseInt(in.readLine())+1;
		
		for (int t = 1; t < T; t++) {
			N = Integer.parseInt(in.readLine());

			input = new int[N];
			nums = new int[N];
			
			token = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(token.nextToken());
			}
			
			cnt = 0;
			perm(0,0);
			builder.append("#"+t + " " + cnt + "\n");
		}
		
		
		System.out.println(builder);
	}
	
	public static void perm(int now, int flag) {
		if(now==N) {
			comb(0,0,0);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if((flag&1<<i)==0) {
				nums[now] = input[i];
				perm(now+1, flag|1<<i);
			}
		}
	}
	
	private static void comb(int n, int wLeft, int wRight) {
		if(wLeft < wRight) return;
		
		if(n==N) {
			cnt++;
			return;
		}

		comb(n+1, wLeft+nums[n], wRight);
		comb(n+1, wLeft, wRight+nums[n]);
	}

}
