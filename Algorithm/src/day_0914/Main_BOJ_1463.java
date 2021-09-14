package day_0914;

import java.util.Scanner;

public class Main_BOJ_1463 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		
		int[] list = new int[X+1];
		list[0] = 0;
		list[1] = 0;
		if(X>=2) list[2] = 1;
		if(X>=3) list[3] = 1;
		
		for (int i = 4; i <= X; i++) {
			int min = Integer.MAX_VALUE;
			if(i%3==0) min = Math.min(min, list[i/3]);
			if(i%2==0) min = Math.min(min, list[i/2]);
			min = Math.min(min, list[i-1]);
			
			list[i] = min+1;
		}
		System.out.println(list[X]);
	}

}
