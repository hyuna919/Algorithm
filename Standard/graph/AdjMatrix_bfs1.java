package com.ssafy.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AdjMatrix_bfs1 {
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
			bfs(0);
			System.out.println();
			
		}
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
				if(map[cur][adj] == 1 && !visited[adj]) {
//					방문하기 위해 queue에 넣기 
					queue.offer(adj);
					visited[adj] = true;		//미리 방문 했다고 표시하기 
				}
			}
		}
	}
}



