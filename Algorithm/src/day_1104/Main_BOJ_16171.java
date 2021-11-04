package day_1104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_BOJ_16171 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = in.readLine().toCharArray();
		char[] pattern = in.readLine().toCharArray();

		// 실패함수
		int pLen = pattern.length;
		int[] fail = new int[pLen];
		for (int i = 1, j = 0; i < pLen; i++) {
			while(j>0 && pattern[i]!=pattern[j]) {
				j = fail[j-1];
			}
			if(pattern[i]==pattern[j]) fail[i] = ++j;
		}
		
		// 숫자 빼고 리스트 생성
		List<Character> text = new ArrayList<Character>();
		for (int i = 0, end=input.length; i < end; i++) {
			if(input[i]>='A') text.add(input[i]); 
		}
		
		// KMP
		int cnt = 0, tLen = text.size();
		for (int i = 0, j = 0; i < tLen; i++) {
			while(j>0 && text.get(i)!=pattern[j]) j = fail[j-1];
			
			if(text.get(i) == pattern[j]) {	// 두 글자 일치
				if(j == pLen-1) {
					System.out.println(1);
					return;
				}
				j++;
			}
		}
		
		System.out.println(0);
		
		
	}

}
