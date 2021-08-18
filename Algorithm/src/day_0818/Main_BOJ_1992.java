package day_0818;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1992 쿼드트리
public class Main_BOJ_1992 {
	static int[][] map;
	static StringBuilder builder;
	public static void main(String[] args) throws NumberFormatException, IOException  {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str;
		builder = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			str = in.readLine();
			for (int j = 0; j< N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		compress(0,0,N);
		System.out.println(builder);
	}
	
	private static void compress(int x, int y, int n) {
		// 기저조건
		if(isSame(x,y,n)) {
			builder.append(map[x][y]);
			return;
		}
		
		builder.append("(");
		
		// 4등분
		int half = n/2;
		compress(x,y,half);
		compress(x,y+half,half);
		compress(x+half,y,half);
		compress(x+half,y+half,half);
		
		builder.append(")");
	}

	private static boolean isSame(int x, int y, int n) {
		int value = map[x][y];
		for (int i = x, endX = x+n; i < endX; i++) {
			for (int j = y, endY = y+n; j < endY; j++) {
				if(map[i][j]!=value) return false;
			}
		}
		return true;
	}
}
