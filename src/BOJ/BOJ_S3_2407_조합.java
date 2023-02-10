import java.io.*;
import java.util.*;

/*
BOJ_S3_2407_조합
(주의)
세미 소인수분해로 풀이
custom 자리수
 */
public class Main {
    static int n, m;
    static int[] primeArr = {2,3,5,7,11,13,17,19,23};
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());

        int gap = n-m;
        int start = 0;
        int left;
        if(gap<m) {
            start = m+1;
            left = gap;
        }else {
            start = gap+1;
            left = m;
        }

        int[] arr = new int[n+1];

        for (int i = start; i <= n; i++) {
            int value = i;
            for(int prime:primeArr){
                while(value%prime==0) {
                    arr[prime]++;
                    value = value / prime;
                }
            }
            arr[value]++;
        }


        for (int i = 2; i <= left; i++) {
            int value = i;
            for(int prime:primeArr){
                while(value%prime==0) {
                    arr[prime]--;
                    value = value / prime;
                }
            }
            arr[value]--;
        }

        long over = 0;
        long max = 100_000_000_000_000L;
        long res = 1;
        for (int i = 2; i <= n ; i++) {
            if(arr[i]==0) continue;
            double tmp = Math.pow(i,arr[i]);
            over *= tmp;
            res *= tmp;
            if(res>max){
                over += res/max;
                res %= max;
            }
        }

        // custom 자리수
        if(over!=0){
            System.out.print(over);
            while(res<(max/10)) {
                System.out.print(0);
                max /= 10;
            }
        }
        System.out.print(res);
    }
}
