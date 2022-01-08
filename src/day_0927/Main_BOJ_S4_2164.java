package day_0927;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_BOJ_S4_2164 {
	static int N, last;
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		
		for (int i = 1, end=N+1; i < end; i++) {
			q.offer(i);
		}
		

		while(!q.isEmpty()) {
			last = q.poll();
			if(!q.isEmpty()) {
				last = q.poll();
				q.offer(last);
			}
		}
		
		System.out.println(last);
	}
}
