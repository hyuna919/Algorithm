package day_0917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/*
 * 
 * 프로세서 연결
 * 
 */
public class Solution_swea_1767 {
	static class Point{
		int x,y;
		int dir=0;
		
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, totalSum, minSum, minSumConn, cntConn, maxConn;
	static ArrayList<Point> list;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		
		int T = Integer.parseInt(in.readLine())+1;
		for (int t = 1; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			list = new ArrayList<Point>();
			map = new int[N][N];
			
			// 코어의 위치 기록
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					if(token.nextToken().equals("1")) {
						map[i][j] = 1;
						list.add(new Point(i,j));
					}
				}
			}
			
			cntConn = 0;
			maxConn = 0;
			minSumConn = 0;
			minSum = Integer.MAX_VALUE;
			totalSum = 0;
			
			dfs(0);

			builder.append("#"+t+" "+minSum+"\n");
		}
		System.out.println(builder);
	}
	
	private static void dfs(int n) {
		// 기저조건
		if(n==list.size()) {
			// 기존보다 연결 코어수가 더 많다면 현재 전선길이가 반영되어야
			if(minSumConn < cntConn) {
				minSumConn = cntConn;
				minSum = totalSum;
			}else if(minSumConn == cntConn){
				minSum = Math.min(minSum, totalSum);				
			}
			return;
		}
		
		// 가지치기: 연결수 같은데 선 연결 길이가 더 길면 리턴
		if(minSumConn == cntConn) {
			if(minSum==totalSum) return;
		}
		
		int x = list.get(n).x;
		int y = list.get(n).y;
		ArrayList<Point> line;	// 전선을 기록
		
		// 경계에 있으면 넘어간다
		if(x==0 || x==N-1 || y==0 || y==N-1) dfs(n+1);
		else {
			//상하좌우
			int nx, ny, cnt;
			for (int i = 0; i < 5; i++) {
				line = new ArrayList<Point>();
				nx = x; ny=y;
				cnt = 0;
				
				// 연결을 하지 않는 경우
				if(i==4) {
					dfs(n+1);
					break;
				}
				
				// 한 방향으로 나아가서 벽까지 연결함
				while(true) {
					nx += dx[i];
					ny += dy[i];
					
					if(nx<0|| nx>N-1 || ny<0 || ny>N-1) break;
					if(map[nx][ny]!=0) {
						break;
					}
					
					
					cnt++;
					map[nx][ny] = 2;
					line.add(new Point(nx,ny));
					
					// 벽까지 도달하면
					if(nx==0|| nx==N-1 || ny==0 || ny==N-1) {
						totalSum += cnt;
						cntConn++;
						dfs(n+1);
						totalSum -= cnt;
						cntConn--;
						break;
					}
				}
				
				// 벽까지 그었던 전선을 지움
				if(line.isEmpty()) continue;
				for (int j = 0, end = line.size(); j < end; j++) {
					map[line.get(j).x][line.get(j).y] = 0;
				}
				
			}
		}
	}

}
