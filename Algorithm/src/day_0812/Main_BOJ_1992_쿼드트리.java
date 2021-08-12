package day_0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1992_쿼드트리 {
	static int N, n; //배열 사이즈 N, 2의 n제곱
	static int [][] map;
	static StringBuilder builder;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		builder = new StringBuilder();
		
		N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		
		// 배열 받기
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < N; j++) {
				// 0은 거짓, 1은 참으로 넣기
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		quarter(N, 0 ,0);
		System.out.println(builder);
	}
	
	private static void quarter(int n, int r, int c) {
		int check = map[r][c];
		boolean flag= true;
		// 1개 단위까지
		top:
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(check != map[r+i][c+j]) {
					flag = false;
					break top;
				}
			}
		}
		
		if(flag) {
			builder.append(map[r][c]);
			return;
		}
		
		n /= 2;
		
		builder.append("(");
		// 좌상 - 우상 - 좌하 - 우하
		quarter(n, r , c);
		quarter(n, r , c+n);
		quarter(n, r+n , c);
		quarter(n, r+n , c+n);
		builder.append(")");
	}
}