package day_1217;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// K번째수


public class Programmers_2016년{
	public static void main(String[] args) {
		int a = 5;
		int b = 24;
		String res = solution(a, b);
		System.out.println(res);
	}
	
	public static String solution(int a, int b) {
		int[] month = {0,31,29,31,30,31,30,31,31,30,31,30,31,30,31};
		String[] week = {"FRI","SAT","SUN","MON","TUE","WED","THU"};
		
		// 며칠 차이냐
		int sum = 0;
		if(a!=1) {
			for (int i = 1; i < a; i++) {
				sum += month[i];
			}
		}
		sum += b;
		sum--;
		
		// 요일 구하기
		return week[sum%7];
    }
}
