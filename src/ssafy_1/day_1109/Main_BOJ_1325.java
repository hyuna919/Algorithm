package day_1109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1325 {
	static int N,M;
	static boolean[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		map = new boolean[N+1][N+1];
		
		int A,B;
		for (int i = 0; i < M; i++) {
			token = new StringTokenizer(in.readLine());
			A = Integer.parseInt(token.nextToken());
			B = Integer.parseInt(token.nextToken());
			
			map[B][A] = true;
		}
	
//		for (int i = 1, end = N+1; i < end; i++) {
//			map[i][i] = true;
//		}
		 
		List<Integer> list = new ArrayList<Integer>();;
		int max = 0, cnt;
		for (int i = 1, end = N+1; i < end; i++) {
			cnt = bfs(i);
			if(cnt>max) {
				list = new ArrayList<Integer>();
				list.add(i);
				max = cnt;
			}else if(cnt==max) {
				list.add(i);
			}
		}
		
		Collections.sort(list);
		
		StringBuilder builder = new StringBuilder();
		for (Integer integer : list) {
			builder.append(integer+" ");
		}
		System.out.println(builder);
	}
	
	private static int bfs(int i) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i);
		int flag = 0;
		flag |= 1<<i;
		int cnt = 1;
		
		int now;
		while(!q.isEmpty()) {
			now = q.poll();
			for (int j = 1, end=N+1; j < N; j++) {
				if((flag&1<<j)==0 && map[now][j]) {
					cnt++;
					q.offer(j);
					flag |= 1<<i;
				}
			}
		}
		return cnt;
	}
	
	

}
