import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S4_1049_기타줄 {
	
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		int minSet = Integer.MAX_VALUE;
		int minEach = Integer.MAX_VALUE;
		int nowSet, nowEach;
		for (int i = 0; i < M; i++) {
			token = new StringTokenizer(br.readLine());
			nowSet = Integer.parseInt(token.nextToken());
			nowEach = Integer.parseInt(token.nextToken());
			
			minSet = minSet<nowSet?minSet:nowSet;
			minEach = minEach<nowEach?minEach:nowEach;
		}
		
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < M; i++) {
			// 묶음 가격 계산
			res = (N/6)*minSet;
			
			// 묶음 구매시 낱개가 있다면
			if((N%6) > 0) {
				// 묶음 계산시 나머지 계산(새 묶음 vs 낱개)
				if(minSet < (N%6)*minEach) res += minSet;
				else res += (N%6)*minEach;
			}

			// 낱개 가격과 비교
			res = (res<(N*minEach))?res:(N*minEach);
		}
		
		
		System.out.println(res);
	}

}
