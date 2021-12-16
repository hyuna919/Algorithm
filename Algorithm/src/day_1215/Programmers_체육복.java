package day_1215;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

// K번째수


public class Programmers_체육복 {
	public static void main(String[] args) {
		int[] arr = {1,1,3,3,0,1,1};
		int[] res = solution(arr);
		System.out.println(res);
	}
	
	public static int[] solution(int[] arr) {
//		Stack<Integer> stack = new Stack<Integer>();
//		
//		for (int i = 0, end=arr.length; i < end; i++) {
//			if(stack.isEmpty()) {
//				stack.add(arr[i]);
//				continue;
//			}
//			
//			if(stack.peek() == arr[i]) continue;
//			else stack.add(arr[i]);
//		}
		
		int idx = 0;
		List<Integer> list = new ArrayList<>();
		list.add(arr[0]);
		for (int i = 1, end=arr.length; i < end; i++) {
			if(list.get(idx) == arr[i]) continue;
			else {
				list.add(arr[i]);
				idx++;
			}
		}
		
		int[] res = new int[list.size()];
		for (int i = 0, end=list.size(); i < end; i++) {
			res[i] = list.get(i);
		}
		
		return res;
    }
}
