package day_0820;

import java.util.Arrays;
import java.util.Scanner;

public class Main_BOJ_2309_일곱난쟁이 {
	static int N = 9, R=7;
	static int[] dwarf, res;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		dwarf = new int[N];
		res = new int[R];
		for (int i = 0; i < N; i++) {
			dwarf[i] = sc.nextInt();
		}
		
		comb(N, R, 0);
	}
	private static void comb(int n, int r, int sum) {
		if(r==0) {
			if(sum == 100) {
				for (int i = 0; i < R; i++) {
					System.out.println(res[i]);
				}
			}
			return;
		}
		
		if(n<r) return;
		if(sum>=100) return;
		
		res[r-1] = dwarf[n-1];
		comb(n-1, r-1, sum+dwarf[n-1]);
		comb(n-1, r, sum);
		
	}

}
