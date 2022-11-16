import java.io.*;
import java.util.*;

/*
행렬 제곱

감상) 최악이 1_000_000_000_000이라 약간 갸웃함

풀이1) DP(Map을 이용한 메모이제이션), Top-Down

틀림1) 입력받을때 B를 습관적으로 Integer.parseInt()로 받음
틀림2) static한 행렬을 tmp에서 matrix로 변경하면서 초기화를 안함
 */

public class Main {
    static int N, MOD=1_000;
    static int[][] A, matrix;
    static Map<Long, int[][]> map;
    static long B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        B = Long.parseLong(token.nextToken());

        // 행렬A 입력
        A = new int[N][N];
        int input;
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                input = Integer.parseInt(token.nextToken());
                A[i][j] = input;
            }
        }

        // A->tmp 복사
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for(int j=0; j < N; j++){
                matrix[i][j] = A[i][j]%MOD;
            }
        }
        map = new HashMap<>();
        map.put((long)1, matrix);
        dp(B);

        // 답 출력
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                builder.append(matrix[i][j]+" ");
            }
            builder.append("\n");
        }

        System.out.println(builder.toString());

    }

    private static void dp(long b) {
        if(b==0 || b==1) return;

        // 구하기
        long key = b/2;
        if(!map.containsKey(key)) dp(key);


        // A^(b/2)를 제곱
        int[][] tmp = new int[N][N];
        matrix = map.get(key);
        for (int i = 0; i < N; i++) {
            for(int j=0; j < N; j++){
                tmp[i][j] = solve(i,j, false);
            }
        }

        // 제곱 결과 복사
        for (int i = 0; i < N; i++) {
            for(int j=0; j < N; j++){
                matrix[i][j] = tmp[i][j];
            }
        }

        map.put(key*2, matrix);

        // b가 홀수면 A를 한번 더 곱해야함
        if(b!=(key*2)) {
            for (int i = 0; i < N; i++) {
                for(int j=0; j < N; j++){
                    tmp[i][j] = solve(i,j, true);
                }
            }

            // 행렬곱 결과 복사
            for (int i = 0; i < N; i++) {
                for(int j=0; j < N; j++){
                    matrix[i][j] = tmp[i][j];
                }
            }

            map.put(b, matrix);
        }
    }

    private static int solve(int i, int j, boolean a) {
        int value = 0;
        if(a){
            for (int k = 0; k < N; k++) {
                value += (matrix[i][k] * A[k][j])%MOD;
            }
        }else {
            for (int k = 0; k < N; k++) {
                value += (matrix[i][k] * matrix[k][j])%MOD;
            }
        }
        return value%MOD;
    }
}
