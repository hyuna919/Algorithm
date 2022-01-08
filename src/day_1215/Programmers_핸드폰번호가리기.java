package day_1215;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

// 핸드폰번호가리기


public class Programmers_핸드폰번호가리기 {
	public static void main(String[] args) {
		String phone_number = "027778888";
		String res = solution(phone_number);
		System.out.println(res);
	}
	
	public static String solution(String phone_number) {
		int n = phone_number.length();
		StringBuilder res = new StringBuilder();
		
		for (int i = 0, end = n-4; i < end; i++) {
			res.append("*");
		}
		
		int idx = n-4;
		res.append(phone_number.charAt(idx++));
		res.append(phone_number.charAt(idx++));
		res.append(phone_number.charAt(idx++));
		res.append(phone_number.charAt(idx));
		
		return res.toString();
    }
}
