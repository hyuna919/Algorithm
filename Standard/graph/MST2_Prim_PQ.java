package com.ssafy.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
// 프림 알고리즘이용
/**
5
0 5 10 8 7 
5 0 5 3 6 
10 5 0 1 3 
8 3 1 0 1 
7 6 3 1 0
==>10

7
0 32 31 0 0 60 51
32 0 21 0 0 0 0
31 21 0 0 46 0 25
0 0 0 0 34 18 0
0 0 46 34 0 40 51
60 0 0 18 40 0 0
51 0 25 0 51 0 0
==>175
 */
/**
 * @author THKim
 */
public class MST2_Prim_PQ{
	static class Vertex implements Comparable<Vertex>{
		int no;
		int weight;
		public Vertex(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}
		public int compareTo(Vertex o) {
			return this.weight - o.weight;
		}
	}
	
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int[][] input = new int[N][N];
        boolean[] visited = new boolean[N];
        
//      정점까지 가는 최소 비용을 저장하는 배열 
        int[] minEdge = new int[N];
        
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
            minEdge[i] = Integer.MAX_VALUE;
        }// i노드에서 j노드까지의 비용을 모두 배열에 저장
        
        int start = 0;   		//입력 받은면 됨 
        int result = 0;
        int nodeCount=0;		//vertex가 연결될때마다 count => V-1가 되면 다 연결됨. 
        PriorityQueue<Vertex> queue =new  PriorityQueue<Vertex>();
        
        queue.offer(new Vertex(start, 0));	// 시작 정점을  queue에 담는다. 
        minEdge[start] = 0; 				// 시작 정점은 비용이 없으므로 0값 셋팅
        
		while(!queue.isEmpty()){// 모든 정점 수만큼 반복
			
			Vertex min = queue.poll();		//PriorityQueue이므로 앞으로 가봐야 할 정점 중 최소 비용의 정점이 추출됨. 
            if(visited[min.no]) continue;	//최소 비용의 정점이 방문했던 정점이라면 pass 다음 정점을 처리
            
//          최소 비용의 정점이 방문하지 않은 정점이라면 선택
            result += min.weight; 			//비용  처리
            visited[min.no] = true; 		//방문 처리 
            
            if(++nodeCount == N) break;		//모든 정점이 다 연결 됐다면 중담
            
//          선택한 정점에서 출발하는 모든 간선의 대한 비용을 업데이트   
            for (int i = 0; i < N; i++) { 
//            	     방문하지 않은 정점          인접 되어 있어야 한다. 				 최소 비용
                if (!visited[i] && input[min.no][i] != 0 &&  minEdge[i] > input[min.no][i]  ) {
                	minEdge[i] = input[min.no][i];
                	queue.offer(new Vertex(i, input[min.no][i]));
                }
            }
        }
        System.out.println(result);
    }
}// end class
