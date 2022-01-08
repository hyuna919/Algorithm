package day_0916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_9205 {
	static class Place {
		int x, y;

		public Place(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		ArrayList<Place> list = new ArrayList<Place>();
		int[][] map;
		boolean[][] LIS;
		int N;
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			list = new ArrayList<Place>();
			
			N = Integer.parseInt(in.readLine());
			map = new int[N+2][N+2]; // 집1 + 편의점N + 페스티벌1
			LIS = new boolean[N+2][N+2]; // 집1 + 편의점N + 페스티벌1
			
			// 입력
			for (int i = 0; i < N+2; i++) {
				token = new StringTokenizer(in.readLine());
				list.add(new Place(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken())));
			}
			
			// 맨해튼 거리
			int x1,y1,x2,y2;
			for (int i = 0; i < N+2; i++) {
				for (int j = 0; j < N+2; j++) {
					x1 = list.get(i).x;
					y1 = list.get(i).y;
					x2 = list.get(j).x;
					y2 = list.get(j).y;
					
					map[i][j] = Math.abs(x1-x2)+Math.abs(y1-y2);
					
					if(map[i][j]<=1000) LIS[i][j] = true;
				}
			}
			
			//플로이드 워샬
			for (int k = 0; k < N+2; k++) {
				for (int i = 0; i < N+2; i++) {
					for (int j = 0; j < N+2; j++) {
						if(LIS[i][k] && LIS[k][j]) LIS[i][j] = true;
					}
				}
			}
			
			System.out.println(LIS[0][N+1]?"happy":"sad");
		}
	}
}
