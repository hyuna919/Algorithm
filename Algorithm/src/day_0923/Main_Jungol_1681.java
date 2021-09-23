package day_0923;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Jungol_1681 {	
	static int N, res=Integer.MAX_VALUE;
	static int[][] adjMatrix;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		adjMatrix = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		perm(0,0,0,0);
		
		System.out.println(res);
	}

	 private static void perm(int cnt, int flag, int now, int sum) {
        if(cnt==N-1) {
            if(adjMatrix[now][0]==0) return;
            res = Math.min(res, sum+adjMatrix[now][0]);
            return;
        }
        
        if(sum>=res) return;
         
        for (int i = 1; i < N; i++) {
            if(now!=i && (flag&1<<i)==0 && adjMatrix[now][i]!=0) {
                perm(cnt+1, flag|1<<i,i,sum+adjMatrix[now][i]);
            }
        }
    }
	
}
