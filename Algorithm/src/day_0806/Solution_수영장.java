package day_0806;

import java.util.Scanner;
import java.util.Stack;

public class Solution_수영장 {
	public static void main(String[] args) {
		int T;
		int [] ticket;
		int [] price;
		Stack<int[]> stack = new Stack<>();
		int min;
		int [] sum; // [0]: 달, [1]: 이달까지의 가격합
		
	
		Scanner in = new Scanner(System.in);
		
		T = in.nextInt();
		ticket = new int[4];
		price = new int[13];
		
		for (int t = 1; t <T+1; t++) {
			
			// 이용권 가격
			for (int i = 0; i < 4; i++) {
				ticket[i] = in.nextInt();
			}
			min = ticket[3];		// 최소값을 1년 이용권 가격으로
			
			// 이용 계획
			int m;
			for (int i = 1; i < 13; i++) {
				// 월간 일 vs 월 이용권 가격 비교해서 해당 월에 뭘 쓸건지 결정	
				m = in.nextInt() ;
				price[i] = ((m* ticket[0])<ticket[1])?(m* ticket[0]):ticket[1];
			}
			
			stack.push(new int[] {0,0});
			while(!stack.isEmpty()) {
				sum = stack.pop();
				// 마지막 달까지 다 계산했으면 최소가격 비교
				if(sum[0]>12) {
					min = Math.min(sum[1], min);
					continue;
				}
				
				// 이달에 이용했다면...가격이 0이 아니니까
				if(price[sum[0]]!=0) {
					stack.push(new int[] {sum[0]+1, sum[1]+price[sum[0]]});
					stack.push(new int[] {sum[0]+3, sum[1]+ticket[2]});
					
				}else {
					sum[0]+=1;
					stack.push(sum);
				}
				
				
				
				
				
			}
			
			
			
			
			System.out.printf("#%d %d%n", t, min);
		}
		
	}
}
