package day_0917;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 치즈옮기기
 */

public class Main_BOJ_G5_2636 {
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, time, cheese, lastCheese;
	static int[][] map;	// 0치즈안 빈공간 1치즈 2치즈밖 3녹는치즈
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer token = new StringTokenizer(in.readLine()); 
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		map = new int[N][M];
		

		
		// 입력
		// 1번줄부터 입력
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		// 최초 공기처리 + 녹이기 + 녹은거 제거
		while(true) {
			// 치즈 외부 2 처리
			lastCheese = cheese;
			cheese = 0;
			bfs(0,0);
			if(cheese==0) break;
			remove();
			time++;
		}
		
		System.out.println(time + "\n" + lastCheese);
	}
	private static void remove() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==1) continue;
				else map[i][j]=0;
			}
		}
	}	
	
	private static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(x, y));
		map[x][y] = 2;
		
		Point now;
		int nx, ny;
		while (!q.isEmpty()) {
			now = q.poll();
			x = now.x;
			y = now.y;
			
			for (int i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				
				// 경계검사 + 방문 검사
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				if(map[nx][ny] == 2) continue;
				// 치즈 밖처리
				if(map[nx][ny] == 0) {
					map[nx][ny] = 2;
					q.offer(new Point(nx,ny));
				}else if(map[nx][ny]==1) {
					map[nx][ny] = 3;
					cheese++;
				}
			}
			
		}
	}
}
