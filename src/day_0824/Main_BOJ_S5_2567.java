package day_0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_BOJ_S5_2567 {
	static int N, sum, nr, nc;
	static boolean[][] map;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		map = new boolean[101][101];
		
		// 입력
		int x,y;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());			
			x = Integer.parseInt(token.nextToken());
			y = Integer.parseInt(token.nextToken());
			fill(x,y);
		}
		
		for (int i = 1; i < 101; i++) {
			for (int j = 1; j < 101; j++) {
				check(i,j);
			}
		}		
		System.out.print(sum);
	}

	private static void check(int x, int y) {
		if(!map[x][y]) return;
		for (int i = 0; i < 4; i++) {
			nr = x+dr[i];
			nc = y+dc[i];
			if(!map[nr][nc])sum++;
		}		
	}

	private static void fill(int x, int y) {
		for (int i = x, endX = x+10; i < endX; i++) {
	        for (int j = y, endY = y+10; j < endY; j++) {
	        	map[i][j] = true;
	        }
		}
	}
}
