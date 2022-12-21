import java.io.*;
import java.util.*;

/*
BOJ_S2_15664_N과M(10)
 */
public class Main {

    static int N, M;
    static int[] inputs, selects;
    static Set<String> set = new HashSet<>();
    static StringBuilder builder = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        inputs = new int[N];
        selects = new int[M];

        token = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) inputs[i] = Integer.parseInt(token.nextToken());

        Arrays.sort(inputs);
        comb(0, 0);

        System.out.println(builder);

    }

    private static void comb(int r, int idx) {
        if(r==M){
            StringBuilder tmp = new StringBuilder();
            for (int s:selects) tmp.append(s+" ");
            tmp.append("\n");

            String str = tmp.toString();
            if(!set.contains(str)){
                set.add(str);
                builder.append(str);
            }
            return;
        }

        for (int i = idx; i < N; i++) {
            selects[r] = inputs[i];
            comb(r+1, i+1);
        }
    }
}
