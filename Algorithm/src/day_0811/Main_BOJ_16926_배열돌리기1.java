package day_0811;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Main_BOJ_16926_배열돌리기1 {
	static int X, Y, R;
	static int [][] arr;
	
	// 우하좌상 상좌하우
	static int[] dr = new int[] {-1,0,1,0};
	static int[] dc = new int[] {0,-1,0,1};

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// 경계값 설정 및 배열 설정
		StringTokenizer token = new StringTokenizer(in.readLine());
		X = Integer.parseInt(token.nextToken());
		Y = Integer.parseInt(token.nextToken());
		R = Integer.parseInt(token.nextToken());
		
		arr = new int[X][Y];
		
		// 배열 값 넣기
		for (int i = 0; i < X; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < Y; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		rotation(0,X,Y);
		
		// 출력
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				builder.append(arr[i][j]+" ");
			}
			builder.append("\n");
		}
		System.out.println(builder);

	}
	
	private static void rotation(int min, int maxX, int maxY) {
		
		// 둘 다 0
		if(maxX == min || maxY ==min) return;
		
		// 둘 다 0 아님(반시계)
		// 회전
		for (int t = 0; t < R; t++) {
			int r = (min<maxX)?min:(X/2);
			int c = (min<maxY)?min:(Y/2);
			int tmp = arr[r][c];
			
			int dir = 3;
			while(dir>=0) {	// 뒤에서부터면 
				int nr = r+dr[dir];
				int nc = c+dc[dir];
				// 경계값 확인
				if(nr>min-1 && nr<maxX && nc>min-1 && nc<maxY) {
					arr[r][c] = arr[nr][nc];
					r = nr;
					c = nc;
				}else {
					dir--;
				}
			}
			arr[r+1][c] = tmp;
			
		}
		rotation(++min, maxX-1,maxY-1);
	}
}
