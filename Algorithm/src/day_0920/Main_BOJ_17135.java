package day_0920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17135 {
	static class Position{
		int x,y,d;

		public Position(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	static int N, M, D, max=0, kill, enemyOri, enemy;
	static int[] archer = new int[3];
	static int[][] mapOri, map;
	static ArrayList<int[]> killXY;
	static Position now;
	
	// 좌상우
	static int[] dx = {0,-1,0};
	static int[] dy = {-1,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		D = Integer.parseInt(token.nextToken());
		
		
		// 입력
		map = new int[N][M];
		mapOri = new int[N][M];
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				mapOri[i][j] = token.nextToken().equals("0")?0:1;
				if(mapOri[i][j]==1)enemyOri++;
			}
		}
		
		// 조합
		long stime = System.currentTimeMillis();
		comb(M,3);
		long etime = System.currentTimeMillis();
		
		System.out.println(etime-stime);
		System.out.println(max);
	}
	
	private static void comb(int n, int r) {
		if(r==0) {
			kill = 0;
			mapClone();
			defense();
			max = Math.max(max, kill);
			return;
		}
		
		if(n<r) return;
		if(n<1) return;
		
		archer[r-1] = n-1;
		comb(n-1,r-1);
		comb(n-1, r);
	}
	
	private static void mapClone() {
		for (int i = 0; i < N; i++) {
			map[i] = mapOri[i].clone();
		}
		enemy = enemyOri;
	}
	private static void defense() {
		for (int i = N; i > 0; i--) {
			killXY = new ArrayList<int[]>();
			
			for (int j = 0; j < 3; j++) {
				bfs(i,archer[j],D);
			}
			remove(i-1);
			if(enemy==0) return;
		}
	}
	
	private static void remove(int x) {
		for (int i = killXY.size()-1; i > -1; i--) {
			map[killXY.get(i)[0]][killXY.get(i)[1]] = 0;
			enemy--;
		}		
		for (int i = 0; i < M; i++) {
			if(map[x][i]==1) {
				map[x][i]=0;
				enemy--;
			}
		}
	}
	
	private static void bfs(int x, int y, int d) {
		Queue<Position> q = new LinkedList<Position>();
		q.offer(new Position(x,y,d));
		
		int nx, ny;
		while(!q.isEmpty()) {    
			now = q.poll();
			if(now.d==0) continue;
			
			for (int i = 0; i < 3; i++) {
				nx = now.x + dx[i];
				ny = now.y + dy[i];
				
				// 경계값 검사
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				// 궁병과 같은 행은 검사하지 않음
				if(nx==now.x && now.d==D) continue;
				// 적이 있으면 죽이고 다음 궁병으로
				if(map[nx][ny]==1) {
					killXY.add(new int[] {nx,ny});
					map[nx][ny] = 2;
					kill++;
					return;
				}else if(map[nx][ny]==2) {
					return;
				}else q.offer(new Position(nx,ny,now.d-1));
			}
		}
	}
}