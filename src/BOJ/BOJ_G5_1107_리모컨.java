import java.io.*;
import java.util.*;

/*
BOJ_G5_1107_리모컨

근사값 찾기

주의점) M이 0인 경우

틀림1) N:99 M:0인 경우 -로만 가는게 유리하다.
 */
public class Main {

    static int N, M, gap1;
    static boolean[] useless = new boolean[10];
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        if(M >0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int input = Integer.parseInt(token.nextToken());
                useless[input] = true;
            }
        }

        int now = 100;

        if(M==10) {
            System.out.println(Math.abs(N-now));
            return;
        }
        if(N == now) {
            System.out.println(0);
            return;
        }


        gap1 = Math.abs(N-100);
        now = findClose();
        if(now == -1) {
            System.out.println(gap1);
        }else{
            int gap2 = Math.abs(N-now);

            int length = 0;
            while ((now/=10)>0) length++;
            gap2 += ++length;

            if(gap1<gap2) System.out.println(gap1);
            else System.out.println(gap2);
        }

    }

    private static int findClose() {
        int i = 0;
        int close = -1;

        // 원본 바로 가능?
        if(isAble(N)) return N;
        // 가까운 숫자 찾기
        while (++i<gap1) {
            if(N-i>-1 && isAble(N-i)) {
                close = N-i;
                break;
            }
            if(isAble(N+i)) {
                close = N+i;
                break;
            }
        }
        return close;
    }

    private static boolean isAble(int num) {
        if(num==0 && useless[0]) return false;
        while (num>0){
            int tmp = num%10;
            if(useless[tmp]) return false;
            num /= 10;
        }
        return true;
    }
}
