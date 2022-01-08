package day_1110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 봄버맨

public class Main_BOJ_S1_16918 {
	static int R, C, N;
	static char[][] map;
	
	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(token.nextToken());
		C = Integer.parseInt(token.nextToken());
		N = Integer.parseInt(token.nextToken())+1;
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		int flag = 1;
		for (int sec = 2; sec < N; sec++) {
			flag *= -1;
			if(flag == -1) {	//2초
				// 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
				setBombs();
			}else {					//3초
				//  3초 전에 설치된 폭탄이 모두 폭발한다.
				bomb();
			}
		}
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]=='X') builder.append('O');
				else builder.append(map[i][j]);
			}
			builder.append('\n');
		}
		
		System.out.println(builder);
		
	}
	private static void setBombs() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]=='O') {
					map[i][j] = 'X';
				}else {
					map[i][j] = 'O';
				}
			}
		}
	}
	private static void bomb() {
		int nr, nc;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]=='X') {
					map[i][j] = '.';
					for (int k = 0; k < 4; k++) {
						nr = i+dr[k];
						nc = j+dc[k];
						if(nr<0 || nr>=R || nc<0 || nc>=C) continue;
						if(map[nr][nc]!='X') map[nr][nc] = '.';
					}
				}
			}
		}
		
	}

}
