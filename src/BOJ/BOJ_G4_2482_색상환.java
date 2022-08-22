import java.io.*;
import java.util.*;
/*
음 점화식 어려워
 */

public class Main {
    static int N, K, MOD=1_000_000_003;
    static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        dp = new int[N+1][N+1];

        // 초기화 : 0,1개 뽑는 경우
        for (int i = 1, end=N+1; i < end; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        for (int i = 3, end=N+1; i < end; i++) {
            for (int j = 2, endJ=((i+1)/2)+1; j < endJ; j++) {
                // i번째 선택안한경우 + i번째 선택한 경우
                dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % MOD;
            }
        }

        System.out.println((dp[N-3][K-1] + dp[N-1][K])%MOD);
    }
}
