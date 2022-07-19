package com.ssafy.strpattern;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class String_RobinKarpPattern {

	public static void main(String[] args)	throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char[] target = in.readLine().toCharArray();
		char[] pattern = in.readLine().toCharArray();
		
		
		int tSize = target.length;
		int pSize = pattern.length;
		if(tSize<pSize) {
			System.out.println(0);
		}else {
			int base = 26;				//A~Z라서 base를 26으로 설정
			int mod = (int)1e9+7;
			long power=1;
			long tHash = 0, pHash=0;
			
			for (int i = 0; i < pSize; i++) {
				pHash = (pHash*base+pattern[i]) % mod;
				tHash = (tHash*base+target[i]) % mod;
				if(i!=0) power = (power*base) % mod;
			}
			LinkedList<Integer> list = new LinkedList<>();
			for (int i = 0, end = tSize- pSize; i <=end; i++) {
				if(pHash==tHash) list.add(i+1);
				if(i+pSize==tSize) break;
				
				tHash = (base* (tHash- target[i]*power)+ target[i+pSize]) % mod;
				if(tHash <0 ) tHash +=mod;
			}
			
			System.out.println(list.size());
			for (Integer idx : list) {
				System.out.print(idx+" ");
				
			}
		}
	}
}
