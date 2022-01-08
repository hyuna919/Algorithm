package day_0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_swea_D4_1259_Queue {
	static class Position{
		int x,y,cost;

		public Position(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
	
	static int T, N, min;
	static int[][] map, visited;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		char[] str;
		
		T = Integer.parseInt(in.readLine())+1;
		for (int t = 1; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			visited = new int[N][N];
			for (int i = 0; i < N; i++) {
				str = in.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = (int)str[j]-'0';
				}
			}
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], -1);
			}
			
			min = Integer.MAX_VALUE;
			bfs(new Position(0, 0, 0));
			
			
			builder.append("#"+t+" "+min+"\n");
		}
		System.out.println(builder);
	}
	
	private static void bfs(Position now) {
		Queue<Position> q = new LinkedList<Position>();
		q.offer(now);
		visited[0][0] = -1;
		
		int x,y,cost,nx,ny;
		while (!q.isEmpty()) {
			now = q.poll();
			x = now.x;
			y = now.y;
			cost = now.cost;
			for (int i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;	// 경계
				
				if(nx==N-1 && ny==N-1) {
					min = Math.min(min, cost);
					continue;
				}
				
				// 방문한 적 없음					기존보다 이번 경로 비용이 적음
				if(visited[nx][ny] == -1 || visited[nx][ny] > cost+map[nx][ny]) {
					visited[nx][ny] = cost+map[nx][ny];
					q.offer(new Position(nx, ny, cost+map[nx][ny]));
				}
				
			}
		}		
	}
}
