package day_0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AdjMatrix {
	static int N;
	static boolean[] visited;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/AdjMatrix.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 입력 처리
		int T = Integer.parseInt(in.readLine());
		
		StringTokenizer token;
		for (int i = 0; i < N; i++) { 
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		// 4. 탐색
					bfs(0);
					System.out.println();
	}
	private static void bfs(int curr) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(curr);
		visited[curr] = true;
		
		while(!q.isEmpty()) {
			curr = q.poll();
			
			for (int i = 0; i < N; i++) {
				if (map[curr][i]==1 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		
		
	}

}
