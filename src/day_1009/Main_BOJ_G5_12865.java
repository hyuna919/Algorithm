package day_1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 평범한 배낭
 * 
 * map을 일차원배열로 하는거 해봤는데 생각해보니 이러면 한 물건이 ㅣ여러개 사용할 수 있는 경우가 되더라
 * 
 */
public class Main_BOJ_G5_12865 {
	static int N, K;
	static int[] weights, values;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());
		
		weights = new int[N];
		values = new int[N];
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			weights[i] = Integer.parseInt(token.nextToken());
			values[i] = Integer.parseInt(token.nextToken());
		}
		
		int[][] map = new int[N][K+1];
		
		int value = 0, weight;
		int max = 0;
		for (int i = 0; i <N; i++) {
			weight = weights[i];
			value = values[i];
			for (int j = 0, end=K+1; j < end; j++) {
				if(i==0) {				// 첫행이면 그냥 넣는다
					if(j >= weight) map[i][j] = value;
					continue;
				}
				if(j<weight) {
					map[i][j] = map[i-1][j];
					continue;
				}
				if(map[i-1][j-weight]+value > map[i-1][j]) { //  이번 물건 무게 더하는게 더 크면
					map[i][j] = map[i-1][j-weight]+value;
				}else {	// 이전 무게가 더 크면
					map[i][j] = map[i-1][j];
				}
			}
			max = Math.max(max, map[i][K]);
		}
		System.out.println(max);
	}
}