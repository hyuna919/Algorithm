package daily;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AdjMatrix {
	private static boolean[][] map;
	private static boolean[] visited;
	private static int n, T;
	private static StringTokenizer token;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/AdjMatrix.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(in.readLine());
			
			// 배열
			map = new boolean[n][n];
			visited = new boolean[n];
			
			// 정보입력
			for (int i = 0; i < n; i++) {
				token = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; j++) {
					if(Integer.parseInt(token.nextToken())==1) map[i][j]=true;
				}
			}
			bfs(0);
			System.out.println();
		}
		
	}

	private static void bfs(int curr) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(curr);
		visited[curr] = true;
		
		while(!q.isEmpty()) {
			curr = q.poll();
			for (int adj = 0; adj < n; adj++) {
				if(map[curr][adj] && !visited[adj]) {
					q.offer(adj);
					visited[adj]= true;
				}
			}
			
		}
		
		
	}

}
