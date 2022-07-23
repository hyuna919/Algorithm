import java.io.*;
import java.util.*;
/*

구현문제
오랜만의 구현이라 애를 많이 먹었다. 설계시간도 오랜걸림.

아이디어1) 구역을 구분하고 나서 각 구역별로 물채우기 -> 너무 꼬여서 더 쪼개기로 함
아이디어2) 각 레벨별로 물을 채우기 -> 채택
            맵을 계속 돌아야하긴 하지만 N이 최대 50이라 할 수 있음
            애먹은점1) 경계검사하는 부분에서 빨리 끝낼 수 있게 break를 걸었는데 이러면 이번 단계에서 방문해야하는 곳을 방문 못하게됨
            애먹은점2) flag=false를 이상한 곳에 걸어두고 있었듬

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
    static int[][] input;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        input = new int[N][M];
        visited = new boolean[N][M];

        // 수영장 입력 + 최대 높이
        int tmp, max = 0;
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                tmp = line[j]-'0';
                input[i][j] = tmp;
                max = Math.max(max, tmp);
            }
        }

        int res = 0;
        for (int lv = 2; lv <= max; lv++) {
            initVisted();
            for (int i = 1, endi=N-1; i < endi; i++) {
                for (int j = 1, endj=M-1; j < endj; j++) {
                    if(input[i][j] < lv && !visited[i][j]){
                        visited[i][j] = true;
                        res += fillWater(i,j,lv);
                    }
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

        int cnt = 1;    // 시작점도 고려
        boolean flag = false;
        while (!q.isEmpty()){
            Pos now = q.poll();
            x = now.x;
            y = now.y;
            int nx, ny;
            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                // 경계검사
                if (nx<0 || nx>=N || ny<0 || ny>=M) {
                    flag = true;
                    continue;
                }

                // 물을 채우는 경우 : 이번 레벨에서 방문x + 높이가 이번 레벨보다 낮음
                if(!visited[nx][ny] && input[nx][ny] < lv) {
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                    cnt++;
                }
            }
        }
        if(flag) cnt = 0;
        return cnt;

    }
}
