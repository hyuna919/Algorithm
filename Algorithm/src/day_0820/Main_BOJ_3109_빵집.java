package day_0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_3109_빵집 {
	static int R, C, cntLine;
	static char[][] map;
	static int[] dr = {-1, 0 ,1};
	public static void main(String[] args) throws IOException {
		BufferedReader in=  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(token.nextToken());
		C = Integer.parseInt(token.nextToken())-1;
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		
		for (int i = 0; i < R; i++) {
			if(pipeLine(i, 0)) cntLine++;
		}
		System.out.println(cntLine);
	}
	
	private static boolean pipeLine(int r, int c) {
		if(c++==C-1) {
			return true;
		}
		
		// 유도
		int nr;
		for (int i = 0; i < 3; i++) {
			nr = r+dr[i];
			if(nr>-1 && nr<R && map[nr][c] != 'x') {	// 경계값검사 +막히지 않았으면
				map[nr][c] = 'x';
				if(pipeLine(nr, c)) return true;
			}
		}
		return false;
	}
}
