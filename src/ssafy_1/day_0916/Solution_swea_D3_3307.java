package day_0916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_swea_D3_3307 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		StringBuilder builder = new StringBuilder();
		
		int N, max;
		int[] arr, LIS;
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine());
			arr = new int[N];
			LIS = new int[N];
			
			token = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(token.nextToken());
			}
			
			max=0;
			for (int i = 0; i < N; i++) {
				LIS[i]=1;
				for (int j = 0; j < i; j++) {
					if(arr[i]>arr[j] && LIS[j]+1 > LIS[i]) LIS[i] = LIS[j]+1;
				}
				max = Math.max(max, LIS[i]);
			}			
			builder.append("#"+t+" "+max+"\n");
		}
		System.out.println(builder);

	}
}
