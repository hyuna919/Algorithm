package day_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_S1_2468 {
	static class Position{
		int x,y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int N, max;
	static int[][] newMap;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		
		int maxHeight = 0;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if(map[i][j] > maxHeight) maxHeight = map[i][j];
			}
		}
		
		newMap = new int[N][N];
		int cnt;
		for (int i = 0; i < maxHeight; i++) {
			cnt = 0;
			copy(map);		// 작업가능한 맵 생성
			flooding(i);	// 높이만큼 침수, 침수는 -1로 표기
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(newMap[j][k]>0) {
						counting(j,k);	// 구역 세기, 각 구역응 0으로 채운다
						cnt++;
					}
				}
			}
			max = Math.max(max, cnt);
		}
		
		System.out.println(max);
	}
	
	//bfs
	private static void counting(int x, int y) {
		Queue<Position> q = new LinkedList<Position>();
		newMap[x][y] = 0; 
		q.offer(new Position(x,y));
		
		Position now;
		int nx,ny;
		while(!q.isEmpty()) {
			now = q.poll();
			for (int i = 0; i < 4; i++) {
				nx = now.x + dx[i];
				ny = now.y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(newMap[nx][ny]<1) continue;	// 침수됐거나, 이미 확인한 지역이면
				
				newMap[nx][ny] = 0; 			// 침수안됐고, 확인 안한곳이면 확인했다고 0으로 채운다
				q.offer(new Position(nx,ny));
			}
		}
	}

	private static void copy(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
	
	private static void flooding(int h) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(newMap[i][j] <= h) newMap[i][j] = -1;
			}
		}
	}

}
