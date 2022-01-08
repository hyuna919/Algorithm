

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// 36분까지
// 0804 과제
// swea 2001 파리퇴치
public class Solution0804 {
	static int N; // 파리가 있는 배열 사이즈 NxN, 5~15
	static int M; // 파리채 MxM, 2~N
	static int [][] arr; // 파리가 있는배열, 각 칸의 파리 0~30
	static int T; // 테케
	static int max = 0; // 파리를 가장 많이 잡을 때의 수
	static int nr, nc;
	static int sum;
	static int end;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//File file = new File("src/com/ssafy/combination/input.txt");
		Scanner in = new Scanner(System.in);
		
		T = in.nextInt();
		in.nextLine();
		
		// 테케 돌리기
		for (int t = 0; t <T; t++) {
			N = in.nextInt();
			M = in.nextInt();
			
			arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = in.nextInt();
				} 
			}
			
			
			// 배열
			end = N-M+1;
			max = 0;
			for (int i = 0; i < end; i++) {
				for (int j = 0; j < end; j++) {
					// 파리채
					sum = 0;
					for (int r = i; r < i+M; r++) {
						for (int c = j; c < j+M; c++) {
							sum += arr[r][c];
						}
					}
					if(max<sum) max = sum;
				}
			}
			
			// 출력
			System.out.printf("#%d %d%n", (t+1), max);
		}
		in.close();
	}	
	
}
