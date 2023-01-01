import java.io.*;
import java.util.*;

/*
BOJ_S1_1697_숨바꼭질
 */
public class Main {
    static int N, K, res;
    static boolean[] arr = new boolean[100_001];
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        res = bfs();
        System.out.println(res);
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{N,0});

        if(N>K) return N-K;

        while (!q.isEmpty()){
            int[] now = q.poll();
            int pos = now[0];
            int cnt = now[1];
            if(pos==K) return cnt;
            else cnt++;

            if(pos-1 >= 0 && !arr[pos-1]) {       // 걷기 x-1
                arr[pos-1] = true;
                q.add(new int[]{pos-1, cnt});
            }
            if(pos+1 <= 100_000 && !arr[pos+1]) {  // 걷기 x+1
                arr[pos+1] = true;
                q.add(new int[]{pos+1, cnt});
            }
            if(pos*2 <= 100_000 && !arr[pos*2]) {  // 순간이동 2x
                arr[pos*2] = true;
                q.add(new int[]{pos*2, cnt});
            }
        }
        return -1;
    }

}
