package day_0813;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_S1_2961_조현아 {
	static int N, min = Integer.MAX_VALUE;
	static int[] subset;
	static int[][] inputs;	// 0 신맛(곱) 1 쓴맛(합)
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		
		inputs = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < 2; j++) {
				inputs[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		subset();
		System.out.println(min);
	}
	
	private static void subset() {
		subset = new int[N];

		for (int r = 1, end =1<<N; r < end; r++) {	//부분집합 수: 0부터 2의 N승 -1까지
			//i가 subset이므로 i를 이진수로 표현해서 0이면 비선택, 1이면 선택
			for (int j = 0; j < N; j++) {
				if((r & 1<<j)!=0) {
					subset[j] = 1;
				}
			}
			
			print(subset);
			Arrays.fill(subset, 0);	//////////중요
		}	
	}
	
	private static void print(int[] subset) {
		int k = 0;
		int salty = 1;
		int bitter =0;
		for (int s : subset) {
			if(s!=0) {
				salty *= inputs[k][0];
				bitter += inputs[k][1];
			}
			k++;
		}
		min = Math.min(min, Math.abs(salty-bitter));
	}
}