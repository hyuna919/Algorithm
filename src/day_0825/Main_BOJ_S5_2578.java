package day_0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_S5_2578 {
	
	// 좌->우 가로
	static void funA() {
		for (int i = 0; i < N; i++) {
			for (int c = 0; c < N; c++) {
				if(map[i][c]!=0) break;
				if(c==4) cntLine++;
			}
		}
	}
	// 상->하 세로
	static void funB() {
		for (int i = 0; i < N; i++) {
			for (int r = 0; r < N; r++) {
				if(map[r][i]!=0) break;
				if(r==4) cntLine++;
			}
		}
	}
	// 좌->우 하향대각선
	static void funC() {
		for (int i = 0; i < N; i++) {
			if(map[i][i]!=0) return;
		}
		cntLine++;
	}
	// 좌->우 상향대각상
	static void funD() {
		int c;
		for (int i = 0; i < N; i++) {
			c = N-i-1;
			if(map[i][c]!=0) return;
		}
		cntLine++;
	}

	static int N = 5, cntLine=0;
	static int[][] map = new int[N][N];
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer token;
		// 번호판 입력
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		// 불러주는 숫자
		int num, cnt=0;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				num = Integer.parseInt(token.nextToken());
				
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if(map[r][c]==num) {
							map[r][c] = 0;
							cnt++;
							
							
							if(cnt<12) continue;
							cntLine = 0;
							funA();
							funB();
							funC();
							funD();
							
							if(cntLine>2) {
								System.out.println(cnt);
								return;
							}
						}
					}
				}
			}
		}
	}
}