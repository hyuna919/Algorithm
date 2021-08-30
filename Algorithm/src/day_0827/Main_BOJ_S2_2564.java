package day_0827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_S2_2564 {
	static int X, Y, N, d, xy, sum;
	static int[] dir, pos;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		X = Integer.parseInt(token.nextToken());
		Y = Integer.parseInt(token.nextToken());
		
		N = Integer.parseInt(in.readLine());
		dir = new int[N];
		pos = new int[N];
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			dir[i] = Integer.parseInt(token.nextToken());
			pos[i] = Integer.parseInt(token.nextToken());
		}
		
		token = new StringTokenizer(in.readLine());
		d = Integer.parseInt(token.nextToken());
		xy = Integer.parseInt(token.nextToken());
		
		int store, distance, tmp;
		for (int n = 0; n < N; n++) {
			store = dir[n];
			if(d==store) {	// 같은 방향
				sum += Math.abs(xy-pos[n]);
			}else if(d*store==2) {	// 맞은편 -남북
				tmp = pos[n]+xy;
				distance = tmp>X?2*X-tmp:tmp;
				sum += Y + distance;
			}else if(d*store==12) { // 맞은편 - 동서
				tmp = pos[n]+xy;
				distance = tmp>Y?2*Y-tmp:tmp;
				sum += X + distance;
			}else {
				tmp = d*store;
				if(tmp==3) {
					sum += pos[n]+xy;
				}else if(tmp==4) {
					if(d==1) sum += X-xy+pos[n];
					else sum += X-pos[n]+xy;
				}else if(tmp==6) {
					if(d==3) sum += Y-xy+pos[n];
					else sum += Y-pos[n]+xy;
				}else {
					sum += X+Y-xy-pos[n];
				}
			}
		}		
		System.out.println(sum);
	}
}
