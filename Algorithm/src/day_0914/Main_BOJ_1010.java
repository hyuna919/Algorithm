package day_0914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1010 {
	static int[][] graph = new int[31][31];
	public static void main(String[] args) throws IOException {
		for (int i = 1; i <= 30; i++) {
			graph[i][0] = 1;
		}
		
		for (int n = 1; n <= 30; n++) {
			for (int r = 1; r <= n; r++) {
				
				if(n==r) graph[n][r] = 1;
				else graph[n][r] = graph[n-1][r-1]+graph[n-1][r];
			}
		}
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		StringBuilder builder = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		int N, M;
		for (int t = 0; t < T; t++) {
			token = new StringTokenizer(in.readLine());
			N = Integer.parseInt(token.nextToken()); 
			M = Integer.parseInt(token.nextToken()); 		
			
			
			builder.append(graph[M][N]+"\n");	
		}
		System.out.println(builder);
		
	}
}