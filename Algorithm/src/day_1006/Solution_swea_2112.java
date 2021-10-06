package day_1006;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 보호필름
 * 
 * 
 */
public class Solution_swea_2112 {
	static int T, D, W, K, res;
	static boolean[][] film, oriFilm;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		
		T = Integer.parseInt(in.readLine())+1;
		for (int t = 1; t < T; t++) {
			token = new StringTokenizer(in.readLine());
			D = Integer.parseInt(token.nextToken());	// 두꼐
			W = Integer.parseInt(token.nextToken());	// 너비
			K = Integer.parseInt(token.nextToken());	// 검사 기준
			res = K;
					
			film = new boolean[D][W];
			oriFilm = new boolean[D][W];
			
			for (int i = 0; i < D; i++) {
				token = new StringTokenizer(in.readLine());
				for (int j = 0; j < W; j++) {
					oriFilm[i][j] = Integer.parseInt(token.nextToken())==0?true:false;	// 0==A==true
				}
			}
			
			// 애초에 합격인지
			copy();
			if(K==1 || test()) builder.append("#"+t+" "+0+"\n");
			else {
				// 부분집합
				for (int i = 1, end = 1<<D; i < end; i++) {
					function(i,0,0);
					if(res==1) break;
				}
				builder.append("#"+t+" "+res+"\n");
			}
		}
		System.out.println(builder);
	}
	
	private static void function(int value, int start,int cnt) {
		if(start>D-1) return;
		if(cnt>res) return;
		if(cnt>=K) return;
		
		for (int j = start; j < D; j++) {
			if((value&1<<j)!=0) {
				cnt++;
				// A로 염색하는 경우
				change(j, true);
				if(test()) {
					res = Math.min(res, cnt);
				}else {
					function(value, j+1,cnt);
				}
				// B로 염색하는 경우
				change(j, false);
				if(test()) {
					res = Math.min(res, cnt);
				}else {
					function(value, j+1,cnt);
				}
				
				// 염색한 행 원복
				for (int c = 0; c < W; c++) {
					film[j][c] = oriFilm[j][c]; 
				}
				return;
			}
		}
		
	}
	
	private static void change(int x,boolean value) {
		for (int i = 0; i < W; i++) {
			film[x][i] = value;
		}
	}
	private static boolean test() {
		for (int c = 0; c < W; c++) {	// 열단위로 볼거라
			for (int r = 0; r < D; r++) {	//
				if(r==D-K+1) return false;	// 이번 열이 통과하지 못함
				if(isSame(r,c, film[r][c])) break;	// 이번 열 통과하면 다음 열로
			}
		}
		return true;
	}
	
	private static boolean isSame(int x, int y, boolean value) {
		for (int i = x+1, end=x+K; i < end; i++) {
			if(film[i][y]!=value) return false;
		}
		return true;
	}
	
	private static void copy() {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				film[i][j] = oriFilm[i][j];
			}
		}
	}

}
