package day_0914;

import java.util.Scanner;

public class Main_Jongol_1411 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] tile = new int[N+1];
		tile[0] = 1;
		tile[1] = 1;
		for (int i = 2; i <= N; i++) {
			tile[i] = (tile[i-1] + tile[i-2]*2)%20100529;
		}
		
		System.out.println(tile[N]);
	}
}
