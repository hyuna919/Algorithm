package day_0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B1_3985_롤케이크 {
	static int L, N, maxPerson1, maxValue1, maxPerson2, maxValue2;
	static int[] cake;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		L = Integer.parseInt(in.readLine())+1;
		N = Integer.parseInt(in.readLine())+1;
		
		cake = new int[L];
		
		int s, e, cnt;
		for (int n = 1; n < N; n++) {
			token = new StringTokenizer(in.readLine());
			s= Integer.parseInt(token.nextToken());
			e= Integer.parseInt(token.nextToken());

			// 많이 먹을 것 같은 사람 
			if(e-s >maxValue1) {
				maxValue1 = e-s;
				maxPerson1 = n;
			}
			// 케이크 표시
			cnt = 0;
			while(!(s>e)) {
				if(cake[s] == 0 ) {
					cake[s] = n;
					cnt++;
				}
				s++;
			}
			// 실제로 많이 먹은 사람
			if(cnt >maxValue2) {
				maxValue2 = cnt;
				maxPerson2 = n;
			}
		}

		System.out.println(maxPerson1);
		System.out.println(maxPerson2);
	}
}
