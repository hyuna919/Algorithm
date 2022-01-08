package day_0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_S5_2477 {
	static int N, sum;
	static int[] dir, num;
	static int bigX=0, bigY=0, smallX=Integer.MAX_VALUE, smallY=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		dir = new int[8];
		num = new int[8];
		
		StringTokenizer token;
		for (int i = 1; i < 7; i++) {
			token = new StringTokenizer(in.readLine());
			dir[i] = Integer.parseInt(token.nextToken());
			num[i] = Integer.parseInt(token.nextToken());
		}
		dir[0] = dir[6];
		dir[7] = dir[1];
		
		for (int i = 1; i < 7; i++) {
			if(dir[i] == 1 || dir[i] == 2) {
				bigX = Math.max(bigX, num[i]);
				if(dir[i-1]==dir[i+1]) {	// 전후의 방향이 같으면 작은 네모의 길이
					smallX = num[i];
				}
			}else {
				bigY = Math.max(bigY, num[i]);
				if(dir[i-1]==dir[i+1]) {	// 전후의 방향이 같으면 작은 네모의 길이
					smallY = num[i];
				}
			}
		}
		
		sum = bigX * bigY;
		sum -= smallX * smallY;
		sum *= N;
		
		System.out.println(sum);
	}
}
