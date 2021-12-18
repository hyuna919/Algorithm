package day_1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// K번째수


public class Main_BOJ_18809_Gaaaaaaaaaarden{
	static class Position{
		int x,y ;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, R, G, max;
	static int[][] groundMap;
	static boolean[][] nowMap, confirmMap;
	static List<Position> canList;
	
	// 상하좌우
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		G = Integer.parseInt(token.nextToken());
		R = Integer.parseInt(token.nextToken());
		
		groundMap = new int[N][M];
		canList = new ArrayList<Position>();
		
		int tmp;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tmp = Integer.parseInt(token.nextToken());
				groundMap[i][j] = tmp;
				// 배양액 뿌릴 수 있는 땅은 따로 저장
				if(tmp == 2) canList.add(new Position(i, j));
			}
		}
		
		// 배양액 뿌릴 조합
		// 초록끼리  순열 -> 남은 자리에 빨강끼리 순열
	}
}
