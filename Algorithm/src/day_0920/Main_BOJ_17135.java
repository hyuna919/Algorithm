package day_0920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17135 {
	static int N, M, D, max=0, kill;
	static int[] archer = new int[3];
	static int[][] mapOri, map;
	static ArrayList<Integer> xKill, yKill;
	
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
			}
		}
		
		// 조합
		comb(M,3);
		
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
		
	}
	private static void defense() {
		for (int i = N; i > 0; i--) {
			xKill = new ArrayList<Integer>();
			yKill = new ArrayList<Integer>();
			for (int j = 0; j < 3; j++) {
				dfs(i,archer[j],D);
				remove();
			}
		}
	}
	
	private static void remove() {
		for (int i = xKill.size()-1; i > 0; i--) {
			map[xKill.get(i)][yKill.get(i)] = 0;
		}		
	}
	
	private static boolean dfs(int x, int y, int d) {
		if(d==0) return false;
		int nx, ny;
		for (int i = 0; i < 3; i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			
			// 경계값 검사
			if(nx<0||nx>=N||ny<0||ny>=M) continue;
			// 궁병과 같은 행은 검사하지 않음
			if(nx==x && d==D) continue;
			// 적이 있으면 죽이고 true반환(dfs완전 종료 위한 플래그)
			if(map[nx][ny]==1) {
				xKill.add(nx);
				yKill.add(ny);
				map[nx][ny] = 2;
				kill++;
				return true;
			}else if(map[nx][ny]==2) {
				return true;
			}
		}
		return false;
	}
	
	private static void bfs(int cur) {
		Queue<Integer> queue = new LinkedList<Integer>();   //탐색할 노드를 담을 queue
		
//		1. 첫 방문한 node를 queue에 담기 
		queue.offer(cur);
//		2. queue에 담은 node를 방문 처리 
		visited[cur] = true;
		
		
//		3. 탐색 시작 
		//queue에 방문할 node를 담기 때문에 queue가 empty라는것는 모든 노드를 다 방문 했다는 의미,  empty가 아니면 계속 방문을 해야 한다. 
		while(!queue.isEmpty()) {    
			cur = queue.poll();			//현재 방문할 노드를 queue에서 꺼내 온다 
			System.out.printf("%c ", cur+65);
			
//			인접한 node를 방문
			for (int adj = 0; adj < n; adj++) {
//				     인접 되어 있고                            인접 노드가  방문하지 않았다면 
				if(map[cur][adj]&& !visited[adj]) {
//					방문하기 위해 queue에 넣기 
					queue.offer(adj);
					visited[adj] = true;		//미리 방문 했다고 표시하기 
				}
			}
		}
	}
}
