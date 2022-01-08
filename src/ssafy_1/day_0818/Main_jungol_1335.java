package day_0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.table.TableColumn;

public class Main_jungol_1335 {
	static int N, white, blue;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		N = Integer.parseInt(in.readLine());
		
		map = new int [N][N];
		for (int i = 0; i <N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		count(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
		
	}
	
	private static void count(int x, int y, int n) {
		// 기저
		// 전부 파랑(1)이면 
		if(check(x,y,n,1)) {
			blue++;
			return;
		}
		// 전부 하양(0)이면 
		else if(check(x,y,n,0)) {
			white++;
			return;
		}
		
		// 재귀
		else {
			int half = n/2;
			count(x,y,half);
			count(x,y+half,half);
			count(x+half,y,half);
			count(x+half,y+half,half);
			
		}
		
		
		
	}

	private static boolean check(int x, int y, int n, int color) {
		for (int i = x, endX = x+n; i < endX; i++) {
			for (int j = y, endY = y+n; j < endY; j++) {
				if(map[i][j] != color) return false;
			}
		}
		return true;
	}

}
