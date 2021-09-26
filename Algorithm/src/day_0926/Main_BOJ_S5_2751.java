package day_0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_S5_2751 {
	static int N;
	static Integer[] input;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader  in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		
		N = Integer.parseInt(in.readLine());
		input = new Integer[N];
		

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(in.readLine());
		}
		
		Arrays.sort(input);
		
		for (int i = 0; i < N; i++) {
			builder.append(input[i]+"\n");
		}
		System.out.println(builder);
		
	}
}
