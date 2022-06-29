import java.io.*;
import java.util.*;

/*
최소경로문제 - 계속 시뮬이나 dp만 풀다보니 최소경로, 최소신장 문제를 잊어서 풀었다.
1회차) 다익스트라(PriorityQueue x) : 값 범위 고려 안함 + 나중에 초기화 과정 추가하면서 버스 여부를 확인하는 경로문이 틀리게됨


 */

public class Main {
    static int N, M, INF = 100_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine())+1;
        M = Integer.parseInt(br.readLine());
        StringTokenizer token;

        // 도시간 비용
        int[][] map = new int[N][N];
        // 초기화 -> 버스 비용이 0일수도 있어서 따로해줘야함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = INF;
            }
        }

        int start, end, cost;
        for (int i = 0; i < M; i++) {
            token = new StringTokenizer(br.readLine());
            start = Integer.parseInt(token.nextToken());
            end = Integer.parseInt(token.nextToken());
            cost = Integer.parseInt(token.nextToken());
            // 경로 같은 버스
            map[start][end] = (map[start][end]<cost)?map[start][end]:cost;
        }

        // 목표로 하는 출도착점
        token = new StringTokenizer(br.readLine());
        start = Integer.parseInt(token.nextToken());
        end = Integer.parseInt(token.nextToken());

        boolean[] visited = new boolean[N];
        long[] distance = new long[N];
        
        // 초기화
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        
        // 도시만큼 반복해서 최소비용찾기
        long min = 0;
        int now = 0;
        for (int i = 1; i < N; i++) {
            min = Integer.MAX_VALUE;

            // 방문x에서 최소비용 찾기
            for (int j = 1; j < N; j++) {
                if(!visited[j] && min > distance[j]) {  // 첫 반복에서 방문x+min보다 적은 비용은 distance[start]가 유일하다.
                    min = distance[j];
                    now = j;
                }
            }

            visited[now] = true;

            // 기존비용vs경유비용
            for (int j = 1; j < N; j++) {
                if(!visited[j] && map[now][j]!=INF && distance[j] > min+map[now][j]) {
                    distance[j] = min+map[now][j];
                }
            }
        }
        System.out.println(distance[end]);
    }
}
