package graph.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
public class MST2_Prim2{
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
        
        int minVertex,min,result = 0;
        minEdge[0] = 0; // 임의의 시작점 비용 0 셋팅
        
		for(int c = 0 ; c< N; c++){// 모든 정점 수만큼 반복
            min = Integer.MAX_VALUE;// 초기값으로 정수의 최대치를 주고 시작
            minVertex = 0;			// 최소 값을 선택된 정점
            
            for(int i=0; i<N; ++i) { 
            	//아직 선택하지 않은 정점중에 최소 값의 정점을 선택
            	if(!visited[i] &&  min > minEdge[i] ) {
            		min = minEdge[i];			
            		minVertex = i;
            	}
            }	
            
            result += min; 
            visited[minVertex] = true; 
//          선택한 정점에서 출발하는 모든 간선의 대한 비용을 업데이트   
            for (int i = 0; i < N; i++) { 
                if (!visited[i] && input[minVertex][i] != 0 &&  minEdge[i] > input[minVertex][i]  ) {
                	minEdge[i] = input[minVertex][i];
                }
            }
        }
        System.out.println(result);
    }
}// end class
