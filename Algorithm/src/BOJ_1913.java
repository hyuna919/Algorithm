

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1913 {
	static int N, sub_N;
	static int number;
	static int[][] arr;
	static int r, c;

	public static void main(String[] args) throws NumberFormatException, IOException {
//		FileReader file = new FileReader("src/hwalgo/input.txt");
//		BufferedReader in = new BufferedReader(file);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			sub_N = N;
			number = N*N;
			arr = new int[N][N];
			r = -1;
			c = 0;
			while(number>0) {
				//하
				for(int i = 0; i<N; i++) {
					arr[++r][c] = number--;
				}
				N--;
				//우
				for(int i = 0; i<N; i++) {
					arr[r][++c] = number--;
				}
				//상
				for(int i = 0; i<N; i++) {
					arr[--r][c] = number--;
				}
				N--;
				//좌
				for(int i = 0; i<N; i++) {
					arr[r][--c] = number--;
				}
			}
			System.out.println("#"+(t+1));
			for (int i = 0; i < sub_N; i++) {
				for (int j = 0; j < sub_N; j++) {
					System.out.printf("%d ",arr[i][j]);
				}
				System.out.println();
			}
				
		}
		
	}
	
}
