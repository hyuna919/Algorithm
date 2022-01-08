package day_1103;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_S5_1158 {
	static int N,K;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		StringBuilder builder = new StringBuilder();
		
		N = Integer.parseInt(token.nextToken()) +1;
		K = Integer.parseInt(token.nextToken());
		
		// 배열 생성
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < N; i++) {
			list.add(i);
		}
		
		builder.append("<");
		
		// 삭제
//		int idx = --K;
//		while(!list.isEmpty()) {
//			if(idx>=list.size()) idx %= list.size();
//			builder.append(list.get(idx)+", ");
//			list.remove(idx);
//			idx += K;
//		}
//		
//		builder.deleteCharAt(builder.lastIndexOf(" "));	
//		builder.deleteCharAt(builder.lastIndexOf(","));	

		// 1 2 3 4 5 6 7
		
		int idx = --K;
		while(list.size()>1) {
			if(idx>=list.size()) idx %= list.size();
			builder.append(list.get(idx)+", ");
			list.remove(idx);
			idx += K;
		}
		builder.append(list.get(0));				
		
		builder.append(">");
		System.out.println(builder.toString());
	}

}
