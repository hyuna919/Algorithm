package day_0819;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// dfs로 처리 + 메모이제이션ver
// visited배열로 따로 방문처리하지 않고
// map자체에 해당 위치끼지 오는 최소 거리 적어서 방문처리+가지치기용 최소값 저장
public class Main_1661_미로탈출로봇_dfs2 {
	static int[][] map;
	static int colN, rowN, sr,sc, er, ec;
	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	public static void main(String[] args)	throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		in = new BufferedReader(new StringReader(inputData));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		colN = Integer.parseInt(st.nextToken());
		rowN = Integer.parseInt(st.nextToken());
		
		map = new int[rowN][colN];
		
		st = new StringTokenizer(in.readLine());
//		좌표가 1,1부터 시작하는 좌표이므로  -1씩 해서 0,0에서 시작하는 좌표로 변경
		sc = Integer.parseInt(st.nextToken())-1;
		sr = Integer.parseInt(st.nextToken())-1;
		ec = Integer.parseInt(st.nextToken())-1;
		er = Integer.parseInt(st.nextToken())-1;
		
		
//		맵 정보 입력 받기 
		for (int i = 0; i < rowN; i++) {
			String line = in.readLine();
			for (int j = 0; j < colN; j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		
		// 출발지 방문체크
		map[sr][sc] = 1;

		dfs(sr, sc);
		
		System.out.println(map[er][ec]-1);
	}
	private static void dfs(int r, int c) {
		// 도착지점 도달(기저)
		if(r == er && c == ec) {
			return;
		}
		
		// 인접경로 탐색
		int nr = 0, nc = 0;
		int dist = map[r][c];
		for (int i = 0; i < 4; i++) {
			nr = r+dr[i];
			nc = c+dc[i];
			if( nr>-1 && nr<rowN && nc>-1 && nc<colN	// 경계체크
					&& (map[nr][nc]==0 || map[nr][nc] > dist+1)){		// 안가본길 + 이전에 가봤지만 더 짧은 동선(dist)
				 map[nr][nc] = dist+1;
				dfs(nr, nc);
			}
		}
		
		
	}
	static String inputData="8 7\r\n" + 
			"1 2 7 5\r\n" + 
			"11111111\r\n" + 
			"00000111\r\n" + 
			"10110011\r\n" + 
			"10111001\r\n" + 
			"10111101\r\n" + 
			"10000001\r\n" + 
			"11111111";
//	static String inputData="5 5\r\n" + 
//			"1 1 5 5\r\n" + 
//			"00000\r\n" + 
//			"01101\r\n" + 
//			"00100\r\n" + 
//			"01110\r\n" + 
//			"00000";
}
