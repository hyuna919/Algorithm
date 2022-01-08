package day_0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_BOJ_B2_2605 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Integer> list = new LinkedList<Integer>();
		StringBuilder builder = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		token.nextToken();
		list.addFirst(1);
		
		for (int i = 2; i < N+1; i++) {
			list.add(i-Integer.parseInt(token.nextToken())-1, i);
		}
		
		for (int i = 0; i < N; i++) {
			builder.append(list.get(i)).append(" ");
		}
		
		System.out.println(builder);
	}
}