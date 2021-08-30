package day_0830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_S1_2527 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		
		int N = 4, gapX, gapY;
		int[] s1 = new int[4];
		int[] s2 = new int[4];
		
		while(N-->0) {
			token = new StringTokenizer(in.readLine());
			for (int i = 0; i < 4; i++) {
				s1[i] = Integer.parseInt(token.nextToken());
			}
			for (int i = 0; i < 4; i++) {
				s2[i] = Integer.parseInt(token.nextToken());
			}
		
			
			gapX = s1[0]<s2[0]?s2[0]-s1[2]:s1[0]-s2[2];
			gapY = s1[1]<s2[1]?s2[1]-s1[3]:s1[1]-s2[3];
		
			
			if(gapX>0 || gapY>0) {
				builder.append("d");	
			}else if(gapX<0){
				if(gapY<0) builder.append("a");
				else builder.append("b");
			}else if(gapY<0) {
				builder.append("b");
			}else if(gapX==0 && gapY==0) {
				builder.append("c");
			}
				
			builder.append("\n");
		}
	
		System.out.println(builder);
	}

}
