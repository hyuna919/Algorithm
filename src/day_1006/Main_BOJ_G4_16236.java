package day_1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 아기상어
 * 0907에 한번 시도해본적있음(당시엔 실패)
 * 
 */

public class Main_BOJ_G4_16236 {
	private static class Shark implements Comparable<Shark>{
		int x,y,size,eat,time;

		public Shark(int x, int y, int size, int eat, int time) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.eat = eat;
			this.time = time;
		}

		@Override
		public int compareTo(Shark o) {
			int res = this.time - o.time;
			if(res==0) res = this.x - o.x;
			if(res==0) res = this.y - o.y;
			return res;
		}
	}
	static int N, cnt;
	static int[][] map;
	static boolean[][] visited;
	static Shark shark;
	
	// 더 위에->더 왼쪽에 우선순위 -> 상좌우하
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		

		// 공간 입력 및 물고기 위치, 상어 위치 확인
		int tmp;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				tmp = Integer.parseInt(token.nextToken());
				map[i][j] = tmp;
				if(tmp==0) {	// 빈공간
					continue;
				}else if(tmp==9) {	// 상어
					shark = new Shark(i, j, 2, 0, 0); // 처음크기 2, 먹은 물고기 없음, 움직이기 전이라 시간0
					map[i][j] = 0;
				}
			}
		}
		
		bfs();

		System.out.println(shark.time);
		
	}

	private static void bfs() {
		PriorityQueue<Shark> q = new PriorityQueue<Shark>();
		visited = new boolean[N][N];
		q.offer(shark);
		visited[shark.x][shark.y] = true;
		
		Shark now;
		int nx, ny, length;
		// 이동할떄마다 잡아먹으려니까 우-우 말고 좌-하를 먼저 먹는 문제가 있더라
		// 미리 검사하고 이동하는게 아니고 일단 움직이고나서 현위치 먹이가 최상-최좌인지 판단하자.
		while(!q.isEmpty()) {
			length = q.size();
			for (int i = 0; i < length; i++) {
				now = q.poll();
				// 먹을 수 있다면(물고기가 상어보다 작다면)
				if(map[now.x][now.y]!=0 && map[now.x][now.y]<now.size) {
					q = new PriorityQueue<Shark>();	// 새 위치에서 다시 근처 물고기를 찾아야해서
					visited = new boolean[N][N];
					// 물고기 먹고 방문표시
					map[now.x][now.y] = 0;
					visited[now.x][now.y] = true;
					int eat = now.eat+1;
					// 성장 여부
					if(eat==now.size) {	// 먹이 다 채움
						shark = new Shark(now.x, now.y, now.size+1, 0, now.time);
					}else {
						shark = new Shark(now.x, now.y, now.size, eat, now.time);
					}
					q.offer(shark);
					continue;	// 물고기 먹었으면 새로 시작하니까 이번 반복도 끝내야
				}
				
				for (int j = 0; j < 4; j++) {
					nx = now.x + dx[j];
					ny = now.y + dy[j];
					
					// 경계검사
					if( nx<0 || nx>=N || ny<0 || ny>=N ) continue;
					// 방문 검사
					if(visited[nx][ny]==true) continue;
					// 지나갈 수 없는지 검사(상어보다 큰 물고기)
					if(map[nx][ny]>now.size) continue;
						
					// 빈공간인지 검사
					if(map[nx][ny]==0) {
						q.offer(new Shark(nx, ny, now.size, now.eat, now.time+1));
						visited[nx][ny] = true;
					}
					
					// 먹을수 있거나 지나갈수있다면
					else if(map[nx][ny]<=now.size) {
						q.offer(new Shark(nx, ny, now.size, now.eat, now.time+1));
						visited[nx][ny] = true;
					}
				}
			}
		}
	}
}
