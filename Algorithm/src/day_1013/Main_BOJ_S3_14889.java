package day_1013;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 스타트와 링크
 * 
 * 조합으로 각 팀원 구하고->각 팀 능력치 구하기
 * 
 * 
 */

public class Main_BOJ_S3_14889 {
	static int N, min=Integer.MAX_VALUE;
	static int[][] map;
	static int[] start, link;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		start = new int[N/2];
		link = new int[N/2];
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		// 스타트팀 구하기
		comb(N,N/2);
		
		System.out.println(min);
		
	}
	private static void comb(int n, int r) {
		if(r==0) {
			// 각 팀의 능력치 구해서 차이 구하기
			calAbility();
			return;
		}
		
		if(min==0) return;
		if(n<r) return;
		
		start[r-1] = n-1;
		comb(n-1,r-1);
		comb(n-1,r);
	}
	
	private static void calAbility() {
		// 링크팀 구하기
		getLink();
		
		int sumStart=0, sumLink=0;
		
		for (int i = 0, end=N/2; i < end; i++) {
			for (int j = i+1; j < end; j++) {
				sumStart += map[start[i]][start[j]] + map[start[j]][start[i]];
				sumLink += map[link[i]][link[j]] + map[link[j]][link[i]];
			}
		}
		
		min = Math.min(min, Math.abs(sumStart-sumLink));
		
	}
	
	private static void getLink() {
		int idx1 = 0;
		int idx2 = 0;
		for (int i = 0; i < N; i++) {
			if(idx1<N/2 && start[idx1]==i) idx1++;
			else link[idx2++] = i;
		}
	}
}
