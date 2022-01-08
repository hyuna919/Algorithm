package day_0813;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

class Pos{
	int x;
	int y;
	
	public Pos() {
		this.x = 0;
		this.y = 0;
	}
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_G5_15686 {
	static Pos[] home, chicken;
	static int[][] distance, map;
	static int[] chicken_comb,res;
	static int N, M, cntHome, cntChicken, min=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;

		// 입력
		token = new StringTokenizer(in.readLine());
		N = Integer.parseInt(token.nextToken())+1;
		M = Integer.parseInt(token.nextToken());
		
		home = new Pos[2*N];	// 집 최대 개수 2N
		chicken = new Pos[14];	// 치킨집 최대 개수 13
		chicken_comb = new int[M];
		
		// 입력 받기
		int tmp;
		for (int i = 1; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 1; j < N; j++) {
				tmp = Integer.parseInt(token.nextToken());
				if(tmp==1) {	// 집이면
					home[++cntHome] = new Pos(i,j);
				}else if(tmp==2) { // 치킨집이면
					chicken[++cntChicken] = new Pos(i,j);
				}
			}
		}
		
		// 모든 치킨집에 대해서 모든 집에 대한 거리 구하기
		distance = new int[cntChicken+1][cntHome+1];	// 인덱스 1부터 하러갈
		int r1, r2,c1,c2;
		for (int i = 1; i < cntChicken+1; i++) {
			r1 = chicken[i].x;
			c1 = chicken[i].y;
			for (int j = 1; j < cntHome+1; j++) {
				r2 = home[j].x;
				c2 = home[j].y;
				distance[i][j] = Math.abs(r1-r2)+Math.abs(c1-c2);
			}
		}
		
		// 조합 구하기
		comb(cntChicken, M);
		
		System.out.println(min);

	}
	private static void comb(int n, int r) {
		if(r==0) {
			check();
			int sum = IntStream.of(res).sum();
			min = Math.min(min, sum);
			return;
		}
		
		if(n<r) return;
		
		chicken_comb[r-1] = n;
		comb(n-1, r-1);
		comb(n-1, r);
	}
	
	// 각 조하벵서 기존에 구해둔 거리 비교ㅐㅎ서 최소값 결정
	// 집에 대해서 각 치킨집이 가지는걸 비ㅣ교해ㅑㅇ
	private static void check() {
		res = new int[cntHome+1];	// 비교하려면 기준 값 필요하니까 1번 치킨집을 기준으로 해보자
		Arrays.fill(res, 100);
		res[0] =0;
		for (int i = 1; i < cntHome+1; i++) {
			for (int c: chicken_comb) {
				res[i] = Math.min(res[i], distance[c][i]);
			}
		}
	}
	
}