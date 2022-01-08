package day_0907;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark{
	int x, y , size=2, eat=0, dist;

	public Shark(int x, int y, int dist) {
		super();
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

class Fish{
	int x, y, size;

	public Fish(int x, int y, int size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
	}
}

public class Main_BOJ_16236 {
	static int N, cnt;
	static int[][] map;
	static boolean[][] visited;
	static Shark shark;
	static ArrayList<Fish> bigfishes = new ArrayList<>();
	static ArrayList<Fish> smallfishes = new ArrayList<>();
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];

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
					shark = new Shark(i,j,0);
					map[i][j] = 0;
				}else if(tmp<2) {	// 먹을 수 있는 물고기
					smallfishes.add(new Fish(i,j,tmp));
				}else {				// 먹을 수 없는 물고기
					bigfishes.add(new Fish(i,j,tmp));
				}
			}
		}

		if(smallfishes.isEmpty()) {
			System.out.println(0);
		}
		
		while(!smallfishes.isEmpty()) {
//			System.out.println(shark.size);
			
			move_bfs();
			
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		
		System.out.println(cnt);
	}
	
	
	static void move_bfs() {
		Queue<Shark> q= new LinkedList<>();
		visited[shark.x][shark.y] = true;
		q.offer(shark);
		
		// 탐색
		int nx, ny;
		ArrayList<Fish> eatFish = new ArrayList<>();
		while(!q.isEmpty()) {
			Shark now = q.poll();
			for (int i = 0; i < 4; i++) {
				nx = now.x + dx[i];
				ny = now.y + dy[i];
				
				// 경계검사 + 물고기 크기비교 + 방문확인
				if(nx<0 || nx>N-1) continue;
				if(ny<0 || ny>N-1) continue;
				if(map[nx][ny] > now.size) continue;
				if(visited[nx][ny]) continue;
				
				// 방문+먹을수있는지
				q.offer(new Shark(nx, ny, now.dist+1));
				visited[nx][ny] = true;
				if(map[nx][ny]!=0 && map[nx][ny] < shark.size) {
					eatFish.add(new Fish(nx,ny,map[nx][ny]));
				}
			}
			
			if(smallfishes.isEmpty()) {
				return;
			}

			// 같은 거리에 있는 생선 중 더 위, 왼쪽에 있는 것
			if(!eatFish.isEmpty()) {			
				int min = 0;
				for (int i = 1, end = eatFish.size(); i < end; i++) {
					if(eatFish.get(min).x > eatFish.get(i).x) {
						min = i;
					}else if(eatFish.get(min).x == eatFish.get(i).x) {
						if(eatFish.get(min).y > eatFish.get(i).y) {
							min = i;
						}
					}
				}
				eat(eatFish.get(min));
				eatFish.clear();
				cnt += now.dist+1;				
				return;
			}
		}
		
	}

	static void eat(Fish fish) {
		shark.x = fish.x;
		shark.y = fish.y;
		shark.eat++;
		visited = new boolean[N][N];
		map[fish.x][fish.y] = 0;
		
		for (int i = smallfishes.size()-1; i > -1; i--) {
			if(smallfishes.get(i).x==fish.x && smallfishes.get(i).y==fish.y) {
				smallfishes.remove(i);
			}
		}
		
		if(shark.eat == shark.size) {
			shark.size++;
			shark.eat = 0;
			for (int i = bigfishes.size()-1; i >= 0; i--) {
				if(bigfishes.get(i).size < shark.size) {
					smallfishes.add(bigfishes.get(i));
					bigfishes.remove(i);
				}
			}
		}
	}
}
