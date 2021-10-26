package day_1025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 
 * 
 * 삼성기출
 * 주사위 굴리기 2
 * 
 * 배열로 주사위 각 면을 가지고, 함수를 이용해 회전마다 배열 숫자를 움직임
 * 
 * 개선사항
 * 숫자를 움직이느게 아니라 회전에 따라 주사위 밑면이 어디인지만 가리키면 된다.
 * 
 */
public class Main_BOJ_G3_23288 {
	
	private static class Dice{
		int[] nums;
		
		public Dice() {
			super();
			this.nums = new int[] {2,1,5,6,4,3};
		}

		// X 상
		public void X() {
			int tmp = nums[0];
			for (int i = 0; i < 3; i++) {
				nums[i] = nums[i+1];
			}
			nums[3] = tmp;
		}
		
		// Xr
		public void Xr() {
			int tmp = nums[3];
			for (int i = 3; i > 0; i--) {
				nums[i] = nums[i-1];
			}
			nums[0] = tmp;
		}
		
		// Z 우
		public void Z() {
			int tmp = nums[5];
			nums[5] = nums[1];
			nums[1] = nums[4];
			nums[4] = nums[3];
			nums[3] = tmp;
		}
		
		// Zr 좌
		public void Zr() {
			int tmp = nums[4];
			nums[4] = nums[1];
			nums[1] = nums[5];
			nums[5] = nums[3];
			nums[3] = tmp;
		}
		
		public int getDown() {
			return nums[3];
		}
		
	}
	
	private static class Position{
		int x,y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	// dir: 상하좌우
	static int N, M, K, total, dir=3;
	static int[][] map;
	static Position now;
	static boolean[][] visited;
	static Dice dice = new Dice();
	
	// 주사위 이동 및 방향 지시용 상우하좌
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken())+1;
		M = Integer.parseInt(token.nextToken())+1;
		K = Integer.parseInt(token.nextToken());
		
		map = new int[N][M];
		
		for (int i = 1; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 1; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());				
			}
		}
		
		now = new Position(1, 1);
		dir = 1;// 동쪽(우측)
		
		int cnt, A, B, C;
		while(K>0) {
			K--;
			// 매번 초기화
			visited = new boolean[N+1][M+1];
			
			// 한칸 회전(칸 없으면 반대로)-주사위회전+위치이동
			move();
			// 점수 획득
			B = map[now.x][now.y];
			C = bfs(B);
			total += B * C;
			// 이동방향 결정
			A = dice.getDown();
			if(A>B) turn(0);	// 시계방향
			else if(A<B) turn(1);// 반시계방향
			// 같으면 변화 없음
		}
		System.out.println(total);
		
	}



	private static void turn(int key) {
		switch (key) {
		case 0:	// 시계
			dir++;
			if(dir>3) dir = 0;
			
			break;	// 반시계

		case 1:
			dir--;
			if(dir<0) dir=3;
			break;
		}
	}



	// bfs
	private static int bfs(int num) {
		Queue<Position> q = new LinkedList<Position>();
		q.offer(now);
		visited[now.x][now.y] = true;
		int cnt = 1;
		
		int nx, ny;
		Position pos;
		while(!q.isEmpty()) {
			pos = q.poll();
			for (int i = 0; i < 4; i++) {
				nx = pos.x + dr[i];
				ny = pos.y + dc[i];
				
				if(nx<1||nx>=N||ny<1||ny>=M) continue;
				if(visited[nx][ny]) continue;
				
				if(map[nx][ny]!=num) continue;
				
				visited[nx][ny] = true;
				q.offer(new Position(nx, ny));
				cnt++;
			}
		}
		return cnt;
		
	}




	private static void move() {
		
		
		// 이동
		int nx, ny;
		nx = now.x + dr[dir];
		ny = now.y + dc[dir];
		
		if(nx<1||nx>=N||ny<1||ny>=M) {	// 경계에 닿아서 반대로 튕김
			now.x -= dr[dir];
			now.y -= dc[dir];
			// 반대로 튕기면 dir로 바꿔야한다.
			switch (dir) {
			case 0:
				dir = 2;
				break;
				
			case 1:
				dir = 3;	
				break;
			case 2:
				dir = 0;
				break;
			case 3:
				dir = 1;
				break;
			}
		}else {
			now.x += dr[dir];
			now.y += dc[dir];
		}
		
		switch (dir) {
		case 0:	//상
			dice.X();
			break;
		case 1:	//우
			dice.Z();		
			break;
		case 2:	//하
			dice.Xr();
			break;
		case 3:	//좌
			dice.Zr();
			break;
		}
		
		
		
	}

}
