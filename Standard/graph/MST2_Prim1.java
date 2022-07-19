package com.ssafy.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 시간 복잡도   V^3
 */
public class MST2_Prim1 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("res/MSTPrim.txt"));
		int T = Integer.parseInt(in.readLine().trim());
		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.parseInt(in.readLine().trim());
			int[][] map = new int[N][N];
			boolean[] visited=new boolean[N];
			StringTokenizer tokens;
			
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			//선택한 정점을 저장하는 배열 
			ArrayList<Integer> selected = new ArrayList<Integer>(N);
			int index;
			int min;
			selected.add(0);		//0번 정점부터 시작
			visited[0] = true;
			int result = 0;
			for (int i = 0; i < N-1; i++) {
				min = Integer.MAX_VALUE;
				index = 0;					//가중치가 가장 작은 정점의 값
				for (Integer v : selected) {
					//선택된 정점에서 갈수 있는 모든 정점의 가중치를 비교해서 최소가 되는 정점을 찾기 
					for (int j = 0; j < N; j++) { //인접된 정점 탐색
						// 정점이 인접되어 있고    인접된 정점이 방문하지 않은 정점이면서  가중치가 작다면  
						if(map[v][j]!=0 && !visited[j] && map[v][j] <min) { 
							min = map[v][j];		//새로운 최소값이므로  임시 선택
							index = j;				//최소값에 해당하는 정점을 기억
						}
					}
				}
				result += min;						//최소의 합을 구하기
				selected.add(index);				//선택한 최저 가중치에 해당하는 정점을 추가 
				visited[index] = true;				//선택했기 때문에 방문 표시 
			}
			System.out.println("#"+testcase+" "+result);
			System.out.println(selected);
		}
	}
}