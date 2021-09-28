package day_0928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 성수의 비밀번호 공격
 * 
 */

public class Solution_swea_D4_6026 {
	static int M,N,cnt;
	static long res,mod=1000000007;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t < T+1; t++) {
			token = new StringTokenizer(in.readLine());
			M = Integer.parseInt(token.nextToken());
			N = Integer.parseInt(token.nextToken());
			
			// 식
			res = (long) (Math.pow(M,N)%mod);
			for (int x = M-1; x > 0; x--) {
				cnt = 0;
				comb(M,x);
				res -= (long)(cnt*Math.pow(x, N)) %mod;
				if(--x>0) {
					cnt = 0;
					comb(M,x);
					res += (long)(cnt%mod*power(x, N)%mod) %mod;
				}
			}
			
			builder.append("#"+t+" "+res+"\n");
		}
		System.out.println(builder);
	}
	private static long power(long x, long y) {
		long res = 1;
		x = x%mod;
		while(y>0) {
			if(y%2==1) res = (res*x) % mod;
			y = y>>1;
			x=(x*x)%mod;
		}
		return res;
		
	}
	private static long comb(int n, int r) {
		if(r==0) {
			return 1;
		}
		
		long[] fac = new long[n+1];
		fac[0] = 1;
		
		for (int i = 1; i <= n; i++) fac[i] = fac[i-1]*i % mod;
		
		return fac[n] * power(fac[n], mod-2) %mod * power(fac[n-r], mod-2) %mod; 
	}
}
