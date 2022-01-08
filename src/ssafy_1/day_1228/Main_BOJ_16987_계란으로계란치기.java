package day_1228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 체력과 공격력으로 생각하니 편하더라

public class Main_BOJ_16987_계란으로계란치기 {
	static int N, max=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(br.readLine());
		int[][] eggList = new int[N][2]; //[계란번호][내구/무게]
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			eggList[i][0] = Integer.parseInt(token.nextToken());
			eggList[i][1] = Integer.parseInt(token.nextToken());
		}
		
		dfs(eggList, 0, 0);
		
		System.out.println(max);
	}
	private static void dfs(int[][] eggList, int egg1, int cnt) {
		// 가장 마지막 계란까지 다 들어봤으면 최대값 비교함
		if(egg1>=N) {
			max = (max<cnt)?cnt:max;
			return;
		}
		
		// 들고있는 계란이 꺠져있으면 내려두고 다음계란을 든다
		if(eggList[egg1][0]<1) {
			dfs(eggList, egg1+1, cnt);
			return;
		}
		
		// 어떤 계란 칠 지 결정
		int broken=0, flag=0;
		for (int egg2 = 0; egg2 < N; egg2++) {
			// 자기 자신이나 || 이미 꺠진 계란은 치지 않음
			if(egg2==egg1 || eggList[egg2][0]<1) continue;
			flag=1;
			// 계란 치기
			eggList[egg1][0] -= eggList[egg2][1];
			eggList[egg2][0] -= eggList[egg1][1];
			
			// 내구도 확인
			broken = 0;
			if(eggList[egg1][0] < 1) broken++;
			if(eggList[egg2][0] < 1) broken++;
			
			// 다음계란을 든다
			dfs(eggList, egg1+1, cnt+broken);
			
			// 원복
			broken = 0;
			eggList[egg1][0] += eggList[egg2][1];
			eggList[egg2][0] += eggList[egg1][1];
		}
		if(flag==0) dfs(eggList, egg1+1, cnt);
	}
}
