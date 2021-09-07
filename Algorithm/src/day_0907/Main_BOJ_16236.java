package day_0907;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_16236 {
	static int N, sharkSize=2, sharkEat, cntFish, x, y, cnt;
	static int[][] map;
	static ArrayList<int[]> fishes = new ArrayList<>();
	static ArrayList<int[]> canList = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];

		// 공간 입력 및 물고기 위치, 상어 위치 확인
		int tmp;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				tmp = Integer.parseInt(token.nextToken());
				map[i][j] = tmp;
				if(tmp==0) {	// 빈공간
					continue;
				}else if(tmp==9) {	// 상어
					x=i;
					y=j;
				}else if(tmp<2) {	// 먹을 수 있는 물고기
					cntFish++;
					canList.add(new int[] {i,j});
				}else {				// 먹을 수 없는 물고기
					cntFish++;
					fishes.add(new int[]{i,j});
					
				}
			}
		}
		
		// 먹을 수 있는 물고기 목록 돌면서 제일 가까운 것 찾기 
		if(cntFish>0) {
			int dist;
			int[] fish;
			int min = Integer.MAX_VALUE;
			int min_idx=-1;
			for (int i = 0; i < cntFish; i++) {
				
				move(i);
				
				if(sharkSize==sharkEat) levelup();
			}
		}
		
		
		System.out.println(cnt);
		
		
		
		
		
		
		
		
	}

	private static void move(int i) {
		int r = x, c = y;
		
		
	}

	private static void levelup() {
		sharkEat = 0;
		sharkSize++;
		for (int[] is : fishes) {
			if(map[is[0]][is[1]] < sharkSize) canList.add(is);
		}
	}

}
