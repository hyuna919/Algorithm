package day_0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_G5_14500 {
	static int N, M, MAX=0;
	static int[][] map;
	
	// base가 될 중심도형 4개 - 가로3개, ㄴ , 역ㄴ, 세로 3개
	static int[] ax = {0, 0, 0}; 
	static int[] ay = {0, 1, 2};
	static int[] bx = {0, 1, 1}; 
	static int[] by = {0, 0, 1};
	static int[] cx = {0, 1, 1}; 
	static int[] cy = {1, 0, 1};
	static int[] ex = {0, 1, 2}; // d는 delta랑 겹치는 관계로 쓰지않음
	static int[] ey = {0, 0, 0};
	
	// 각 중심도형에 더할 한조각
	static int[] adx = {1, 1, 1, 0, -1};
	static int[] ady = {0, 1, 2, 3, 2};
	static int[] bdx = {0, 1, 2, 2, 1, 0, -1};
	static int[] bdy = {-1, -1, 0, 1, 2, 1, 0};
	static int[] cdx = {-1, 0, 1, 2, 2};
	static int[] cdy = {1, 2, 2, 1, 0};
	static int[] edx = {0, 3, 0};
	static int[] edy = {-1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		map = new int[N][M];
		
		// 맵 입력
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		getMax(M-2, N, 'a');
		getMax(M-1, N-1, 'b');
		getMax(M-1, N-1, 'c');
		getMax(M, N-2, 'd');

		System.out.println(MAX);
	}
	
	static void getMax(int m, int n, char type) {
		int[] x, y, dx, dy;
		if(type=='a') {
			x = ax; y = ay;
			dx = adx; dy = ady;
		}else if(type=='b') {
			x = bx; y = by;
			dx = bdx; dy = bdy;
		}else if(type=='c') {
			x = cx; y = cy;
			dx = cdx; dy = cdy;
		}else{
			x = ex; y = ey;
			dx = edx; dy = edy;
		}
		
		int len = dx.length;
		int nx, ny, localMax, tmp, base;
		for (int i = 0; i <n; i++) {
			for (int j = 0; j < m; j++) {
				// 해당 위치 기준 a도형의 합
				base = 0;
				for (int k = 0; k < 3; k++) {
					nx = i+x[k];
					ny = j+y[k];
					if(nx>-1 && nx<N && ny>-1 && ny<M) { // 경계검사
						base += map[nx][ny];
					}			
				}
				
				localMax = 0;
				for (int k = 0; k < len; k++) { // 각 추가칸 값 비교해서 제일 큰 것이 localMax
					nx = i+dx[k];
					ny = j+dy[k];
					if(nx>-1 && nx<N && ny>-1 && ny<M) { // 경계검사
						tmp = map[nx][ny];
						localMax = (localMax < tmp)? tmp : localMax;
					}			
				}
				
				// 기준위치 i,j까지 중에서 가장 큰 값
				tmp = base + localMax;
				MAX = (MAX<tmp)?tmp:MAX;
			}
		}
	}
}