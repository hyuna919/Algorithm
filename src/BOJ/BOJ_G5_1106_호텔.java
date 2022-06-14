import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(token.nextToken());
        int N = Integer.parseInt(token.nextToken());
        int[] D = new int[C+101];
        Arrays.fill(D, 100000);
        D[0] = 0;

        int cost, customer;

        for (int i = 0; i < N; i++) {
            token= new StringTokenizer(br.readLine());
            cost = Integer.parseInt(token.nextToken());
            customer = Integer.parseInt(token.nextToken());
            for (int j = customer; j < C+101; j++) {
                D[j] = Math.min(D[j], D[j-customer]+cost);
            }
        }


        int res = Integer.MAX_VALUE;
        for(int i=C; i<C+101;i++){
            res = Math.min(res,D[i]);
        }

        System.out.println(res);

    }
}
