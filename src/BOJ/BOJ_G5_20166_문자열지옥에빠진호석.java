import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
	static int N, M, K, max;
	static char[][] map;
	static String[] targets;
	static Map<String, Integer> counts = new HashMap<String, Integer>();
	
	static int[] dx = {-1,1,0,0,-1,-1,1,1};
	static int[] dy = {0,0,-1,1,-1,1,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());
		
		map = new char[N][M];
		targets = new String[K];
		
		// 격자정보
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 신이 좋아하는 문자열
		max = 0;
		for (int i = 0; i < K; i++) {
			targets[i] = br.readLine();
			if(targets[i].length() > max) max = targets[i].length();
		}
		
		// 모든 위치에 대해 탐색하고 수를 센다.
		StringBuilder builder;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 모든 위치에서 시작해서 최대 길이만큼 탐색
				builder = new StringBuilder();
				explore(i,j, 0, builder);
			}
		}
		
		
		// 신이 좋아하는 문자열 경우의 수
		StringBuilder res = new StringBuilder();
		int count;
		for (int i = 0; i < K; i++) {
			if(counts.containsKey(targets[i])) count = counts.get(targets[i]);
			else count = 0;
			
			res.append(count+"\n");
		}
		
		System.out.println(res.toString());
	}
	
	private static boolean explore(int x, int y, int level, StringBuilder builder) {
		// 기저조건
		if(level == max) return false;
		
		builder.append(map[x][y]);
		
		// counting
		String key = builder.toString();
		if(counts.containsKey(key)) {
			counts.put(key, counts.get(key)+1);
		}else {
			counts.put(key, 1);
		}
		
		// 이동
		int nx, ny;
		boolean flag;
		for (int i = 0; i < 8; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			
			if(nx<0) nx = N-1;
			else if(nx>=N) nx = 0;
			
			if(ny<0) ny = M-1;
			else if(ny>=M) ny = 0;
			
			flag = explore(nx,ny,level+1,builder);
			
			if(flag)builder.deleteCharAt(builder.length()-1);
		}
		
		return true;
	}
}
