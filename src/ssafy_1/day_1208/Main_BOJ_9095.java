package day_1208;

import java.util.Scanner;

public class Main_BOJ_9095 {
	static int T, n, cnt;
	static int[] nums;
	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		while(T-->0) {
			n = sc.nextInt();
			cnt = 0;
			
			getCount(0);
			
			builder.append(cnt+"\n");
		}
		System.out.println(builder.toString());
	}
	private static void getCount(int sum) {
		if(sum>n) return;
		if(sum==n) {
			cnt++;
			return;
		}
		
		for (int i = 1; i < 4; i++) {
			getCount(sum+i);
		}
	}
}
