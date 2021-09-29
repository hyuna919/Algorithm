package day_0929;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 *  디저트 카페
 *  
 *  마름모꼴로 돌아야하는데 도는 방향은 상관없는듯
 * 
 */
public class Solution_swea_2105 {
	static int T, N, max, eat;
	static int[][] map;
	
	// 12시 위치부터 시계방향
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		
		T = Integer.parseInt(in.readLine());
		for (int t = 1; t < T+1; t++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			max = 0;
			
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(in.readLine());
				for (int j = 0; j < 4; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}				
			}
			
			for (int i = 0, end = N-1; i < end; i++) {
				for (int j = 0; j < end; j++) {
					dfs(i,j,i,j,1<<map[i][j],0,0);
				}
			}
			
			
			max = (max==0)?-1:max;
			builder.append("#"+t+" "+max+"\n");
		}
		System.out.println(builder);
	}
	private static void dfs(int x, int y, int nx, int ny, int flag, int cnt, int dir) {
		// 한바퀴돌아서 시작점 직전칸에 오면
		if(x-1==nx && y-1==ny) {
			max = Math.max(max, cnt);
			return;
		}
		
		
		
		int nnx, nny;
		int end = dir+2;
		end = (end>4)?4:end;
		for (int i = dir; i < end; i++) {
			nnx = nx + dx[i]; 
			nny = ny + dy[i];
			
			// 마지막 회전시 여기서 꺽어봐야 출발지로 돌아올 수 없는 경우
			if(i==3 && x+y != nnx+nny) return;
						
			// 경계
			if(nnx<0 || nnx>=N || nny<0 || nny>=N) continue;
			
			// 이미 먹은 음식이면, 아니면 추가
			if((flag&1<<map[nnx][nny])!=0) continue;
			flag |= 1<<map[nnx][nny];
			
			dfs(x,y,nnx,nny,flag,cnt+1,i);
		}
		
	}
}
