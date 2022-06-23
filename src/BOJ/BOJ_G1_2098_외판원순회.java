import java.io.*;
import java.util.*;

/*
* 후... 설계 열심히했는데 구현에서 자꾸 실수한다.
*/


public class Main {
    static int N, min = Integer.MAX_VALUE;
    static int[][] W, D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        W = new int[N][N];
        D = new int[(1<<N)][N];

        // 경로 입력
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        // 채우기
        int stop = (1<<N);
        for (int i = 0; i < stop; i++) {
            for (int j = 0; j < N; j++) {
                D[i][j] = 20_000_000;
            }
        }

        // 순회
        int min = travel(1<<0,0);



        System.out.println(min);

    }

    private static int travel(int visit, int now) {
        if(D[visit][now] != 20_000_000){
            return D[visit][now];
        }

        if(visit==(1<<N)-1){
            if(W[now][0]==0) return -1;
            else return W[now][0];
        }

//        D[visit][now] = 987654321;

        for (int i = 0; i < N; i++) {
            if((visit&1<<i)==0 && (W[now][i] != 0)) {
                int value = travel(visit|1<<i, i);
                if(value != -1) {
                    value += W[now][i];
                    D[visit][now] = Math.min(D[visit][now], value);
                } else {
                    D[visit][now] = 16_000_001;
                }
            }
        }

        return D[visit][now];
    }
}
