package day_1028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_S3_14501 {
	static int N, max;
	static int[][] list;
	static boolean[] isOver;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine())+1;
		list = new int[2][N];
		isOver = new boolean[N];
		
		for (int day = 1; day < N; day++) {
			token = new StringTokenizer(in.readLine());
			list[0][day] = Integer.parseInt(token.nextToken());
			list[1][day] = Integer.parseInt(token.nextToken());
		}
		
		// 1. 일정 보면서 상담할 수 없는 날짜 체크
		for (int day = 1; day < N; day++) {
			if(list[0][day]+day-1 >= N) isOver[day] = true;
		}
		
		// 2. 경우의 수 
		comb(1,0);
		
		System.out.println(max);
	}
	private static void comb(int day, int total) {
		max = (max<total)?total:max;
		
		for (int i = day; i < N; i++) {
			if(isOver[i]) continue;	// 상담일자가 넘으면 패스
			comb(i+list[0][i], total+list[1][i]);
		}
	}

}
