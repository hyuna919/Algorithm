import java.io.*;
import java.util.*;

/*
BOJ_S1_9009_피보나치

아이디어 : 피보나치를 매번 새로 만들거나, 처음부터 최대값까지 만들지 않고, 필요한 경우 기존 피보나치 배열을 연장한다.

첫 성공 : 테케마다 f(k) = n 인 피보니치 배열 만들기
두번째 성공 : + 이진탐색
 */
public class Main {

    static int T, n;
    static ArrayList<Integer> fibo;
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        // 초기화
        fibo = new ArrayList<>();
        fibo.add(0);
        fibo.add(1);
        for (int t = 0; t < T; t++) {
            // n까지 피보나치 배열 만들기
            n = Integer.parseInt(br.readLine());
            int idx=fibo.size()-1;
            int val = fibo.get(idx);
            boolean flag = true;

            // 피보나치 연장
            if(n > val){
                flag = false;
                while (val<n) {
                    idx++;
                    val = fibo.get(idx-1) + fibo.get(idx-2);
                    fibo.add(val);
                }
            }


            // n에 제일 가깝게 작은 수
            ArrayList<Integer> list = new ArrayList<>();
            int end = fibo.size()-1;
            if(flag) end = binarySearch(idx);
            for (int j = end; j >= 0; j--) {
                val = fibo.get(j);
                if(n >= val) {
                    n -= val;
                    list.add(val);
                    if(n==0) break;
                    else if (n==1) {
                        list.add(1);
                        break;
                    }
                }
            }

            // 출력
            end = list.size();
            for (int j = end-1; j >= 0; j--) {
                builder.append(list.get(j) + " ");
            }
            builder.append("\n");
        }

        System.out.println(builder);
    }

    private static int binarySearch(int maxIdx) {
        int l =0, r=maxIdx, m=0;
        while(l<=r){
            m = (l+r)/2;
            if(n < fibo.get(m)) r = m-1;
            else l = m+1;
        }
        return m;
    }
}
