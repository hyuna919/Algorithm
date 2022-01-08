package day_0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.FileInputStream;


public class Main_BOJ_1987 {
	static int R, C, max = 1;
	static char[][] map;
	static int[][] visited;
	
	// 상하좌우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer token = new StringTokenizer(in.readLine());
		R = Integer.parseInt(token.nextToken());
		C = Integer.parseInt(token.nextToken());
		
		map = new char[R][C];
		visited = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		move(0,0,1,1<<(map[0][0]-'A')); // 첫칸도 움직인걸로 친다
		System.out.println(max);
	}
	private static void move(int r, int c, int cnt, int flag) {
		// 백트래킹...여기까지 거친 알파벳이 같다면
		if(visited[r][c] == flag) return;
		visited[r][c] = flag;
		
		// 유도
		int nr, nc;
		for (int i = 0; i < 4; i++) {
			nr = r+dr[i];
			nc = c+dc[i];
			
			if(nr>-1 && nr<R && nc>-1 && nc<C) {	// 경계값
				int alpha = map[nr][nc]-'A';
				if((flag & 1<<alpha) == 0) {		// 해당 알파벳 쓴적 없으면
					max = Math.max(max, cnt+1);
					move(nr, nc, cnt+1, flag|1<<alpha);
				}
			}
		}
	}
}
