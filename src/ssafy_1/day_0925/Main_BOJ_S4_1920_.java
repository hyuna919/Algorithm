package day_0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_S4_1920_ {
	static int N, M, flag;
	static int[] text, find;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		StringBuilder builder = new StringBuilder();
		
		N = Integer.parseInt(in.readLine());
		text = new int[N];
		token = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			text[i] = Integer.parseInt(token.nextToken());
		}
		
		M = Integer.parseInt(in.readLine());
		find = new int[M];
		token = new StringTokenizer(in.readLine());
		for (int i = 0; i < M; i++) {
			find[i] = Integer.parseInt(token.nextToken());
		}
		
		// text를 크기순으로 정렬
		Arrays.sort(text);
		
		// 탐색
		for (int num : find) {
			flag = 0;
			builder.append(binarySearch(num));
			builder.append("\n");
		}
		System.out.println(builder);
	}

	private static int binarySearch(int key) {
		int start = 0, end = N-1, mid;
		while(start<=end) {
			mid = (start+end) /2;
			if(text[mid] == key) return 1;
			else if(text[mid] < key) start = mid+1;
			else end = mid-1;
		}
		return 0;
	}
}
