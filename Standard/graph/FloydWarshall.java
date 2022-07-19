package graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
 * 플로이드 워셜
 * - 모든 정점에 대한 최소 비용(최단거리) 구하는 알고리즘
 * - 성능 (N^3)
 * - 정점이 400개면 1초컷 내
 */

public class FloydWarshall {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		int max = Integer.MAX_VALUE >> 2;
		
		StringTokenizer token;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if(map[i][j]==0 && i!=j) map[i][j] = max;
			}
		}

		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
//				if(i==k) continue;				// 이 연산을 따로 n^2번 하느니 그냥 진행하는게 수가 클수록 유리하다.
				for (int j = 0; j < N; j++) {
//					if(i==j||k==j) continue;	// 위와 동일
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
		
	}

}
