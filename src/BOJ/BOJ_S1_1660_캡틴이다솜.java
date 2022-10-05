import java.io.*;
import java.util.*;

/*
캡틴 이다솜
DP문제

 */

public class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        pyramid(N);

        int[] dp = new int[N+1];
        Arrays.fill(dp, N);

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2,end = N+1; i < end; i++) {
            for (int j = 1, endj=list.size(); j < endj; j++) {
                if(list.get(j)>i) break;
                dp[i] = Math.min(dp[i], dp[i-list.get(j)]+1);
            }
        }

        System.out.println(dp[N]);
    }

    // list:정사면체별 구슬 개수
    private static void pyramid(int n) {
        int start = 0, layer = 0, total = 0;
        while (total<=n){
            list.add(total);
            start++;
            layer += start;
            total += layer;
        }

        return;
    }
}
