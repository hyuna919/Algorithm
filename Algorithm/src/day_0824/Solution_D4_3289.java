package day_0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3289 {
	static int[] p;
	private static void make() {
		p = new int[N+1];
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
	}
	
	private static int find(int a) {
		if(p[a] == a) return a;
		else return p[a] = find(p[a]);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return;
		p[a] = b;
		return;
	}
	
	
	
	static int T,N,M, a,b,c;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		
		T = Integer.parseInt(in.readLine())+1;
		for (int t = 1; t < T; t++) {
			builder1 = new StringBuilder();
			token = new StringTokenizer(in.readLine());
			N = Integer.parseInt(token.nextToken());
			M = Integer.parseInt(token.nextToken());
			
			make();
			
			for (int m = 0; m < M; m++) {
				token = new StringTokenizer(in.readLine());
				c= Integer.parseInt(token.nextToken());
				a= Integer.parseInt(token.nextToken());
				b= Integer.parseInt(token.nextToken());
				
				if(c==0) {		//union
					union(a,b);
				}else if(c==1) {	//find
					if(find(a) == find(b)) builder1.append(1);
					else builder1.append(0);
				}
			}
			
			builder2.append("#").append(t).append(" ");
			builder2.append(builder1+"\n");
		}
		System.out.println(builder2);
	}

}
