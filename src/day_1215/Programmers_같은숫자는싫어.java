package day_1215;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 같은숫자는싫어


public class Programmers_같은숫자는싫어 {
	public static void main(String[] args) {
		int arr[] = {1,1,3,3,0,1,1};
		int res[] = solution(arr);
		System.out.println(res);
	}
	
	public static int[] solution(int []arr) {
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
