package day_0916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.StringTokenizer;

public class Solution_swea_D6_1263 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		StringBuilder builder = new StringBuilder();
		
		int[][] map;
		int max = Integer.MAX_VALUE >>2;
		int N, res;
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			token = new StringTokenizer(in.readLine());
			N = Integer.parseInt(token.nextToken());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
					if(map[i][j]==0 && i!=j) map[i][j] = max;
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
					}
				}
			}
			
			
			int tmp;
			res = max;
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					tmp = 0;
					for (int j = 0; j < N; j++) {
						tmp += map[i][j];
					}
					res = Math.min(res, tmp);
				}
			}
			
			builder.append("#"+t+" ").append(res+"\n");
			
		}
		System.out.println(builder);
	}
}
