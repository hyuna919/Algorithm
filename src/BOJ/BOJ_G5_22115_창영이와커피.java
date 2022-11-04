import java.io.*;
import java.util.*;

/*
창영이와 커피
배낭
오랜만이라 생각이 잘 안났지만 혼자 해결
예외처리를 많이 달았다.(N이 작아서 안해도 되긴함)

해결 1) now = bag 복사를 미리 해야
해결 2) 예외처리간 순서 문제로 오답이 나오고 있었다.(48~60줄)
 */

public class Main {
    static int N, K, min;
    static int[] C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        min = N;

        if(K==0) {
            System.out.println(0);
            return;
        }

        boolean flag = false;
        int sum = 0;
        ArrayList<Integer> list = new ArrayList<>();
        token = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(token.nextToken());

            if(tmp>K) continue;
            else if(tmp==K) {
                flag = true;
                break;
            }
            else {
                sum += tmp;
                list.add(tmp);
            }
        }

        if(flag) {
            System.out.println(1);
            return;
        }
        if(list.isEmpty()) {
            System.out.println(-1);
            return;
        }
        if(sum<K) {
            System.out.println(-1);
            return;
        }

        int size = list.size();
        C = new int[size];
        for (int i = 0; i < size; i++) C[i] = list.get(i);

        // 배낭 초기화
        int[] now, bag = new int[K+1];
        Arrays.fill(bag, N+1);
        bag[0] = 0;

        for (int i = 0; i < size; i++) {
            now = new int[K+1];
            int caffeine = C[i];
            // 복사 : bag = now
            for (int k = 0; k <= K; k++) now[k] = bag[k];

            for (int k = caffeine; k <= K; k++) {
                // 그대로 계승 or 이번 커피 추가 중 적은 것
                now[k] = Math.min(bag[k], bag[k-caffeine] + 1);
            }
            // 복사 : bag = now
            for (int k = 0; k <= K; k++) bag[k] = now[k];
        }



        // 출력
        if(bag[K]>N) System.out.println(-1);
        else System.out.println(bag[K]);
    }


}
