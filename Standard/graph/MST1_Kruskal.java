package com.ssafy.graph;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;
 
// 크루스칼 알고리즘이용
/*
 * 크루스칼 알고리즘이용
 * 1. makeSet()		==> 시간 복잡도   O(v)
 * 2. Edge정렬 		==> 시간 복잡도   O(ElogE)
 * 3. union			==> 시간 복잡도   O(E)
 * 
 * O(v+ElogE+E)  => O(ElogE)
 * 
정점수 간선수
시작정점 끝정점 가중치
5 10
0 1 5
0 2 10
0 3 8
0 4 7
1 2 5
1 3 3
1 4 6
2 3 1
2 4 3
3 4 1

==>10
----------------------------------

7 11
0 1 3
0 2 17
0 3 6
1 3 5
1 6 12
2 4 10
2 5 8
3 4 9
4 5 4
4 6 2
5 6 14

==>31

 */
/**
 * @author THKim
 */
public class MST1_Kruskal{
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int weight;
		
		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", weight="
					+ weight + "]";
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static Edge[] edgeList;
	static int[] parents;
	static int V,E;
	
	public static void make() {
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	public static int find(int a){
		if(parents[a] == a) return a;// 자신이 루트이면 그냥 자신의 번호 리턴
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a,int b){
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		
		// 두 노드의 root가 다르면 합침
		parents[bRoot] = aRoot;
		return true;
	}	
	
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parents = new int[V];
        edgeList = new Edge[E];
        
        for(int i=0; i<E; ++i){
        	st = new StringTokenizer(br.readLine().trim());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int weight = Integer.parseInt(st.nextToken());
  
	        edgeList[i] = new Edge(from ,to, weight);

        }//간선 저장
        
        make();

        // 간선비용이 작은 순서대로 정렬
        Arrays.sort(edgeList);
        int result = 0;
        int count=0;// 연결 간선수
	    for(Edge edge : edgeList){
	    	if(union(edge.start,edge.end)){ // 싸이클이 발생하지 않았으면
	    		result += edge.weight;
	    		if(++count == V-1){ // 연결 간선수가 정점수-1이면 다 연결한 것임.
	    			break;
	    		}
	    	}
	    }
        System.out.println(result);
    }
}// end class
