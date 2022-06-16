import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int K = Integer.parseInt(token.nextToken());

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if(N%i==0) {
                cnt++;
                if(cnt==K){
                    System.out.println(i);
                    return;
                }
            }
        }

        System.out.println(0);
    }
}
