package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KMP {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] text = in.readLine().toCharArray();
		char[] pattern = in.readLine().toCharArray();
		
		int tLength = text.length, pLength = pattern.length;
		
		int[] fail = new int[pLength];
		for (int i = 0, j = 0; i < pLength; i++) {
			while(j>0 && pattern[i] != pattern[j]) {
				j = fail[i-1];
			}
			if(pattern[i] == pattern[j]) fail[i] = ++j;
		}

		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0, j=0; i < tLength; i++) {
			while(j>0 && text[i] != pattern[j]) {
				j = fail[i-1];
			}
			
			if(text[i] == pattern[j]) {
				if(j==pLength-1) {
					cnt++;
					list.add((i+1)-pLength+1);
					j=fail[j];
				}else {
					j++;
				}
			}
			
			System.out.println(cnt);
			
		}
	}

}
