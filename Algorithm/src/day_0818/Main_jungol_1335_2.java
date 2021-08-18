package day_0818;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_jungol_1335_2 {
	static int T, N, min;
	static int[] A, B, check;
	static boolean[] isSelected;
	static int[][] S;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		StringBuilder builder = new StringBuilder();
		
		T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t < T+1; t++) {
			N = Integer.parseInt(in.readLine());
			
			S = new int[N][N];
			// 시너지 테이블 입력
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			
			A = new int[N/2];
			B = new int[N/2];
			isSelected = new boolean[N];
			
			min = Integer.MAX_VALUE;
			comb(N, N/2);
		
			builder.append("#"+t +" " + min +"\n");
		}
		
		System.out.println(builder);
		
	}
	private static void comb(int n, int r) {
		if(r==0) {
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if(!isSelected[i]) B[idx++] = i;
			}

			
			// 표에서 해당 조합에 해당하는 값을 모두 더하자
			// min = Math.min(min, 두합의 차)
			int sum1 = calSynergy(A);
			int sum2 = calSynergy(B);
			min = Math.min(min,  Math.abs(sum2-sum1));
			return;
		}
		
		if(n<r) return;
		
		A[r-1] = n-1;
		isSelected[n-1] = true;
		comb(n-1, r-1);
		isSelected[n-1] = false;
		comb(n-1, r);
	}
	
	
	private static int calSynergy(int [] a) {
		int sum = 0;
		for (int i : a) {
			for (int j : a) {
				sum += S[i][j];
			}
		}
		
		return sum;
		
	}


}
