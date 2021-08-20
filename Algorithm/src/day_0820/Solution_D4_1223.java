package day_0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_D4_1223 {
	static int T=11, N;
	static String item;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		
		Stack<String> stack = new Stack<>();
		
		String[] arr;
		String item;
		int a;
		for (int t = 1; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			arr = in.readLine().split("");
			for (int i = 0; i < N; i++) {
				item = arr[i];
				// 곱셈은 미리 처리
				if(item.equals("*")) {
					a = Integer.parseInt(stack.pop()) * Integer.parseInt(arr[++i]);
					stack.push(Integer.toString(a));
				}else if(item.equals("+")) {
					continue;
				}else {
					stack.push(item);
				}
			}
			
			// 뽑으면서 덧셈 연산
			int res = 0;
			while(!stack.isEmpty()) {
				res += Integer.parseInt(stack.pop());
			}
			
			builder.append("#" + t + " " + res +"\n");
		}
		System.out.println(builder);
	}
}
