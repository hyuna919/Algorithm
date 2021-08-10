package day_0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2563 {
	static boolean [][] base;
	static boolean [] row;
	static int N, r, c, cnt;
	static StringTokenizer token;
	
	static String [] tmp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		cnt = 0;
		base = new boolean[100][100];
		while(N-->0) {
//			token = new StringTokenizer(in.readLine());
//			r = Integer.parseInt(token.nextToken());
//			c = Integer.parseInt(token.nextToken());
			tmp = in.readLine().split(" ");
			r = Integer.parseInt(tmp[0]);
			c = Integer.parseInt(tmp[1]);
			
			
			for (int i = r, r_end=r+10; i < r_end; i++) {
				row = base[i];
				for (int j = c, c_end=c+10; j < c_end; j++) {
					if(!row[j]) {
						cnt++;
						row[j] = true;
					}
				}
			}
		}
		System.out.println(cnt);
	}
}
