package day_0817;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_jungol_2247_조현아 {
	static int N, maxUse, maxEmpty;
	static class Time implements Comparable<Time>{
		int start, end;

		public Time(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Time o) {
			int res = start - o.start;
            if(res==0) res = end - o.end;
            return res;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		ArrayList<Time> list = new ArrayList<>();
		StringTokenizer token;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			list.add(new Time(Integer.parseInt(token.nextToken()),
						Integer.parseInt(token.nextToken())
					));
		}
		
		Collections.sort(list);
		
		Time now = list.get(0);
		int start = now.start, end=now.end;
        for (int i = 0; i <N; i++) {
        	now = list.get(i);
            
        	// 연속됨
        	if(now.start <= end) {
        		if(end <now.end) end = now.end;
            	maxUse = Math.max(maxUse, end-start);
        	}else {	// 끊김
        		maxEmpty = Math.max(maxEmpty, now.start - end);
        		
        		start = now.start;
        		end = now.end;
        	}
        }
        System.out.println(maxUse + " " + maxEmpty);
		
	}
}
