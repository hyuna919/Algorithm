package day_0817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BOJ_1931 {
	static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Meeting o) {
			int time = end - o.end;
			if(time==0) time = start - o.start;
			return time;
		}		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		ArrayList<Meeting> list = new ArrayList<>();
		
		StringTokenizer token;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			list.add(new Meeting(	Integer.parseInt(token.nextToken()),
									Integer.parseInt(token.nextToken())
					));
		}
		
		Collections.sort(list);
		
		int cnt = 1;
		int end = list.get(0).end;
		for (int i = 1; i < N; i++) {
			if(end <= list.get(i).start) {
				end = list.get(i).end;
				cnt ++;
			}
		}
		
		System.out.println(cnt);
		
		

	}

}
