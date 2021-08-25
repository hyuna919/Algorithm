package day_0824;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D4_1251 {
	
	static class Vertex implements Comparable<Vertex>{
		int no, x,y;
		double w;

		@Override
		public int compareTo(Vertex o) {
			int res = (int) (w - o.w);
			if(res==0) res = x - o.x;
			if(res==0) res = y-o.y;
			return res;
		}

		public Vertex(int no, int x, int y, double w) {
			super();
			this.no = no;
			this.x = x;
			this.y = y;
			this.w = w;
		}

	}
	
	static int T, N, start, nodeCnt;
	static double[] minEdge;
	static double sum;
	static double E;
	static boolean[] visited;
	static Vertex[] list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer x, y;
		StringBuilder builder = new StringBuilder();

		PriorityQueue<Vertex> q;
		
		T = Integer.parseInt(in.readLine())+1;
		
		for (int t = 1; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			list = new Vertex[N];
			minEdge = new double[N];
			visited = new boolean[N];
			
			
			// 입력
			x = new StringTokenizer(in.readLine());
			y = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				list[i] = new Vertex(i, Integer.parseInt(x.nextToken()), Integer.parseInt(y.nextToken()),0);
				minEdge[i] = Double.MAX_VALUE;
			}
			E = Double.parseDouble(in.readLine());
			
			/* --------------- 핵심파트 -----------------  */
			start = 0;
			nodeCnt = 0;
			sum = 0;
			
			q = new PriorityQueue<Vertex>();
			q.offer(list[start]);
			minEdge[start] = 0;
			
			while(!q.isEmpty()) {
				Vertex min = q.poll();
				if(visited[min.no]) continue;
				
				visited[min.no] = true;
				sum += min.w;
				
				if(++nodeCnt == N) break;
				
				int x1 = min.x, y1 = min.y;
				int x2, y2;
				double w;
				for (int i = 1; i <	N; i++) {
					x2 = list[i].x;
					y2 = list[i].y;
					w = Math.pow(x2-x1,2)+Math.pow(y2-y1,2);
					
					if(!visited[i] && w!=0 &&minEdge[i]>w) {
						minEdge[i] = w;
						q.offer(new Vertex(i, x2, y2, w));
					}
					
				}
			}
			sum *= E;
			builder.append("#").append(t).append(" ").append(Math.round(sum)).append("\n");
		}
		System.out.println(builder);
		
	}

}
