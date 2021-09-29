package day_0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 달이 차오른다, 가자
 * 
 * 취약점
 * : map[x][y]를 이전에 a,b 키와 b,c키를 가지고 방문한 적이 있으면 visited[x][y]에 a,b,c가 체크되어있어서
 * 다음에 a,c키를 들고 방문할때 방문이 제한된다.
 * visited를 3차원 배열로 쓰는게 결국은 해결책같은데 별로 쓰고싶지 않다;;; 
 * 
 */
public class Main_BOJ_G1_1194 {
	static class Vertex implements Comparable<Vertex>{
		int x,y,cntMove, key;

		public Vertex(int x, int y, int cntMove, int key) {
			super();
			this.x = x;
			this.y = y;
			this.cntMove = cntMove;
			this.key = key;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.cntMove - o.cntMove;
		}
	}
	static int N, M;
	static int[][] visited;
	static char[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		map = new char[N][M];
		visited = new int[N][M];
		
		Vertex start = new Vertex(0, 0, 0,0);;
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if(map[i][j]=='0') {
					start = new Vertex(i, j, 0,1);
					map[i][j] = '.';
				}
			}
		}
		
		int minMove = bfs(start);
		
		System.out.println(minMove);
	}
	
	
	private static int bfs(Vertex v) {
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.offer(v);
		visited[v.x][v.y] = 1;
		
		int nx,ny,x,y, key;
		int now;
		while(!q.isEmpty()) {
			v = q.poll();
			x = v.x;
			y = v.y;
			for (int i = 0; i < 4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				// 안되는거 - 경계값, 새로운키없이 재방문
				if(nx<0||nx>=N || ny<0||ny>=M) continue;
				if(!haveNewKey(visited[nx][ny], v.key)) continue;
				now = map[nx][ny];
				
				switch (now) {
				case '#':		// 벽
					continue;
				case '1':		// 도착
					return ++v.cntMove;
				case '.':		// 길
					q.offer(new Vertex(nx, ny, v.cntMove+1,v.key));
					visited[nx][ny] = v.key;
					break;
				default:		// 문이나 열쇠
					if(now >='Z') {	// 열쇠면
						now = now -'a' +1;
						key = v.key | 1<<now;
						q.offer(new Vertex(nx, ny, v.cntMove+1,key));
						visited[nx][ny] = key;
					}else {			// 문이면 열리는지
						if((v.key & 1<<map[nx][ny])!=0) {	// 열리면
							q.offer(new Vertex(nx, ny, v.cntMove+1,v.key));
							visited[nx][ny] = v.key;							
						}
					}
					break;
				}
			}
		}
		return -1;
	}


	private static boolean haveNewKey(int now, int key) {
		if(now == 0) return true;
		int or = now | key;
		if(now == or) return false;
		return true;
	}

}
