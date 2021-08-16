package day_0813;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S1_2961_재귀 {
	static int N, min = Integer.MAX_VALUE;
	static int[] subset;
	static int[][] inputs;	// 0 신맛(곱) 1 쓴맛(합)
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		
		inputs = new int[N][2];
		subset = new int[N];
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < 2; j++) {
				inputs[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		for (int i = 1; i < N+1; i++) {
			subset(N, i, 1, 0);
		}
		
		System.out.println(min);
	}
	
	private static void subset(int n, int r, int salty, int bitter) {
		// 기저조건
		if(r==0) {
			min = Math.min(min, Math.abs(salty-bitter));
			return;
		}

		if(n<r) return;
		
		subset(n-1, r-1, salty *inputs[n-1][0], bitter+inputs[n-1][1]);
		subset(n-1, r, salty, bitter);
		
	}	
}