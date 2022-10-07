import java.io.*;
import java.util.*;

/*
암기왕
Set

 */

public class Main {
    static int T, N,M;
    static Set<Integer> memo1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        StringBuilder builder = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        while(T-->0){
            memo1 = new HashSet<>();

            // 수첩1 -> memo1
            N = Integer.parseInt(br.readLine());
            int input;
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                input = Integer.parseInt(token.nextToken());
                memo1.add(input);
            }


            // 수첩2 -> memo1에서 검색해서 builder에 결과
            M = Integer.parseInt(br.readLine());
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                input = Integer.parseInt(token.nextToken());
                if(memo1.contains(input)) builder.append(1+"\n");
                else builder.append(0+"\n");
            }
        }


        System.out.println(builder.toString());
    }
}
