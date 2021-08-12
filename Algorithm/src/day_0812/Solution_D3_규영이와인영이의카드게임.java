package day_0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D3_규영이와인영이의카드게임 {
	static int [] mein, dein;
	static int N=9, T, win, lose, flag;
	public static void main(String[] args) throws NumberFormatException, IOException {
		mein = new int[N];
		dein = new int[N];
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		StringBuilder builder = new StringBuilder();
		T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t < T+1; t++) {
			win = 0;
			lose = 0;
			token = new StringTokenizer(in.readLine());
			
			// 내 카드 받아서 정렬
			for (int i = 0; i< N; i++) {
				mein[i] = Integer.parseInt(token.nextToken());
				flag = flag| 1<<mein[i];
			}
			Arrays.sort(mein);
			
			// 상대방 카드 목록 만들기
			int idx = 0;
			for (int i = 1; i< 19; i++) {
				if((flag & 1<<i)!=0) continue;
				else dein[idx++] = i;
			}
			
			flag = 0;
			round(0,0,0);
			builder.append("#"+ t +" " +win +" " + lose +"\n");
		}
		System.out.println(builder);
	}
	
	
	private static void round(int r, int meinSum, int deinSum) {
		int sum = 0;
		// 한 라운드의 한 케이스 종료
		if(r == N) {
			// 승패 여부에 따라 승패 카운드 올리기
			if(meinSum>deinSum)win++;
			else lose++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if((flag & 1<<i) !=0) continue;
			flag = flag | 1<<i;
			
			sum = mein[r] + dein[i];
			if(mein[r]>dein[i]) round(r+1, meinSum+sum, deinSum);
			if(mein[r]<dein[i]) round(r+1, meinSum, deinSum+sum);
			
			flag = flag ^ 1<<i;
		}
	}
}
