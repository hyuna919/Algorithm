package day_0924;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17144_dfs {
	static int R,C,T,dust;
	static int cleaner = -1;	// 공청기 윗칸, 열은 0 고정이라 저장 안해도된다
	static int[][] mapRoom, mapWeight;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(token.nextToken());
		C = Integer.parseInt(token.nextToken());
		T = Integer.parseInt(token.nextToken());
		
		mapRoom = new int[R][C];
		
		// 입력받으면서 총 먼지의 수 얻음
		int tmp;
		for (int i = 0; i < R; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < C; j++) {
				tmp = Integer.parseInt(token.nextToken());
				mapRoom[i][j] = tmp;
				if(tmp>0) dust += tmp;
				else if(tmp==-1 && cleaner==-1) cleaner = i;
			}
		}
		// n초
		for (int t = 0; t < T; t++) {
			// 먼지 확산
			diffuse();
			// 공청기-위-시계
			cleanUpside();
			// 공청기-아래-반시계
			cleanDownside();
		}
		
		// 출력
		System.out.println(dust);
	}
	
	private static void cleanUpside() {
		// 좌상우하-상우하좌
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		
		int d = 0,nx = cleaner,ny=0;
		while(d<4) {
			nx += dx[d];
			ny += dy[d];
			
			if(nx<=0 || nx >= cleaner || ny<=0 || ny>=C) {
				d++;
				continue;
			}
			mapRoom[nx][ny] = mapRoom[nx+dx[d]][ny+dy[d]];
		}
	}

	// 시계
	private static void cleanDownside() {
		// (사이드)좌상우하-(방향)상우하좌
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		
		int d = 0,nx = cleaner,ny=0,nnx,nny;
		while(d<4) {
			nx += dx[d];
			nnx = nx + dx[d];
			
			ny += dy[d];
			nny = ny+dy[d];
			
			if(nnx<0 || nnx > cleaner || nny<0 || nny>C-1) {
				d++;
				continue;
			}
			mapRoom[nx][ny] = mapRoom[nnx][nny];
		}
	}
	
	private static void diffuse() {
		mapWeight = new int[R][C];
		int nx,ny,val;	// val:a/5
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(mapRoom[i][j]>0) {	// 먼지가 있으면 확산시키자
					val = mapRoom[i][j]/5;
					
					for (int k = 0; k < 4; k++) {
						nx = i+dx[k];
						ny = j+dy[k];
						
						// 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
						if(nx<0 || nx>=R || ny<0 || ny>=C) continue;
						if(mapRoom[nx][ny] == -1) continue;
						
						mapWeight[nx][ny] += val;
						mapRoom[i][j] -= val;
					}
				}
			}
		}
		
		// 양쪽 배열에 있는 먼지 합산
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(mapWeight[i][j]>0) mapRoom[i][j] += mapWeight[i][j];
			}
		}
	}
	
	
}
