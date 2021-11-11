package day_1110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 구간합
// 어렵;;;
// 한번 공부해두면 두고두고 좋겠다
public class Main_BOJ_11660 {
	static int N, M;
	static int[][] map;
	static int[] prefixSum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		StringBuilder builder = new StringBuilder();
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		map = new int[N][N];
		prefixSum = new int[N*N];
		
		// 표 입력
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if(i==0 && j==0) {
					prefixSum[0] = map[0][0];
				}else {
					prefixSum[i*N+j] = prefixSum[i*N+j-1] + map[i][j];
				}
				
			}
		}
		
		long sum;
		int x1, y1, x2, y2;
		int n1, n2;
		for (int m = 0; m < M; m++) {
			sum = 0;
			
			token = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(token.nextToken())-1;
			y1 = Integer.parseInt(token.nextToken())-1;
			x2 = Integer.parseInt(token.nextToken())-1;
			y2 = Integer.parseInt(token.nextToken())-1;
			
			// 구간합
			n2 = x2*N+y2;
			n1 = x1*N+y1-1;
			if(n1<0) {
				sum = prefixSum[n2];
			}else {
				sum = prefixSum[n2] - prefixSum[n1];
			}
			
			// 제외-왼쪽
			for (int x = x1+1; x <= x2; x++) {
				// 왼쪽
				n2 = (x*N) + (y1-1);
				n1 = ((x-1)*N) + (N-1);
				if(n1<0) {
					sum -= prefixSum[n2];
				}else {
					sum -= prefixSum[n2] - prefixSum[n1];
				}
			}
			
			for (int x = x1; x < x2; x++) {
				// 오른쪽 
				n2 = ((x+1)*N) - 1;
				n1 = (x*N) + y2;
				if(n1<0) {
					sum -= prefixSum[n2];
				}else {
					sum -= prefixSum[n2] - prefixSum[n1];
				}
			}
			
			builder.append(sum+"\n");			
		}
		System.out.println(builder);
	}
}
