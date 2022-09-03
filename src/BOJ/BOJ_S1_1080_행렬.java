import java.io.*;
import java.util.*;

/*
 */
public class Main {
    static int N,M;
    static char[][] A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        A = new char[N][M];
        B = new char[N][M];

        // 행렬 A입력
        for (int i = 0; i < N; i++) A[i] = br.readLine().toCharArray();
        // 행렬 B입력
        for (int i = 0; i < N; i++) B[i] = br.readLine().toCharArray();

        // N이나 M이 3보다 작은 경우(연산을 할 필요없는 경우)
        if(N<3 || M<3) {
            if(isSameMatrix()) System.out.println(0);
            else System.out.println(-1);
            return;
        }

        // 그 외
        int cnt = 0;
        for (int n = 0, endN = N-3; n <= endN; n++) {
            // 행이 같으면 넘어간다.
            if(isSameRow(n)) continue;
            // 행이 다르면 해당 행의 열을 비교하면서 수정한다.
            for (int m = 0, endM = M-3; m <= endM; m++) {
                if(A[n][m] != B[n][m]) {
                    change(n,m);
                    cnt++;
                }
            }
            // 다 바꿨는데 행의 마지막 두 열이 안맞으면 불가능이다.
            if((A[n][M-2] != B[n][M-2]) || (A[n][M-1] != B[n][M-1])) {
                System.out.println(-1);
                return;
            }
        }
        // 다 바꿨는데 마지막 두 행이 안맞으면 불가능이다.
        if(!isSameRow(N-2) || !isSameRow(N-1)) {
            System.out.println(-1);
            return;
        }

        System.out.println(cnt);
    }

    // 3x3 부분 행렬 뒤집기
    private static void change(int n, int m) {
        for (int i = n, endI=n+3; i < endI; i++) {
            for (int j = m, endJ=m+3; j < endJ; j++) {
                if(A[i][j] == '0') A[i][j] = '1';
                else if(A[i][j] == '1') A[i][j] = '0';
            }
        }
    }

    private static boolean isSameRow(int n) {
        for (int j = 0; j < M; j++) {
            if(A[n][j] != B[n][j]) return false;
        }
        return true;
    }

    private static boolean isSameMatrix() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(A[i][j] != B[i][j]) return false;
            }
        }
        return true;
    }
}
