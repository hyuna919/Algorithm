package day_0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_B2_8958 {
	static int T,N,sum;
	static int[] score;
	static String str;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		
		T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			str = in.readLine();
			N = str.length();
			score = new int[N];
			sum = 0;
			
			score[0] = (str.charAt(0)=='O')?1:0;
			for (int i = 1; i < N; i++) {
				if(str.charAt(i)=='X') continue;
				if(score[i-1]==0) {
					score[i] = 1;
					continue;
				}
				score[i] = score[i-1]+1;
			}
			for (int i = 0; i < N; i++) {
				sum += score[i];
			}
			builder.append(sum+"\n");
		}
		System.out.println(builder);
	}
}
