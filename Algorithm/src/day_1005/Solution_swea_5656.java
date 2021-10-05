package day_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_swea_5656 {
	static class Point{
		int r,c,cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int T,N,W,H,min;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		
		T = Integer.parseInt(in.readLine())+1;
		for (int t = 1; t < T; t++) {
			token = new StringTokenizer(in.readLine());
			N = Integer.parseInt(token.nextToken());
			W = Integer.parseInt(token.nextToken());
			H = Integer.parseInt(token.nextToken());
			int[][] map = new int[H][W];
			
			// 벽돌 입력
			min = 0;
			for (int i = 0; i < H; i++) {
				token = new StringTokenizer(in.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			// 구슬 N번
			shooting(0,map);

			builder.append("#"+t+" "+min+"\n");
		}
		System.out.println(builder);
	}
	private static void shooting(int cnt, int[][] map) {
		if(cnt==N) {
			int res = getRemain(map);
			min = Math.min(min, res);
			return;
		}
		
		int[][] newMap = new int[H][W];
		for (int c = 0; c < W; c++) {
			int r= 0;
			// c열에 구슬을 쐈을 때 위에서 처음 만나는벽돌 찾기
			while(r<H && map[r][c]==0) r++;
			
			if(r==H) {	// 구슬이 바닥에 닿았다->해당 열에 벽돌이 없었다
				shooting(cnt+1,map);	// 다음 구슬 던지기
			}else {	// 벽돌 맞춤
				// map 복사
				copy(map, newMap);
				// 제거
				boom(newMap, r,c);
				// 내리기
				fall(newMap);
				// 다음구슬 쏘기
				shooting(cnt+1, newMap);
				
			}
		}
		
	}
	private static int getRemain(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]>0) cnt++;
			}
		}
		return cnt;
	}
	private static void fall(int[][] map) {
		for (int c = 0; c < W; c++) {	
			int r = H-1;
			while(r>0) {
				if(map[r][c] == 0) {
					int nr = r-1;
					while(nr>0 && map[nr][c] == 0) nr--;
					map[r][c] = map[nr][c];
					map[nr][c] = 0;
				}
				--r;
			}
		}
		
	}
	private static void boom(int[][] map, int r, int c) {
		Queue<Point> q = new LinkedList<Point>();
		if(map[r][c]>1) {
			q.offer(new Point(r,c,map[r][c]));
		}
		map[r][c] = 0;
		
		Point p = null;
		while(!q.isEmpty()) {
			p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r;
				int nc = p.c;
				for (int k = 1; k < p.cnt; k++) {
					nr += dr[d];
					nc += dc[d];
					if(nr<0 || nr>=H || nc<0 || nc>=W || map[nr][nc]==0) continue;
					
					if(map[nr][nc]>1) q.offer(new Point(nr,nc,map[nr][nc]));
					map[nr][nc] = 0;
				}
			}
		}
		
	}
	private static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
	
}
