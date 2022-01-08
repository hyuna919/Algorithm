package day_1210;

import java.util.Arrays;

// K번째수


public class Programmers_K번째수 {
	
	static int T, N;
	public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		int[] res = solution(array, commands);
		System.out.println(Arrays.toString(res));
	}
	
	public static int[] solution(int[] array, int[][] commands) {
		T = commands.length;
		N = array.length;
		
		int[] now, list, res;
		int length;
		
		res = new int[T];
		
		for (int t = 0; t < T; t++) {
			now = commands[t];
			list = Arrays.copyOfRange(array, now[0]-1, now[1]);
			
			Arrays.sort(list);
			
			res[t] = list[now[2]-1];
		}
		return res;
    }
}
