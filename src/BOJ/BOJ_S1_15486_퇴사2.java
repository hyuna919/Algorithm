import java.io.*;
import java.util.*;

/*
DP문제
시간초과된 코드
 */

public class Main {
    static int N;
    static int[] finishMax; // 종료일에 따른 최대수익
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        N = Integer.parseInt(br.readLine()) + 1;
        finishMax = new int[N];

        int period, income;
        int newMax;
        for (int i = 1; i < N; i++) {
            token = new StringTokenizer(br.readLine());

            period = Integer.parseInt(token.nextToken());
            income = Integer.parseInt(token.nextToken());

            // 퇴사전에 끝낼 수 없는 상담인지
            if((i+period) > N) continue;

            // 이번 상담 종료 기준으로 : 기존 최대값 vs 이번 상담 포함 최대값
            // 이번 상담 포함 최대값 : (이번 시작일 전날까지의 최대값) + 이번 수익
//            finishMax[i+period-1] = Math.max(finishMax[i+period-1], getMax(i-1)+income);
            newMax = getMax(i-1)+income;
            if(finishMax[i+period-1] < newMax) {
                for (int j = i+period-1; j < N; j++) {
                    if(finishMax[j] >= newMax) break;
                    finishMax[j] = newMax;
                }
            }
        }

//        System.out.println(getMax(N-1));

        System.out.println(finishMax[N-1]);
    }

//    static int getMax(int day){
//        int max = 0;
//        for (int i = 0; i <= day; i++) {
//            max = (max<finishMax[i])?finishMax[i]:max;
//        }
//
//        return max;
//    }
}
