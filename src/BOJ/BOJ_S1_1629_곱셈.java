import java.io.*;
import java.util.*;

/*
BOJ_S1_1629_곱셈
 */
public class Main {
    static long A, B,C;

    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token =new StringTokenizer(br.readLine());
        A = Integer.parseInt(token.nextToken());
        B = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());

        A %= C;
        long res = process(B);
        System.out.println(res);
    }

    private static long process(long b) {
        if(b==1) return A%C;

        long value = process(b/2)%C;
        value *= value;
        value %= C;
        if(b%2==1) value *= A;

        return value%C;
    }
}
