package day_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 인접행렬ver
public class main_BOJ_S2_1260{
	static int N, M, V, flag;
	static boolean[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		
		token = new StringTokenizer(in.readLine());
		N = Integer.parseInt(token.nextToken())+1;
		M = Integer.parseInt(token.nextToken());
		V = Integer.parseInt(token.nextToken());
		
		int r,c;
		arr = new boolean[1001][1001];
		for (int i = 1; i < M+1; i++) {
			token = new StringTokenizer(in.readLine());
			r= Integer.parseInt(token.nextToken());
			c= Integer.parseInt(token.nextToken());
			
			arr[r][c] = arr[c][r] = true;
		}
		

		boolean[] visited =new boolean[N];
		dfs(V,visited);
		System.out.println();
		bfs();
	}

	private static void dfs(int current, boolean[] visited) {

		visited[current] = true;
		System.out.print(current+ " ");
		
		for (int i = 0; i < N; i++) {
			if(!visited[i] && arr[current][i]) {
				dfs(i, visited);
			}
		}
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];
		
		q.offer(V);
		visited[V] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			System.out.print(current+ " ");
			
			for (int i = 0; i < N; i++) {
				if(!visited[i] && arr[current][i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
	}

}
