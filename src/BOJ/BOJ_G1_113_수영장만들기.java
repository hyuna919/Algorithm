import java.io.*;
import java.util.*;
/*
디버깅중...

구현문제

 */

public class Main {
    static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static int[][] input, level;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        input = new int[N][M];
        level = new int[N][M];
        visited = new boolean[N][M];

        // 수영장 입력 + 최대 높이
        int tmp, max = 0;
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                tmp = line[j]-'0';
                input[i][j] = tmp;
                level[i][j] = tmp;
                max = Math.max(max, tmp);
            }
        }

        int res = 0;
        max++;
        for (int lv = 1; lv < max; lv++) {
            initVisted();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    res += fillWater(i,j,lv);
                    visited[i][j] = true;
                }
            }

        }

        System.out.println(res);

    }

    private static void initVisted() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = false;
            }
        }

    }

    private static int fillWater(int x, int y, int lv) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(x,y));

        int cnt = 0;
        boolean flag = false;
        while (!q.isEmpty()){
            Pos now = q.poll();
            x = now.x;
            y = now.y;
            flag = false;
            int nx, ny;
            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                // 경계검사
                if (nx<0 || nx>=N || ny<0 || ny>=M) {
                    flag = true;
                    break;
                }

                // 물을 채우는 경우 : 이번 레벨에서 방문x + 높이가 이번 레벨보다 낮음
                if(!visited[nx][ny] && input[nx][ny] <= lv) {
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                    cnt++;
                }
            }
            if(flag) break;
        }
        if(flag) cnt = 0;
        return cnt;

    }
}
