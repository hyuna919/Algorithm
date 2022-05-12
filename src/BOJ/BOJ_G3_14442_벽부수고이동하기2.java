import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Pos {
        int x, y, cnt, skill;

        public Pos(int x, int y, int cnt, int skill) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.skill = skill;
        }

    }

    static int N, M, K;
    static char[][] map;
    static int[][][] history;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        map = new char[N][M];
        history = new int[K+1][N][M];


        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int min = move();

        System.out.println(min);
    }

    private static int move() {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(0,0,1,0));

        int nx, ny;
        Pos now;
        while (!q.isEmpty()) {
            now = q.poll();
            if(now.x==N-1&&now.y==M-1) return now.cnt;  // 도착
            for (int i = 0; i < 4; i++) {
                nx = now.x + dx[i];
                ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;    // map 범위
                if(history[now.skill][nx][ny]==1) continue;

                if (map[nx][ny] == '0') {   // 벽이 없으면
                    history[now.skill][nx][ny] = 1;
                    q.offer(new Pos(nx, ny, now.cnt + 1, now.skill));
                }else if(now.skill<K) {    // 벽이 있으면
                    history[now.skill+1][nx][ny] = 1;
                    q.offer(new Pos(nx, ny, now.cnt + 1, now.skill+1));
                }
            }
        }
        return -1;
    }
}
