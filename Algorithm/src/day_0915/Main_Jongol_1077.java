package day_0915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Jongol_1077 {
	static int N, W;
	static int[] weights, values;
	static int[][] D;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken());
		W = Integer.parseInt(token.nextToken());
		
		weights = new int[N+1];
		values = new int[N+1];
		D = new int[N+1][W+1];
		
		for (int i = 1, end = N+1; i < end; i++) {
			token = new StringTokenizer(in.readLine());
			weights[i] = Integer.parseInt(token.nextToken());
			values[i] = Integer.parseInt(token.nextToken());
		}
		
		int tmp;
		for (int i = 1, end = N+1; i < end; i++) {
			for (int w = 1; w <= W; w++) {
				tmp = w/weights[i];
				if(weights[i]<=w) 
					D[i][w] = Math.max(D[i-1][w], D[i-1][w-tmp*weights[i]] + values[i]*tmp);
				else
					D[i][w] = D[i-1][w];
			}
		}
		System.out.println(D[N][W]);
	}
}