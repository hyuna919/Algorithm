package day_0915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_swea_D4_3124{
	static class Edge implements Comparable<Edge>{
		int start, end, w;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.w = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
		
	}
	
	static int[] parents;
	
	private static void make() {
		parents = new int[V+1];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a){
		if(a==parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		
		parents[b] = a;
		return true;
	}
	
	static int V, E;
	static Edge[] edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			token = new StringTokenizer(in.readLine());
			
			V = Integer.parseInt(token.nextToken());
			E = Integer.parseInt(token.nextToken());

			edgeList = new Edge[E];
			
			int start, weight, end;
			for (int i = 0; i < E; i++) {
				token = new StringTokenizer(in.readLine()," ");
				start = Integer.parseInt(token.nextToken());
				end = Integer.parseInt(token.nextToken());
				weight = Integer.parseInt(token.nextToken());
				edgeList[i] = new Edge(start, end, weight);
			}
			
			Arrays.sort(edgeList);
			
			make();
			
			int cnt = 0;
			long res=0;
			for (Edge e : edgeList) {
				if(union(e.start, e.end)){
					res += e.w;
					if(++cnt == V-1) break;
				}
			}
			
			System.out.println("#" + t+ " " +res);
		}
		
		
		
	}
}