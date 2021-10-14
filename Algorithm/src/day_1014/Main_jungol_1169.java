package day_1014;

import java.io.IOException;
import java.util.Scanner;

public class Main_jungol_1169 {
	static int N,M;
	static int[] out;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in); 
		N = sc.nextInt();
		M = sc.nextInt();
		
		out = new int[N];
		
		if(M==1) combR(6, 0);
		else if(M==2) comb(1,0);
		else perm(6,0,0);
	}
	private static void perm(int n, int r, int flag) {
		if(r==N) {
			for (int i = 0; i < N; i++) {
				System.out.print(out[i]+" ");
			}
			System.out.println();
			return;
		}
		
		if(n<r) return;
		
		for (int i = 1; i < 7; i++) {
			if((flag&1<<i)==0) {
				out[r]=i;
				perm(n,r+1,flag|1<<i);
			}
		}
		
	}
	private static void comb(int start, int r) {
		if(r==N) {
			for (int i = 0; i < N; i++) {
				System.out.print(out[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for (int i = start; i < 7; i++) {
			out[r] = i;
			comb(i,r+1);
		}
	}
	private static void combR(int n, int r) {
		if(r==N) {
			for (int i = 0; i < N; i++) {
				System.out.print(out[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 1; i < 7; i++) {
			out[r]=i;
			combR(n,r+1);
		}
		
	}

}
