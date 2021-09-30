package day_0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  탈주범 검거
 * 	
 *  파이프 연결이 되려면 이번 방향+다음파이프 방향이 상하 / 좌우 조합이 되어야한다
 *  어느쪽이 어떤 방향인지는 중요하지 않음
 *  따라서 아래 식을 이용하면 두 파이프가 연결되는지 알 수 있다.
 *  상+하 = 1, 상*하=0
 *  좌+우 = 5 좌*우=6
 */

public class Solution_swea_1953 {
	static class Position {
		int x,y,time;

		public Position(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	static int T, N, M, R,C,L,cnt;
	static int[][] map;
	static boolean[][] visited;
	
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] pipeDir = {{0,0,0,0},
								{1,1,1,1},
								{1,1,0,0},
								{0,0,1,1},
								{1,0,0,1},
								{0,1,0,1},
								{0,1,1,0},
								{1,0,1,0}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
 		
		T = Integer.parseInt(in.readLine())+1;
		for (int t = 1; t < T; t++) {
			token = new StringTokenizer(in.readLine());
			N = Integer.parseInt(token.nextToken());
			M = Integer.parseInt(token.nextToken());
			R = Integer.parseInt(token.nextToken());
			C = Integer.parseInt(token.nextToken());
			L = Integer.parseInt(token.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(in.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			
			if(L==1) cnt = 1;
			else {
				cnt = 0;
				bfs();
			}
			
			builder.append("#"+t+" "+cnt+"\n");
		}
		System.out.println(builder);
	}
	
	private static void bfs() {
		Queue<Position> q = new LinkedList<Position>();
		q.offer(new Position(R, C, L-1));
		
		Position now;
		int x,y,nx,ny, pipe, dir;
		while(!q.isEmpty()) {
			now = q.poll();
			if(now.time == 0) continue;
			x = now.x;
			y = now.y;
			pipe = map[x][y];
			for (int thisPipeDir = 0; thisPipeDir < 4; thisPipeDir++) {
				dir = pipeDir[pipe][thisPipeDir];
				if(dir==0) continue;	// 파이프 방향 아님
				
				nx = x+dx[thisPipeDir];
				ny = y+dy[thisPipeDir];
				
				if(nx<0||nx>=N||ny<0||ny>=M) continue;	// 경계검사
				if(visited[nx][ny]) continue;			// 방문검사
				if(map[nx][ny]==0) continue;			// 파이프(길) 검사
				
				// 범위 안이고, 내 파이프로 갈 수도 있음
				// (nx,ny)의 파이프가 내 방향인지 확인 (상+하 /좌+우)
				for (int nextPipeDir = 0; nextPipeDir < 4; nextPipeDir++) {
					if(pipeDir[map[nx][ny]][nextPipeDir]==0) continue;
					if(thisPipeDir + nextPipeDir == 1 && thisPipeDir*nextPipeDir==0) {	// 상+하 조합
						cnt++;
						visited[nx][ny] = true;
						q.offer(new Position(nx, ny, now.time-1));
					}else if(thisPipeDir + nextPipeDir == 5 && thisPipeDir*nextPipeDir==6) {	// 좌+우 조합
						cnt++;
						visited[nx][ny] = true;
						q.offer(new Position(nx, ny, now.time-1));
					}
				}
			}
		}
	}
}
