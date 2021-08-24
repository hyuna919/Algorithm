package day_0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_S3_11399 {
	static int N;
	static int[] P;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		P = new int[N];
		
		StringTokenizer token = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(token.nextToken());
		}
		Arrays.sort(P);
		
		int res = 0;
		for (int i = 0; i < N; i++) {
			res += P[i]*(N-i);
		}
		
		System.out.println(res);
	}
}