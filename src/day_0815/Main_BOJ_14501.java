package day_0815;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main_BOJ_14501 {
	static int N, maxPay = 0, tmp;
	static int[] T, P;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		
		T = new int[N];
		P = new int[N];
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			tmp = Integer.parseInt(token.nextToken());
			if(tmp>(N-i)) continue;		// 만약 남은 일자보다 상담일이 길면 아에 안받겠다.
			T[i] = tmp;
			P[i] = Integer.parseInt(token.nextToken());
		}
		

//		if(IntStream.of(T).sum() == N) maxPay = IntStream.of(P).sum();	//  모든 일자가 1일때 걸림
//		else subset(0,0,0,0);
		
		subset(0,0,0,0);
		
		System.out.println(maxPay);

	}
	private static void subset(int start, int now, int pay, int day) {
		// 기저조건
		if(now > N || day>N) {
			return;
		}

		maxPay = Math.max(maxPay, pay);
		
		for (int i = start; i < N; i++) {
			tmp = (T[i]!=0)?T[i]:1;		// 해당일의 상담일이 0이 아니면 상담일수, 0이면 1넣겠다.
			subset(i+tmp, now+tmp, pay+P[i], day+tmp);
		}
	}
}


