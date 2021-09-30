package day_0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 *  키정렬
 *  
 *  N*2의 배열 만들어서 n입장에서 작은/큰 노드를 0/1열에 비트연산으로 넣고(나머지연산하면서)
 *  마지막에 이 두 열의 합을  2^N과 비교하면 되지 않을까
 *  
 * 
 */
public class Solution_swea_D4_5643 {
	static int T,N,M,res;
	static ArrayList<Integer>[] big, small;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		
		T = Integer.parseInt(in.readLine())+1;
		
		for (int t = 1; t < T; t++) {
			res = 0;
			N = Integer.parseInt(in.readLine());
			M = Integer.parseInt(in.readLine());
			
			big = new ArrayList[N+1];
			small = new ArrayList[N+1];
			
			for (int i = 0, end =N+1; i < end; i++) {
				big[i] = new ArrayList<Integer>();
				small[i] = new ArrayList<Integer>();
			}			
			
			int s,b;
			for (int i = 0; i < M; i++) {
				token = new StringTokenizer(in.readLine());
				s = Integer.parseInt(token.nextToken());
				b = Integer.parseInt(token.nextToken());
				small[b].add(s);	// 작
				big[s].add(b);
			}

			for (int i = 1, end = N+1; i < end; i++) {
				if(bfs(i) == N-1) res++;				
			}
			
			builder.append("#"+t+" "+res+"\n");
		}
		System.out.println(builder);
	}

	private static int bfs(int input) {
		Queue<Integer> q = new LinkedList<Integer>();
		visited = new boolean[N+1];
		visited[input] = true;
		int cnt = 0;
		ArrayList<Integer> list;

		q.offer(input);
		int cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			list = big[cur];
			for (int i = 0, end = list.size(); i < end; i++) {
				if(visited[list.get(i)]) continue;
				visited[list.get(i)] = true;
				cnt++;
				q.offer(list.get(i));
			}
		}

		q.offer(input);
		while(!q.isEmpty()) {
			cur = q.poll();
			list = small[cur];
			for (int i = 0, end = list.size(); i < end; i++) {
				if(visited[list.get(i)]) continue;
				visited[list.get(i)] = true;
				cnt++;
				q.offer(list.get(i));
			}
		}
		return cnt;
	}
}
