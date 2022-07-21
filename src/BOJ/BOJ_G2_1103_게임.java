import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. bfs풀이
문제를 보고 bfs로 풀면 편하겠다 싶었는데 무한루프(-1) 경우 때문에 어렵겠다 싶었음
2. dfs+dp
무한루프는 이번 경로에서 갔던 곳 또 가는거니까 dfs
이전 경로에서 했던 걸 반복할 필요 없으니까 dp

 */

public class Main {
    static int N, M, max;
    static char[][] map;
    static int[][] dp;
    static boolean[][] visited;
    static boolean flag = false;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        dp = new int[N][M];

        // map
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visited[0][0] = true;
        dfs(0,0,1);

        if(flag) System.out.println(-1);
        else System.out.println(max);
    }

    private static void dfs(int x, int y, int cnt) {
        max = (max<cnt)?cnt:max;
        dp[x][y] = cnt;

        int nx, ny;
        int distance = map[x][y]-'0';
        for (int i = 0; i < 4; i++) {
            nx = x + (dx[i] * distance);
            ny = y + (dy[i] * distance);

            // 경계검사
            if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
            // 구멍검사
            if(map[nx][ny] == 'H') continue;
            // 이번 경로에서 방문 검사
            if(visited[nx][ny]) {
                flag = true;
                return;
            }
            // 다른 경로에서 방문 검사
            if(dp[nx][ny] > cnt) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, cnt+1);
            visited[nx][ny] = false;
        }
    }
}
