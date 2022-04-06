import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 건너 친구까지를 포함해서 친구가 가장 많은 경우를 찾는 문제
* 구분을 위해 1-친구와 2-친구로 구분함
* 입력을 통해 1-친구를 기록하고
* 완탐으로 1-친구의 1-친구를 2-친구로 기록함
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine())+1;
		int[][] map = new int[N][N];
		
		
		// 1-친구 기록
		String tmp;
		for (int i = 1; i < N; i++) {
			tmp = br.readLine();
			for (int j = 1; j < N; j++) {
				if(tmp.charAt(j-1)=='Y') map[i][j] = 1;
			}
		}
		
		
		// 2-친구 기록
		int max = 0, now;
		for (int i = 1; i < N; i++) {
			now = 0;
			for (int j = 1; j < N; j++) {
				if(map[i][j]==1) {
					now++;	//1-친구
					for (int k = 1; k < N; k++) {
						if(map[j][k]==1) {
							if(map[i][k]==0 && i!=k) {
								map[i][k]=2;
								now++;
							}
						}
					}
					
				}
			}
			if(max<now) max=now;
		}
		
		System.out.println(max);
	}
}
