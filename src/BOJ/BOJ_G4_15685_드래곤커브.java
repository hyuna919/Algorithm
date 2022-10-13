import java.util.*;
import java.io.*;

/*
 * 드래곤커브
 */
public class Main {
	static int N,SIZE=100;
	static boolean[][] map = new boolean[SIZE+1][SIZE+1];
	static int[][] curve = {{3,0},		//->
							{0,2},		//위
							{2,1},		//<-
							{1,3}};	// 아래
	// 상하좌우
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		
		// 1. 입력받으면서 드래곤 커브 map에 그리기
		StringTokenizer token;
		int x,y,D,G;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			y = Integer.parseInt(token.nextToken());
			x = Integer.parseInt(token.nextToken());
			D = Integer.parseInt(token.nextToken());
			G = Integer.parseInt(token.nextToken());

			// 커브 경로 만들기, 0세대 초기화
			ArrayList<Integer> route = new ArrayList<>();
			route.add(curve[D][0]);
			if(G>0) {
				route.add(curve[D][1]);
				for (int g = 2; g <= G; g++) {
					for (int now = 0, length=(int) Math.pow(2, g-1); now < length; now++) {	// 2^(g-1)만큼 추가한다
						if(now<(length/2)) route.add(reverse(route.get(now)));
						else route.add(route.get(now));
					}
				}
			}
			
			// 만들어진 경로따라 map에 그리기
			map[x][y] = true; 	// 시작점
			for (int dir : route) {
				x += dx[dir];
				y += dy[dir];
				map[x][y] = true;
			}
		}
		
		
		// 2. 정사각형 수 세기
		int cnt = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	// 상하 좌우 반대로 출력
	private static int reverse(int now) {
		switch (now) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		default:
			return 2;
		}
	}
}
