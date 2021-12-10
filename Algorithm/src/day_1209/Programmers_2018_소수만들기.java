package day_1209;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 소수 만들기
// 조합 + 소수
// 3개의 서로 다른 수 더해서 소수가 나오는가


public class Programmers_2018_소수만들기 {
	
	static int length, cnt;
	static int[] choosen = new int[3], input;
	public static void main(String[] args) {
        int[] nums = {1,2,7,6,4};
		int res = solution(nums);
		System.out.println(res);
	}
	
	public static int solution(int[] nums) {
		input = nums;
		length = nums.length;
		comb(0, 0, 0);
		
		return cnt;
    }

	private static void comb(int now, int level, int sum) {
		if(level == 3) {
			if(isPrime(sum)) cnt++;
			return;
		}
		
		for (int i = now; i < length; i++) {
			comb(i+1, level+1, sum+input[i]);
		}
	}

	private static boolean isPrime(int sum) {
		
		int end = (int)Math.sqrt((double)sum)+1;
		for (int i = 2; i < end; i++) {
			if(sum%i==0) return false;
		}
		return true;
	}
}
