package day_0817;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_jungol_1828 {

	static class Temp implements Comparable<Temp>{
		int min, max;

		public Temp(int min, int max) {
			super();
			this.min = min;
			this.max = max;
		}

		@Override
		public int compareTo(Temp o) {
			int res = max - o.max;
			if(res == 0) res = min - o.min;
			return res;
		}		
	}
	
	static int N, cnt;
	static ArrayList<Temp> arr ;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		N = Integer.parseInt(in.readLine());
		
		arr = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			arr.add(new Temp(Integer.parseInt(token.nextToken()),
								Integer.parseInt(token.nextToken())
							));
		}
		Collections.sort(arr);
		
		////////////////////////////////////
		cnt = 1;
		int max = arr.get(0).max;
		int min = arr.get(0).min;
		Temp now;
		for (int i = 0; i < N; i++) {
			now = arr.get(i);
			if(now.min > max) {
				cnt++;
				max = now.max;
				min = now.min;
			}
		}
		/////////////////////////////////////////
		System.out.println(cnt);
		
		

	}

}
