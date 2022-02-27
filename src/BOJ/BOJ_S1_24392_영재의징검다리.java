import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_24392_영재의징검다리 {
	
	static char[][] map;
	static long[][] record;
	static int N, M;
	static long cnt;
	
	// 인접한 최대 3개의 유리
	static int[] dy = {-1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		// map
		map = new char[N][M];
		record = new long[N][M];
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
//				map[i][j] = Integer.parseInt(token.nextToken());
				map[i][j] = token.nextToken().charAt(0);
			}
//			map[i] = br.readLine().toCharArray();
		}
		
		for (int j = 0; j < M; j++) {
			if(map[N-1][j]=='1') record[N-1][j] = 1;
		}
		
		
		// 탐색
		for (int i = N-1; i > 0; i--) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]!='0') count(i,j);
			}
		}
		
		// 합
		for (int i = 0; i < M; i++) {
			cnt += record[0][i];
		}
		
		System.out.println(cnt%1000000007);
	}
	
	static void count(int x, int y) {
		for (int i = 0; i < 3; i++) {
			int ny = y + dy[i];
			
			if(ny<0 || ny>=M) continue;
			
			// 일반유리면
			if(map[x-1][ny]=='0') continue;
			
			record[x-1][ny] += (record[x][y]%1000000007);
		}
	}

	static int readI() throws Exception {
        int n = 0, c;
        while ((c = System.in.read()) > 47)
            n = n * 10 + c - 48;
        return n;
    }
	
}
