package day_1206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 동철이의 일 분배
 * 
 * 오랜만에 자릿수 바꾸기 하려니 생각이 잘 안난다
 * 기존에 쓰던 String.format()과 Math.round()를 모두 사용해봄
 * Math.round()는 소수점 아래가 0이면 절삭해서 여기서 못씀
 * 
 */

public class Solution_swea_D4_1865 {
	static int T, N;
	static double maxSum;
	static double[][] map, input;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		StringBuilder res = new StringBuilder();
		
		T = Integer.parseInt(br.readLine())+1;
		
		double max;
		for (int t = 1; t < T; t++) {
			N = Integer.parseInt(br.readLine())+1;
			maxSum = 0;
			
			input = new double[N][N];
			map = new double[N][N];

			// 각 직원이 각 일을 성공할 확률 Pij 입력
			for (int i = 1; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 1; j < N; j++) {
					int tmp = Integer.parseInt(token.nextToken());
					input[i][j] = (double)((double)tmp / 100);
				}
			}
			
			// 이번 테케에서의 max 확률
			dfs(1,0,1);
			
			max = 0;
			for (int i = 1; i < N; i++) {
				max = Math.max(max, map[N-1][i]);
			}
		
			
			res.append("#"+t+" "+ String.format("%.6f", max*100) + "\n");
//			res.append("#"+t+" "+ Math.round(max*100000000) / 1000000.0 + "\n");
		}
		
		System.out.println(res.toString());
	}
	
	private static void dfs(int worker, int preWorks, double sum) {
		if(sum==0) return;
		if(worker==N) {
			maxSum = (sum>maxSum)?sum:maxSum;
			return;
		}
		for (int i = 1; i < N; i++) {
			// 이전에 선택한적 있으면 패스
			if((preWorks&1<<i)!=0) continue;
			// 이전 경로의 해당 위치보다 작으면 패스
			if(maxSum >= input[worker][i]*sum) continue;
			// 위의 가지치기에 해당하지 안으면 계속 진행
			map[worker][i] = input[worker][i]*sum;
			dfs(worker+1, preWorks|1<<i, input[worker][i]*sum);
		}
	}
}