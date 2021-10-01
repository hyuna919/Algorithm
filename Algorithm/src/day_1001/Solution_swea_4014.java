package day_1001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution_swea_4014 {	
	static int T, N, X, res;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		
		T = Integer.parseInt(in.readLine())+1;
		for (int t = 1; t < T; t++) {
			token = new StringTokenizer(in.readLine());
			N = Integer.parseInt(token.nextToken());
			X = Integer.parseInt(token.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			
			res = 0;
			for (int i = 0; i < N; i++) {
				horizon(i);
				vertical(i);
			}
			
			builder.append("#"+t+" "+res+"\n");
		}
		System.out.println(builder);
	}

	private static void horizon(int x) {
		int nowH, cntSame, gap;
		nowH = map[x][0];
		cntSame = 1;
		for (int y = 1; y < N; y++) {
			gap = nowH - map[x][y];
			
			// 높이차가 1 이상
			if(Math.abs(gap)>1) return;
			
			if(gap == 0) {
				cntSame++;
				nowH = map[x][y];
				continue;
			}
			
			// 높이 다르다 -> 경사로 필요
			// 1차이로 올라가는 경우
			if(gap == -1) {
				if(cntSame>=X) {
					cntSame = 1;
					nowH = map[x][y];
					continue;
				}else {
					return;
				}
			}
			
			// 1 차이로 내려가는 경우
			if(gap == 1) {
				nowH = map[x][y];
				cntSame = 1;
				for (int i = 1; i < X; i++) {
					y++;
					if(y>=N) return;
					if(nowH != map[x][y]) return;
				}
				nowH = map[x][y];
				cntSame = 0;	// 경사로 끝이라 길이 0처리
			}
			
		}
		res++;
	}
	
	private static void vertical(int y) {
		int nowH, cntSame, gap;
		nowH = map[0][y];
		cntSame = 1;
		for (int x = 1; x < N; x++) {
			gap = nowH - map[x][y];
			
			// 높이차가 1 이상
			if(Math.abs(gap)>1) return;
			
			if(gap == 0) {
				cntSame++;
				nowH = map[x][y];
				continue;
			}
			
			// 높이 다르다 -> 경사로 필요
			// 1차이로 올라가는 경우
			if(gap == -1) {
				if(cntSame>=X) {
					cntSame = 1;
					nowH = map[x][y];
					continue;
				}else {
					return;
				}
			}
			
			// 1 차이로 내려가는 경우
			if(gap == 1) {
				nowH = map[x][y];		// 경사로 시작부부터 길이 센다
				cntSame = 1;
				for (int i = 1; i < X; i++) {	// 경사로의 길이동안은 높이가 같아야한다
					x++;
					if(x>=N) return;
					if(nowH != map[x][y]) return;
				}
				nowH = map[x][y];
				cntSame = 0;	// 경사로 끝이라 길이 0처리
			}
		}
		res++;
	}
}
