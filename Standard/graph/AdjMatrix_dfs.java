package com.ssafy.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AdjMatrix_dfs {
	private static int[][] map;				//인접 행렬 정보
	private static boolean[] visited;		//Node에 대한 방문 여부 
	private static int n;					//Node 수
	
	public static void main(String[] args) throws Exception{
		 System.setIn(new FileInputStream("res/lecture/AdjMatrix.txt"));
		 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		 
//		 1. 입력 처리 
		 int T = Integer.parseInt(in.readLine());
		 for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(in.readLine());		// 전체 노드 수 입력 받기 
			
//			배열들을 생성 
			map = new int[n][n];
			visited=new boolean[n];
			
//			인접 행렬 정보 입력 받기 
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			그래프 탐색
			dfs(0);
			System.out.println();
			
		}
	}

	private static void dfs(int cur) {
		//현재 노드에 도착하면 방문 처리 
		visited[cur] = true;
		System.out.printf("%c=>",cur+'A');
		
		//인접된 노드를 순회
		for (int ad = 0; ad <n; ad++) {
			//  인접 되어 있고                      방문 하지 않은 노드라면
			if(map[cur][ad]==1  && !visited[ad]) {
				dfs(ad);
			}
		}
		
	}
}









