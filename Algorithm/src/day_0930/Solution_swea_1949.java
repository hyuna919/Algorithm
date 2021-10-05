package day_0930;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import java.util.StringTokenizer;

/*
 * 
 *  모의 sw역량 테스트 등산로 조성
 * 
 */
public class Solution_swea_1949 {
	static class Position {
		int x,y,length,height,k;

		public Position(int x, int y, int length, int height, int k) {
			super();
			this.x = x;
			this.y = y;
			this.length = length;
			this.height = height;
			this.k = k;
		}
	}
	static int T, N, K, longest, maxHeight;
	static int[][] map, visited;
	static ArrayList<Position > list;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		
		T = Integer.parseInt(in.readLine())+1;
		for (int t = 1; t < T; t++) {
			token = new StringTokenizer(in.readLine());
			N = Integer.parseInt(token.nextToken());
			K = Integer.parseInt(token.nextToken());
			map = new int[N][N];

			longest = 0;
			maxHeight = 0;
			list = new ArrayList<Position>();
			
			int now;
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					now = Integer.parseInt(token.nextToken());
					map[i][j] =  now;
					if(maxHeight==now) {
						list.add(new Position(i,j,1,now,K));
					}else if(maxHeight<now) {
						maxHeight = now;
						list = new ArrayList<Position>();
						list.add(new Position(i,j,1,now,K));
					}
				}
			}
			
			for (Position start : list) {
				visited = new int[N][N];
				bfs(start);
			}
			
			
			builder.append("#"+t+" "+longest+"\n");
		}
		System.out.println(builder);
	}
	
	private static void bfs(Position curr) {
		Queue<Position> q = new LinkedList<Position>();
		q.offer(curr);
		visited[curr.x][curr.y] = curr.length;
		
		int x,y,nx,ny,gap;
		while(!q.isEmpty()) {
			curr = q.poll();
			x = curr.x;
			y = curr.y;
			for (int i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny]>curr.length+1) continue;
				if(nx==0&&ny==2) continue;
				
				if(map[nx][ny]>=curr.height) {	// 다음 위치가 나랑 같거나 높으면
					if(curr.k>0) {					// 공사 기회 있으면
						gap = map[nx][ny]-curr.height;
						if(gap<curr.k) {				// 두 위치의 차이가 K보다 작으면 공사가능 
							visited[nx][ny] = curr.length+1;
							q.offer(new Position(nx, ny, curr.length+1, curr.height-1,0));
						}else {
							longest = Math.max(longest, curr.length);
						}
					}else {
						longest = Math.max(longest, curr.length);
					}
					
				}else {							// 다음 위치가 나보다 낮음
					visited[nx][ny] = curr.length+1;
					q.offer(new Position(nx, ny, curr.length+1, map[nx][ny],curr.k));
				}
			}
		}
		
	}
}
