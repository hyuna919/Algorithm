package day_0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// 난 조합으로 풀었는데
// 1000의 제곱이라 이중반복이 차라리 났다...
public class Solution_D3_9229_한빈이와spotmart {
	static int T, N, M, max, sum;
	static int [] numbers, inputs;
	static StringTokenizer token;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			numbers = new int[2];
			
			token = new StringTokenizer(in.readLine());
			N = Integer.parseInt(token.nextToken());
			M = Integer.parseInt(token.nextToken());
			inputs = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			max = -1;
			combination(N,2);
			System.out.printf("#%d %d%n",t,max);
		}
		
	}
	
	private static void combination(int n, int r) {
		if(r==0) {
			sum =numbers[0]+numbers[1];
			if(sum<=M) max = Math.max(max, numbers[0]+numbers[1]);
			return;
		}
		if(n<r) return ;
		
		numbers[r-1] = inputs[n-1];
		
		combination(n-1, r-1);
		combination(n-1, r);
		
	}
}
