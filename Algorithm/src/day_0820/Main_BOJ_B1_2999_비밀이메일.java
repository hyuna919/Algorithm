package day_0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_B1_2999_비밀이메일 {
	static char[] arr;
	static char[][] map;
	static int N, R=1, C;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		
		arr = in.readLine().toCharArray();
		N = arr.length;
		
		// R,C구하기
		int n = (int)Math.sqrt(N)+1;
		for (int r = 1; r < n; r++) {
			// N을 r로 나눠서 떨어지면 c를 구할 수 있고, 이게 기존 r값보다 크면
			if(N%r == 0) {
				R = r;
				C = N/r;
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = i; j < N; j+=R) {
				builder.append(arr[j]);
			}
		}
		
		System.out.println(builder);
	}
}
