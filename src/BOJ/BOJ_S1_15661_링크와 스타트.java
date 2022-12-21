import java.io.*;
import java.util.*;

/*
BOJ_S1_15661_링크와 스타트

아이디어
- 조합
 */
public class Main {

    static int N, min = Integer.MAX_VALUE;
    static boolean[] teamS;
    static int[][] S;
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        teamS = new boolean[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        // 1. 조합 (각 팀 최소 1명 -> 최대 N-1명)
        int end = (N/2)+1 + (N%2);
        for (int r = 1; r < end; r++) comb(N, r);

        System.out.println(min);

    }

    private static void comb(int n, int r) {
        if(r==0){
            compareScore();
            return;
        }
        if(n<r) return;

        teamS[n-1] = true;
        comb(n-1, r-1);
        teamS[n-1] = false;
        comb(n-1, r);
    }

    private static void compareScore() {
        // 스타트팀 점수
        int sScore = 0;
        for (int i = 0; i < N; i++) {
            if(!teamS[i]) continue;
            for (int j = i+1; j < N; j++) {
                if(teamS[j]) sScore += S[i][j] + S[j][i];
            }
        }
        // 링크팀 점수
        int lScore = 0;
        for (int i = 0; i < N; i++) {
            if(teamS[i]) continue;
            for (int j = i+1; j < N; j++) {
                if(!teamS[j]) lScore += S[i][j] + S[j][i];
            }
        }
        // min이랑 비교
        min = Math.min(min, Math.abs(sScore-lScore));
    }
}
