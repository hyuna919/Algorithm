package day_0928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * [Professional] 조합
 * 
 */
public class Solution_swea_D3_5607 {
	static int T, N, R, mod=1234567891;
	static long[] fac;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		StringBuilder builder = new StringBuilder();
		
		int max = 1000000+1;
		fac = new long[max];
		fac[0] =1;
		
		for (int i = 1; i < max; i++) fac[i] = (fac[i-1] * i) % mod; 
		
		T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			token = new StringTokenizer(in.readLine());
			N = Integer.parseInt(token.nextToken());
			R = Integer.parseInt(token.nextToken());
			
			builder.append("#"+t+" "+comb()+"\n");
		}
		System.out.println(builder);
	}
	
	// 5C3 = 5! * (3!2!)^1005^(mode(1007))
	private static long comb() {
		if(R==0) {
			return 1;
		}
		long n = fac[N]%mod;
		long r = power((fac[R]*fac[N-R])%mod,mod-2) % mod;
		return (n * r) %mod;
	}

	private static long power(long a, int x) {
		long res = 1;
		if(x==0) return 1;
		while(x>0) {
			if(x%2==1) res = (res*a)%mod;
			a = (a*a)%mod;
			x = x>>1;
		}
		return res;
	}
}
