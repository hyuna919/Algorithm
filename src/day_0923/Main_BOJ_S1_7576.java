package day_0923;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_S1_7576 {
	static class Position{
		int x,y,day;
		public Position(int x, int y, int day) {
			super();
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}
	
	static int N, M, cnt, tomato;
	static int[][] box;
	static boolean[][] visited;
	static ArrayList<Position> list;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		M = Integer.parseInt(token.nextToken());
		N = Integer.parseInt(token.nextToken());
		box = new int[N][M];
		visited = new boolean[N][M];
		
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(token.nextToken());
				if(box[i][j]== 1) list.add(new Position(i,j,0));
				if(box[i][j]== 0) tomato++;
			}
		}
		
		bfs();
		
		if(tomato>0) System.out.println(-1);
		else System.out.println(cnt);
	}
	private static void bfs() {
		Queue<Position> q = new LinkedList<Position>();
		for (int i = 0, end=list.size(); i < end; i++) {
			q.offer(list.get(i));
			// visited
		}
		
		Position now;
		int nx,ny;
		while(!q.isEmpty()) {
			now = q.poll();
			cnt = now.day;
			for (int i = 0; i < 4; i++) {
				nx = now.x+dx[i];
				ny = now.y+dy[i];
				
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(box[nx][ny]==-1) continue;
				if(box[nx][ny]== 1) continue;
				
				// 익지 않은 토마토면
				tomato--;
				box[nx][ny] = 1;
				q.offer(new Position(nx,ny,now.day+1));
			}
		}
	}
}
