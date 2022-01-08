package day_0917;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 
 * 무선충전
 * 
 */
public class Solution_swea_5644 {
	static class BC implements Comparable<BC>{
		int x,y,cover,perf;

		public BC(int x, int y, int cover, int perf) {
			super();
			this.x = x;
			this.y = y;
			this.cover = cover;
			this.perf = perf;
		}

		@Override
		public int compareTo(BC o) {
			return o.perf-this.perf;
		}
	}
	static int[][] map,moveAB;
	static ArrayList<BC> list;
	static int N = 10, M, numBC, sum;
	static int[] A,B;
	
	// 정-상-우-하-좌
	static int[] dx = {0,-1,0,1,0};
	static int[] dy = {0,0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		
		int T = Integer.parseInt(in.readLine())+1;
		for (int t = 1; t < T; t++) {
			map = new int[N+1][N+1];
			list = new ArrayList<BC>();
			sum = 0;
			
			token = new StringTokenizer(in.readLine());
			M = Integer.parseInt(token.nextToken());
			numBC = Integer.parseInt(token.nextToken());
			
			// 사용자 이동 정보 입력
			moveAB = new int[2][M];
			for (int i = 0; i < 2; i++) {
				token = new StringTokenizer(in.readLine());
				for (int j = 0; j < M; j++) {
					moveAB[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			
			// 충전기 입력
			int x,y,cover,perf;
			list.add(new BC(-1,-1,-1,Integer.MAX_VALUE));
			for (int i = 0; i < numBC; i++) {
				token = new StringTokenizer(in.readLine());
				y = Integer.parseInt(token.nextToken());
				x = Integer.parseInt(token.nextToken());
				cover = Integer.parseInt(token.nextToken());
				perf = Integer.parseInt(token.nextToken());
				list.add(new BC(x,y,cover,perf));
			}
			// 충전기 정렬
			Collections.sort(list);
			
			// map에 충전기 범위 표시
			int tmp;
			for (int i = 1; i < numBC+1; i++) {
				BC bc = list.get(i);
				
				tmp = map[bc.x][bc.y];
				if(tmp==0) map[bc.x][bc.y] = i;
				else if(tmp!=i && tmp<10) map[bc.x][bc.y] = tmp*10+i;
				
				
				dfs(bc.x, bc.y, bc.cover, i);
			}
			
			
			
			// 사용자 움직이기
			// 초기화+시작위치가 충전범위인지 확인
			A = new int[] {1,1};
			B = new int[] {10,10};
			isCharged();
			for (int i = 0; i < M; i++) {
				A[0] += dx[moveAB[0][i]];
				A[1] += dy[moveAB[0][i]];
				B[0] += dx[moveAB[1][i]];
				B[1] += dy[moveAB[1][i]];
				isCharged();
			}
			
			
			builder.append("#"+t+" "+sum+"\n");
		}
		System.out.println(builder);
		
	}

	private static void isCharged() {
		int aVal, bVal;
		aVal = map[A[0]][A[1]];
		bVal = map[B[0]][B[1]];
		// 둘 다
		if(aVal!=0 && bVal!=0) {
			// 양쪽 다 하나씩
			if(aVal<10 && bVal<10) {
				if(aVal!=bVal) sum += list.get(aVal).perf + list.get(bVal).perf;
				else sum += list.get(aVal).perf;
			}
			// a만 한개
			else if(aVal<10) {
				sum += list.get(aVal).perf;
				if((bVal/10)==aVal) sum += list.get(bVal%10).perf;
				else sum += list.get(bVal/10).perf;
			}
			// b만 한개
			else if(bVal<10) {
				sum += list.get(bVal).perf;
				if((aVal/10)==bVal) sum += list.get(aVal%10).perf;
				else sum += list.get(aVal/10).perf;
			}
			// 양쪽 다 두개
			else {
				// 양쪽 상위가 다름
				if((aVal/10)!=(bVal/10)) sum += list.get(aVal/10).perf + list.get(bVal/10).perf;
				else {
					int max = Math.max(list.get(aVal/10).perf + list.get(bVal%10).perf,
										list.get(aVal%10).perf + list.get(bVal/10).perf);
					max = Math.max(max, list.get(aVal/10).perf);	// 같은 충전기 선택하는 경우
					sum += max;
				}
			}
		}
		// 한쪽만
		else if(aVal!=0) {
			if(aVal<10) sum += list.get(aVal).perf;
			else sum += list.get(aVal/10).perf;
		}else if(bVal!=0) {
			if(bVal<10) sum += list.get(bVal).perf;
			else sum += list.get(bVal/10).perf;
		}
		
		
	}

	private static void dfs(int x, int y, int cover, int num) {
		if(cover==0) {
			return;
		}
		
		int nx,ny,tmp;
		for (int i = 1; i < 5; i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			
			// 경계값 검사
			if(nx<1 || nx>N || ny<1 || ny>N) continue;
			
			tmp = map[nx][ny];
			if(tmp==0) map[nx][ny] = num;
			else if(tmp!=num && tmp<10) map[nx][ny] = tmp*10+num;
			// 충전량 기준으로 정렬되어있기 떄문에 이미 해당 자리에 충전량 상위 2개가 기록되어있다면 더 기록할 필요가 없다.
			dfs(nx,ny,cover-1,num);
		}
	}
}
