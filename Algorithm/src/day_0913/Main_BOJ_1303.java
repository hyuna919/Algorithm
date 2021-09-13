package day_0913;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1303 {
	static int N, M, cntW, cntB, cnt;
	static char[][] map;
	static boolean[][] visited;
	
	static int[] dx = {-1,1, 0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer token = new StringTokenizer(in.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		map = new char[M][N];
		visited = new boolean[M][N];
		
		for (int i = 0; i < M; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					cnt = 0;
					dfs(i,j, map[i][j]);
					if(map[i][j]=='W') cntW += cnt*cnt;
					else cntB += cnt*cnt;
				}
			}
		}
		
		System.out.println(cntW + " " + cntB);
	}

	private static void dfs(int x, int y, char color) {
		visited[x][y] = true;
		cnt++;

		int nx, ny;
		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			
			// 경계검사
			if(nx<0 || nx>=M || ny<0 || ny>=N) continue;
			if(visited[nx][ny]) continue;
			if(map[nx][ny]!=color) continue;
			
			dfs(nx, ny, color);
			
		}
	}
}
