package day_0928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_G4_10830 {

	static int T = 1000, N;
	static int[][] m;
	static long B;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		N = Integer.parseInt(token.nextToken());
		B = Long.parseLong(token.nextToken());
		m = new int[N][N];
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				m[i][j] = Integer.parseInt(token.nextToken())%T;
			}
		}
		
		int[][] mm = matrix(B);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(mm[i][j]+" ");
			}
			System.out.println();
		}

	}
	private static int[][] matrix(long y) {
		int[][] res = new int[N][N];
		for (int i = 0; i < N; i++) {
			res[i][i] = 1;
		}
		while(y>0L) {
			if(y%2==1L) {
				res = mul(res,m);
			}
			y/=2L;
			res = mul(m,m);
		}
		return res;
	}
	private static int[][] mul(int[][] r, int[][] x) {
		int[][] res = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int t = 0;
				for (int k = 0; k < N; k++) {
					t = (t%T + (r[i][k]*x[k][j])%T)%T;
				}
				res[i][j] = t%T;
			}
		}
		return res;
	}

}
