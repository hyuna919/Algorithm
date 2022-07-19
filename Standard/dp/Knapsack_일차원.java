package dp;

import java.util.Scanner;

/*
 * 
 * 0-1Knapsack
 * W길이 일차원배열로
 * 배열을 뒤에서부터 채운다.
 * 
 */

public class Knapsack_일차원 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int W = sc.nextInt();
		
		int[] weights = new int[N+1];
		int[] profits = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			weights[i] = sc.nextInt();
			profits[i] = sc.nextInt();
		}

		////////////////////////////////////////////////////////////////////
		
		int[] D = new int[W+1];
		
		for (int i = 1; i <= N; i++) {
			for (int w = W; w >= weights[i]; w--) {	// 일차원은 뒤에서부터 채운다
				D[w] = Math.max(D[w], profits[i]+D[w-weights[i]]);
			}
		}
		System.out.println(D[W]);
		
		///////////////////////////////////////////////////////////////////////
	}

}
