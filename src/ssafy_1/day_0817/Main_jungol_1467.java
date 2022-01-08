package day_0817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_jungol_1467 {
	static int N, A, B;
	static long sum, gap1, gap2, sol, res;
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());		
		
		// 입력
		token = new StringTokenizer(in.readLine());
		A = Integer.parseInt(token.nextToken());
		B = Integer.parseInt(token.nextToken());
		
		sum = Long.parseLong(in.readLine());
		gap1 = Math.abs(A-B);
		
		sol = 0;
		for (int i = 1; i <= N; i++) {
			sol += (long)A*i;
		}
		
		gap2 = Math.abs((long)sol - sum);
		
		res = (((long)gap2/gap1)==0)?-1:(long)gap2/gap1;
		
		System.out.println(res);
		
	}
}
