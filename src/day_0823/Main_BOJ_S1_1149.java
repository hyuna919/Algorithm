package day_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_S1_1149 {
	static int N, min=Integer.MAX_VALUE;
	static int[][] minLevel;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][3];
		minLevel = new int[N][3];
		
		StringTokenizer token;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			map[i][0] = Integer.parseInt(token.nextToken());
			map[i][1] = Integer.parseInt(token.nextToken());
			map[i][2] = Integer.parseInt(token.nextToken());
			
			minLevel[i] = new int[]{min,min,min};
		}
		
		dfs(0, 0, -1);
		System.out.println(min);
	}
	private static void dfs(int level, int sum, int col) {
		if(sum>=min) return;
		
		if(level==N) {
			min = sum;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			if(i==col)continue;
			
			if(minLevel[level][i] <= sum+map[level][i]) continue;
			else minLevel[level][i] = sum+map[level][i];
			
			dfs(level+1, minLevel[level][i], i);
		}
		
	}

}
