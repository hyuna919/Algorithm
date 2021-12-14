package day_1213;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 2019 카카오 블라인드
// 다트게임


public class Kakao2019Blind_다트게임{
	
	static class Score{
		int num, bonus, option;

		public Score(int num, int bonus, int option) {
			super();
			this.num = num;
			this.bonus = bonus;
			this.option = option;
		}
	}
	
	public static void main(String[] args) {
		String dartResult = "1S*2T*3S";
		int res = solution(dartResult);
		System.out.println(res);
	}
	
	public static int solution(String dartResult) {
		int N = dartResult.length();
		char[] arr = dartResult.toCharArray();
		List<Score> list = new ArrayList<Score>();
		
		int num, bonus=1, option=1;
		for (int i = 0; i < N; i++) {
			if(arr[i] <'A') {
				if(arr[i+1]=='0') {
					num = 10;
					i++;
				}else {
					num = arr[i]-'0';
				}
				
				i++;
				if(arr[i] == 'S') bonus = 1;
				else if(arr[i] == 'D') bonus = 2;
				else if(arr[i] == 'T') bonus = 3;

				if(i+1>=N) {
					list.add(new Score(num, bonus, 1));
					break;
				}
				
				if(arr[i+1] >= '0') {
					list.add(new Score(num, bonus, 1));
					continue;
				}
				
				i++;
				if(arr[i] == '*') option = 2;
				else if(arr[i] == '#') option = -1;

				list.add(new Score(num, bonus, option));
			}
		}
		
		int res = 0, pre=0;
		for (int i = 0, end = list.size(); i < end; i++) {
			num = list.get(i).num;
			bonus = list.get(i).bonus;
			option = list.get(i).option;

			if(option==2) res += pre*option;
			else res += pre;
			pre = (int)Math.pow(num, bonus) * option;
		}
		res += pre;
		return res;
		
    }
}
