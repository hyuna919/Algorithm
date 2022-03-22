// 첫 시도를 복잡하게 하려고 하지 말자

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M, max, dir;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		int size = (N<M)?N-1:M-1;
		
		map = new char[N][M];
		String tmp;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		max = 0;
		
		
		while(size>0 && max == 0) {
			
			// 한 단계
			int now;
			for (int i = 0; i < N-size; i++) {
				for (int j = 0; j < M-size; j++) {
					now = map[i][j];
					// 모서리가 같으면
					if(now == map[i+size][j] && now==map[i][j+size] && now==map[i+size][j+size]) max = size;
						
				}
			}
			size--;
		}
		
		max++;
		
		System.out.println(max*max);
	}
}
