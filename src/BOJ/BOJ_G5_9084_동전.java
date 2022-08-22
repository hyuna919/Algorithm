import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
DP
1차원 배낭
 */

public class Main {
    static int T,N, target;
    static int[] coins;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        T = Integer.parseInt(token.nextToken());

        StringBuilder res = new StringBuilder();
        for (int t = 0; t < T; t++) {
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            coins = new int[N];

            // 동전 금액 받기(1~10_000)
            token = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                coins[n] = Integer.parseInt(token.nextToken());
            }
            // 목표금액액
           target = Integer.parseInt(br.readLine());

            // 초기화
            dp = new int[target+1];
            dp[0] = 1;

            // 동전별
            for (int c = 0; c < N; c++) {
                for (int price = coins[c]; price <= target; price++) {
                    dp[price] += dp[price-coins[c]];
                }
            }
            res.append(dp[target]+"\n");
        }
        System.out.println(res.toString());
    }
}
