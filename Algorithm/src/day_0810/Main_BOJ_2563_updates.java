package day_0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2563_updates {
	static boolean [] base;
	static int N, r, c, cnt;
	
	static String [] tmp;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		cnt = 0;
		base = new boolean[10000];
		while(N-->0) {
			tmp = in.readLine().split(" ");
			r = Integer.parseInt(tmp[0]);
			c = Integer.parseInt(tmp[1]);
			
			
			for (int i = r, r_end=r+10; i < r_end; i++) {
				for (int j = c, c_end=c+10; j < c_end; j++) {
					if(!base[i*100 + j]) {
						cnt++;
						base[i*100 + j] = true;
					}
				}
			}
		}
		System.out.println(cnt);
	}
}
