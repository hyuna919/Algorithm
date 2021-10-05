package day_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_15961 {
	static int N, d, k, coupon;
	static int[] belt, sushi;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken());
		d = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());
		coupon = Integer.parseInt(token.nextToken());
		belt = new int[N];
		sushi = new int[d+1];
		
		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(in.readLine());
		}
		
		int max = 0;
		
		int cnt = 0;
		// 쿠폰추가
		sushi[coupon] = 1;
		cnt++;
		
		// 초기화_첫 k-1개 미리 넣어둠
		for (int i = 0, end = k-1; i < end; i++) {
			if(sushi[belt[i]]==0) cnt++;
			sushi[belt[i]]++;
		}
		
		// 슬라이딩 윈도우
		for (int i = k-1, end=N+k-1; i < end; i++) {
			// 뒤에꺼 하나 추가
			if(i>=N) {
				if(sushi[belt[i-N]]==0) cnt++;
				sushi[belt[i-N]]++;
			}else {
				if(sushi[belt[i]]==0) cnt++;
				sushi[belt[i]]++;
			}
			
			// 최댓값 갱신
			max = Math.max(max, cnt);
			
			// 첫 접시 제거
			sushi[belt[i-k+1]]--;
			if(sushi[belt[i-k+1]]==0) cnt--;

		}
		
		System.out.println(max);
	}

}
