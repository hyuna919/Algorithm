package day_1216;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// K번째수


public class Programmers_두개뽑아서더하기{
	public static void main(String[] args) {
		int[] numbers = {5,0,2,7};
		int[] res = solution(numbers);
		System.out.println(Arrays.toString(res));
	}
	
	public static int[] solution(int[] numbers) {
		Set<Integer> set = new HashSet<Integer>();
		int N = numbers.length;
		
		for (int i = 0, end = N-1; i < end; i++) {
			for (int j = i+1; j < N; j++) {
				set.add(numbers[i]+numbers[j]);
			}
		}
		
		N = set.size();
		int[] res = new int[N];
		Iterator<Integer> iter = set.iterator();
		for (int i = 0; i < N; i++) {
			res[i] = iter.next();
		}
		
		
		Arrays.sort(res);
		
		return res;
    }
}
