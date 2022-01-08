package day_1027;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 게리맨더링 2
 * 
 */
public class Main_BOJ_17779 {
	static int N, res=Integer.MAX_VALUE, max, min;
	static int[][] map;
	static int[] headcount;
	static boolean isNew;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		map = new int[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 1; j < N+1; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		// 기준점을 완탐하면서 각 기준점 기준으로 늘릴수 있는만큼 5번선거구를 늘릴거야
		// 기준점 r,c 는 1, N일수 없다.
		for (int x = 1, end=N; x < end; x++) {
			for (int y = 2; y < end; y++) {

				headcount = new int[6];
				isNew = true;
				seperate(x,y);
			}
			
		}
		
		System.out.println(res);
		
	}
	private static void seperate(int x, int y) {
		
		// 1. 선거구 나누기
		int d1=1, d2=1;
		count(x, y, d1, d2);	// 5칸일때 
		//d1늘리기 
		while(x+d1+d2 <= N) {
			d1++;
			if(Math.abs(d1-d2)==1)continue;
			count(x, y, d1, d2);	//1,3,5선거구
		}
		//d1+d2늘리기
		while(x+d2 <= N) {
			d2++;
			if(Math.abs(d1-d2)==1)continue;
			count(x, y, d1, d2);
		}
		//d1줄이기
		while(d1>=1) {
			d1--;
			if(Math.abs(d1-d2)==1)continue;
			count(x, y, d1, d2);
		}
		//d2줄이기
		while(d2>=1) {
			d2--;
			if(Math.abs(d1-d2)==1)continue;
			count(x, y, d1, d2);
		}
		
	}
	private static void count(int x, int y, int d1, int d2) {
		headcount = new int[6];
		int xd1 = x+d1;
		int xd2 = x+d2;
		int yd = y-d1+d2;
		
		int[][] visit = new int[N+1][N+1];
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				
				if(r<xd1 && c<=y) {
					headcount[1] += map[r][c];
					visit[r][c] = 1;
				}
				else if(r<xd2 && y<c) {
					headcount[2] += map[r][c];
					visit[r][c] = 2;
				}
				else if(xd1<=r && c<=yd) {
					headcount[3] += map[r][c];
					visit[r][c] = 3;
				}
				else if(xd2<r && yd<=c) {
					headcount[4] += map[r][c];
					visit[r][c] = 4;
					
				}
				else headcount[5] += map[r][c];
			}
		}
		
		min = headcount[1];
		max = headcount[1];
		
		
		
		for (int i = 2; i < 6; i++) {
			min = (headcount[i]<min)?headcount[i]:min;
			max = (headcount[i]>max)?headcount[i]:max;
		}
		
		res = (res>max-min)?max-min:res;
	}

}
