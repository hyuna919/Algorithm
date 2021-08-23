package day_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_G5_1759 {
	static int L, C;
	static String  mo ="aeiou";
	static String[] input, output;
	static StringBuilder builder;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		builder = new StringBuilder();
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		L = Integer.parseInt(token.nextToken());
		C = Integer.parseInt(token.nextToken());

		input = new String[C];
		output = new String[L];
		token = new StringTokenizer(in.readLine());
		for (int i = 0; i < C; i++) {
			input[i] = token.nextToken();
		}
		Arrays.sort(input);
		
		comb(0,0);
		System.out.println(builder);
	}
	private static void comb(int n, int r) {
		if(r==L) {
			int cnt = 0;
			String str = "";
			for (int i = 0; i < L; i++) {
				str = str+output[i];
				if(mo.contains(output[i])) cnt++;
			}
			if(cnt!=0 && L-cnt>1) builder.append(str+ "\n");
			return;
		}
		
		if(C<=n) return;
		
		output[r] = input[n++];
		comb(n, r+1);
		comb(n, r);
	}
}
