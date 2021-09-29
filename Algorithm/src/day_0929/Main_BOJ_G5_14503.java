package day_0929;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_G5_14503 {
	static int N,M,r,c,d, cleaning;
	static int[][] map;
	
	// 북동남서
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		token = new StringTokenizer(in.readLine());
		r = Integer.parseInt(token.nextToken());
		c = Integer.parseInt(token.nextToken());
		d = Integer.parseInt(token.nextToken());
		
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		// 0: 빈칸	1: 벽	2: 청소함
		while(true) {
			// 1.현재위치 청소
			if(map[r][c]==0) {
				map[r][c] = 2;
				cleaning++;
			}
			// 2. 왼쪽방향 청소했는지 할수없는지
			if(!fuction(d, 0)) {
				System.out.println(cleaning);
				return;
			}
		}
	}
	private static boolean fuction(int nd, int cnt) {
		// 2. 왼쪽방향
		nd = (nd==0)?3:nd-1;	
		int nr = r + dr[nd];
		int nc = c + dc[nd];
		
		// 2.a 왼쪽 청소안함
		if(map[nr][nc]==0) {
			r = nr;
			c = nc;
			d = nd;
			return true;
		}else{	// 2.b 왼쪽 청소되어있거나 벽이면
			if(cnt==3) {	// 2.c 사방이 청소불가 
				nr = r + (dr[nd]*-1);
				nc = c + (dc[nd]*-1);
				
				if(map[nr][nc]==1) return false;	// 벽이라 후진 못하면 작동중지
				r = nr;
				c = nc;
				d = nd;
				return true;	// 후진
			}
			return fuction(nd,cnt+1); // 2.b 왼쪽에 청소할 공간 없음
		}
	}
}










