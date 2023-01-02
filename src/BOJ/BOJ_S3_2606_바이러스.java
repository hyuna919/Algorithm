import java.io.*;
import java.util.*;

/*
BOJ_S3_2606_바이러스
 */
public class Main {
    static int N, M;
    static boolean[] isVirus;
    static boolean[][] links;
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        isVirus = new boolean[N+1];
        links = new boolean[N+1][N+1];
        for (int i = 0; i < M; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            links[a][b] = true;
            links[b][a] = true;
        }

        // 1. 감염
        isVirus[1] = true;
        process(1);

        // 결과
        int cnt = -1;
        for(boolean i:isVirus) if(i) cnt++;
        System.out.println(cnt);
    }

    private static void process(int master) {
        for (int i = 1; i <= N; i++) {
            if(i==master) continue;
            if(isVirus[i]) continue;    // 이미 감염된 곳은 패스
            if(links[master][i]) {      // 연결관계
                isVirus[i] = true;
                process(i);
            }
        }
    }
}
