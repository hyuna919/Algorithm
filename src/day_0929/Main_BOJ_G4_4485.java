package day_0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_G4_4485 {
	static class Vertex implements Comparable<Vertex> {
		int x,y,distance;

		public Vertex(int x, int y, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.distance - o.distance;
		}
	}
	static int N;
	static boolean[][] visited;
	static int[][] map, distance;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		
		int t = 0;
		while(true) {
			t++;
			N = Integer.parseInt(in.readLine());
			if(N == 0) break;
			
			map = new int[N][N];
			distance = new int[N][N];
			visited = new boolean[N][N];			
			
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) Arrays.fill(distance[i], Integer.MAX_VALUE);
			distance[0][0] = 0;
			
			function();
			
			builder.append("Problem "+t+": "+distance[N-1][N-1]+"\n");
		}
		System.out.println(builder);
	}
	
	private static void function() {
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		q.offer(new Vertex(0, 0,map[0][0]));
		int nx,ny,x,y,curDis;
		Vertex cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			x = cur.x;
			y = cur.y;
			for (int i = 0; i < 4; i++) {
				nx = cur.x + dx[i];
				ny = cur.y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				curDis = cur.distance + map[nx][ny];
				if(distance[nx][ny] > curDis) {
					distance[nx][ny] = curDis;
					if(nx==N-1 && ny==N-1) return;
					q.offer(new Vertex(nx, ny, curDis));
				}
			}
		}
		
	}
}
