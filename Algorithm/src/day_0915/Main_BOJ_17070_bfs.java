package day_0915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17070_bfs {
	
	static int N, total;
	static int[][] map, visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		map = new int[N+1][N+1];
		visited = new int[N+1][N+1];
		
		for (int i = 1, end = N+1; i < end; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 1; j < end; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		bfs();
		
		System.out.println(visited[N][N]);
		
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		// { x,y,type }
		q.offer(new int[] {1,2,0});
		visited[1][2] = 1;
		
		int[] tmp = new int[2];
		int x,y, type, nx, ny, cnt;
		while(!q.isEmpty()) {
			tmp = q.poll();
			x = tmp[0];
			y = tmp[1];
			type = tmp[2];
			cnt = visited[x][y];

			for (int i = 0; i < 3; i++) {
				tmp = isAble(x,y,i,type);
				if(tmp[0]==-1) continue;
				nx = tmp[0];
				ny = tmp[1];
				
				visited[nx][ny] += 1;
				if(nx==N && ny==N) continue;
				else q.offer(new int[] {nx,ny, i});
			}
		}
		
	}

	private static int[] isAble(int x, int y, int i, int type) {
		// 가로-세로-대각선
		if(i==0) {
			if(isIn(x,y+1) && map[x][y+1]==0 && type!=1) return new int[] {x,y+1};
		}else if(i==1) {
			if(isIn(x+1, y) && map[x+1][y]==0 && type!=0) return new int[] {x+1,y};
		}else if(i==2) {
			if(isIn(x+1,y+1) && map[x][y+1]==0 && map[x+1][y]==0 && map[x+1][y+1]==0) 
				return new int[] {x+1,y+1};
		}
		return new int[] {-1,-1};
	}

	private static boolean isIn(int x, int y) {
		if(x<1 || x>N || y<1 || y>N) return false;
		return true;
	}
}
