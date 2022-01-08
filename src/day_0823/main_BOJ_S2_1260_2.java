package day_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 인접리스트ver
public class main_BOJ_S2_1260_2{
	static ArrayList<Integer>[] list;
	static int N, M, V, flag;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(token.nextToken())+1;
		int M = Integer.parseInt(token.nextToken());
		int V = Integer.parseInt(token.nextToken());
		flag = 0;
		
		list = new ArrayList[N];
		for (int i = 1; i < N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		int node, ad;
		for (int i = 0; i < M; i++) {
			token = new StringTokenizer(in.readLine());
			node = Integer.parseInt(token.nextToken());
			ad = Integer.parseInt(token.nextToken());
			list[node].add(ad);
			list[ad].add(node);
		}
		
		// 행렬떄는 인덱스 때문에 정렬되어있었는데
		// 인접리스트는 입력순으로 넣은거라 인접노드 정보 정렬이 안되어있다.
		for (int i = 1; i < N; i++) {
			Collections.sort(list[i]);
		}
		
		visited = new boolean[N];
		dfs(V);
		System.out.println();
		Arrays.fill(visited, false);
		bfs(V);
	}

	private static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(v);
		visited[v] = true;
//		flag = flag|1<<v;
		
		while(!q.isEmpty()) {
			v = q.poll();
			System.out.print(v+" ");
			
			int ad;
			for (int i = 0, end = list[v].size(); i < end; i++) {
				ad = list[v].get(i);
//				if((flag&1<<ad)==0) {
//					flag = flag|1<<ad;
				if(!visited[ad]) {
					visited[ad] = true;
					q.offer(ad);
				}
			}
		}
		
	}

	private static void dfs(int v) {
		visited[v] = true;
//		flag = flag|1<<v;
		System.out.print(v+" ");
		
		int ad;
		for (int i = 0; i < list[v].size(); i++) {
			ad = list[v].get(i);
//			if((flag&1<<ad)==0) {
			if(!visited[ad]) {
				dfs(ad);
			}
		}
		
	}
}
