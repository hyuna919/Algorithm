package com.ssafy.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dijkstra {

	public static void main(String[] args) throws Exception	{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		1.데이타 입력 및 초기화 
		
		int V = Integer.parseInt(in.readLine());
		int[][] map = new int[V][V];			//정점에 대한 정보를 인접 행렬로 선언 
		boolean[] visited=new boolean[V]; 		//정점에 대한 방문 처리를 위한 배열
		int[] distance = new int[V];			//정점간의 최소 비용을 저장할 배열 
		
//		시작 정점 
		int start = 0;
//		도작 정점
		int end = V-1;
		
//		정점에 대한 인접 정보 입력 받기
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine()," ");
			for (int j = 0; j < V; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		정점의 최소 비용을 갱신하기 위해 초기 값을 max로 설정한다. 
		Arrays.fill(distance, Integer.MAX_VALUE);
//		시작 정점의 비용을 0으로 설정
		distance[start] = 0;
		
//		2.각 정점 수만큼 반복해서 최소 비용을 찾기 
		int min= 0, cur = 0;
		for (int i = 0; i < V; i++) {
			min = Integer.MAX_VALUE;
//			2.1 방문하지 않은 정점 중에서 최소 비용의 정점을 선택  ==> 처음에서는 start로 지정한 정점을 선택 
			for (int j = 0; j < V; j++) {
				if(!visited[j] && min > distance[j]){
					min= distance[j];
					cur = j;
				}
			}
			
//			2.2 최소 비용으로 선택된 정점을 방문 처리 
			visited[cur] = true;
			
//			2.3  기존의 비용과  선택한 정점(경유지)을 통해 가는 비용 중 더 적은 비용으로 갱신  
			for (int j = 0; j < V; j++) {
//				    방문하지 않은 정점 중에             인접되어 있어야 하고        기존의 비용보다   선택한 정점을 통해 가는 비용이 더 저렴하면 
				if(!visited[j]  && map[cur][j] !=0 && distance[j] >min+map[cur][j]) {
//					기존의 비용을 경유해서 가는 비용으로 갱신
					distance[j] = min+map[cur][j]; 
				}
			}
			
		}
		System.out.println(Arrays.toString(distance));
		System.out.println(distance[end]);
	}
	static String input="5\r\n" + 
			"0 2 2 5 9\r\n" + 
			"2 0 3 4 8\r\n" + 
			"2 3 0 7 6\r\n" + 
			"5 4 7 0 5\r\n" + 
			"9 8 6 5 0";
}






