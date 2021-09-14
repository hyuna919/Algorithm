package day_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2178 {
	static int N, M;
	static char[][] map;
	static int[][] visited;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		map = new char[N][M];
		visited = new int[N][M];
		
		for (int i = 0; i < N ; i++) {
			map[i] = in.readLine().toCharArray();
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		
		bfs();
		
		System.out.println(visited[N-1][M-1]);
		
		
		
		
	}
	private static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		// x,y,최소비용
		q.offer(new int[]{0,0,1});
		visited[0][0] = 1;
		
		int x, y, cost, nx, ny;
		int[] node = new int[3];
		while(!q.isEmpty()) {
			node = q.poll();
			x = node[0];
			y = node[1];
			cost = node[2]+1;
			if(x==N-1 && y==M-1) continue;
			
			for (int i = 0; i < 4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				if(map[nx][ny]=='0') continue;
				
				if(visited[nx][ny] > cost) {
					q.offer(new int[] {nx,ny,cost});
					visited[nx][ny] = cost;
				}
			}
		}
	}
}
