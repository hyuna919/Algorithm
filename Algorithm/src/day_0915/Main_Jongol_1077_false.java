package day_0915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


/*
 * 
 * 틀렸는데요 정답이 떴습니다.
 * 반례
 * 4 3 
	2 40 
	5 110 
	10 200 
	3 50
 * 
 */

class Jewelry implements Comparable<Jewelry>{
	int w,v1,v2;

	public Jewelry(int w, int v1, int v2) {
		super();
		this.w = w;
		this.v1 = v1;
		this.v2 = v2;
	}

	@Override
	public int compareTo(Jewelry o) {
		int res = o.v2-v2;
		if(res==0) res = o.w-w;
		return res;
	}
	
	
}



public class Main_Jongol_1077_false {
	static int N, W;
	static ArrayList<Jewelry> list = new ArrayList<Jewelry>();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken());
		W = Integer.parseInt(token.nextToken());
		
		int w, v1, v2;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			w = Integer.parseInt(token.nextToken());
			v1 = Integer.parseInt(token.nextToken());
			v2 = v1/w;
			
			list.add(new Jewelry(w, v1, v2));
		}
		
		Collections.sort(list);
		
		int total = 0, idx = 0;
		while(W>0) {
			if(idx==N) break;
			if(list.get(idx).w > W) {
				idx++;
				continue;
			}
			W -= list.get(idx).w;
			total += list.get(idx).v1;
		}
		
		System.out.println(total);
	}
}
