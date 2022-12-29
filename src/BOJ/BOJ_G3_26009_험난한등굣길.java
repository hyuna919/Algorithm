import java.io.*;
import java.util.*;

/*
BOJ_G3_26009_험난한등굣길

시간초과 -> 길찾아가는건 문제 없는데 정체지점에서 시간초과가 난다.
시도1) 일반 bfs
시도2) 한줄씩 정체구간 체크
시도3) 정체구간 외곽만 체크(정체구간을 다 칠하는 순간 시간초과 -> 길이 3000짜리 구간이 3000개 있을 수도 있음) -> 성공 
 */
public class Main {
    static class Node {
        int r, c, d;

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static int N, M, K;
    static int[][] map;
    static boolean[][] jamMap;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(br.readLine());
        map = new int[N][M];
        jamMap = new boolean[N][M];

        // 0-1. 정체구간 입력
        for (int k = 0; k < K; k++) {
            token = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(token.nextToken())-1;
            int c = Integer.parseInt(token.nextToken())-1;
            int d = Integer.parseInt(token.nextToken());
            checkTrafficJam(r,c,d);
        }

        // 학교로 이동
        int res = goSchool();

        // 결과
        if(res==-1) System.out.println("NO");
        else {
            System.out.println("YES");
            System.out.print(res);
        }

    }

    private static int goSchool() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,0));
        map[0][0] = 1;

        while (!q.isEmpty()){
            Node now = q.poll();
            int nd = now.d+1;
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                if(nr<0 || nr>=N || nc<0 || nc>= M) continue;
                if(jamMap[nr][nc]) continue;
                if(map[nr][nc] == 1) continue;
                else map[nr][nc] = 1;

                // 도착
                if(nr==N-1 && nc==M-1) return nd;
                else q.add(new Node(nr,nc,nd));
            }
        }
        return -1;
    }

    // bfs -> 모양
    private static void checkTrafficJam(int r, int c, int d) {
        jamMap[r][c] = true;

        for (int i = d; i >0; i--) {
            if(0 <= c-i && c-i < M) checkJamOutline(r, c-i, d-i);
            if(0 <= c+i && c+i < M) checkJamOutline(r, c+i, d-i);
        }
        checkJamOutline(r, c, d);
    }

    private static void checkJamOutline(int r, int c, int d) {
        if(r-d>=0) jamMap[r-d][c] = true;
        if(r+d<N) jamMap[r+d][c] = true;
    }

}
