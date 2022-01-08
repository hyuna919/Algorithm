package day_0914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1149 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		int N = Integer.parseInt(in.readLine());
		
		int[][] paint = new int[N+1][3];
		int[][] weight = new int[N+1][3];
		
		
		for (int i = 1; i <= N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < 3; j++) {
				paint[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		for (int i = 1; i <= N; i++) {
			weight[i][0] = Math.min(weight[i-1][1], weight[i-1][2]) + paint[i][0];
			weight[i][1] = Math.min(weight[i-1][0], weight[i-1][2]) + paint[i][1];
			weight[i][2] = Math.min(weight[i-1][0], weight[i-1][1]) + paint[i][2];
		}
		
		int min = Math.min(weight[N][0], weight[N][1]);
		min = Math.min(min, weight[N][2]);
		
		System.out.println(min);
		
	}

}
