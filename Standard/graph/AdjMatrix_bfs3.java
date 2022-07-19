package com.ssafy.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AdjMatrix_bfs3 {
	private static int[][] map;				//인접 행렬 정보
	private static boolean[][] visited;		//Node에 대한 방문 여부 
	private static int n;					//Node 수
//							     상  하 좌  우
	private static int[] dr= {-1,1,0 ,0};
	private static int[] dc ={0, 0,-1,1};
	
	private static int sr, sc, er, ec;
	public static void main(String[] args) throws Exception{
		 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		 
//		 1. 입력 처리 
		 n = Integer.parseInt(in.readLine());		// 전체 노드 수 입력 받기 
//			배열들을 생성 
		 map = new int[n][n];
		 visited=new boolean[n][n];
		
		 StringTokenizer st = new StringTokenizer(in.readLine()," ");
		 sr = Integer.parseInt(st.nextToken());
		 sc = Integer.parseInt(st.nextToken());
		 er = Integer.parseInt(st.nextToken());
		 ec = Integer.parseInt(st.nextToken());
		
//			그래프 탐색
		 bfs();
		 System.out.println();
	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<int[]>();   //탐색할 노드를 담을 queue
		
//		1. 첫 방문한 node를 queue에 담기 
		queue.offer(new int[] {sr,sc});
//		2. queue에 담은 node를 방문 처리 
		visited[sr][sc] = true;
		
		
//		3. 탐색 시작 
		//queue에 방문할 node를 담기 때문에 queue가 empty라는것는 모든 노드를 다 방문 했다는 의미,  empty가 아니면 계속 방문을 해야 한다. 
		top:
		while(!queue.isEmpty()) {    
			int[] cur = queue.poll();			//현재 방문할 노드를 queue에서 꺼내 온다 
			int r = cur[0];
			int c = cur[1];
			
//			인접한 node를 방문
			for (int i = 0; i <4; i++) {
				int nr = r+dr[i];		
				int nc = c+dc[i];
				
//				경계 검사   && 인접한 node가 도작 위치 확인
				if(nr>-1 && nr<n && nc>-1 && nc<n && !visited[nr][nc] ) {
					//인접 노드가 도착 위치인지 확인
					if(nr==er && nc==ec) {
						System.out.printf("%d %d 위치에 도착 했다~~~%n",nr, nc);
						break top;
					}else {  //인접 노드가 도착 위치가 아니라면 계속 탐색해야 하는 노드 
						queue.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			}
		}
	}
}






