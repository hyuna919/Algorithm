import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
쉽게 풀었다.
이번에는 그냥 종이에 문제 적어가면서 해서 문제 읽기에 시간 오래 걸렸고(20분 가까이)
그러면서 설계가 ㅇ거의 자연스럽게 완성되어서 설계에 10분정도 더해진듯?
다만 경계 연결하는게 수식 고민하는데 좀 더 걸렸다.
 */
public class Main {
    static class Node {
        int bucket;
        boolean cloud;

        public Node(int bucket, boolean cloud) {
            this.bucket = bucket;
            this.cloud = cloud;
        }
    }

    static class Position{
        int x,y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N,M;
    static Node[][] map;
    static ArrayList<Position> cloudList;
    static int[] dmx = {0,-1,-1,-1,0,1,1,1}; //이동명령용
    static int[] dmy = {-1,-1,0,1,1,1,0,-1}; //이동명령용
    static int[] dcx = {-1,-1,1,1}; //복사명령용
    static int[] dcy = {-1,1,-1,1}; //복사명령용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        map = new Node[N][N];
        // 물 입력
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = new Node(Integer.parseInt(token.nextToken()), false);
            }
        }

        cloudList = new ArrayList<>();
        // 구름 첫 위치
        cloudList.add(new Position(N-2,0));
        cloudList.add(new Position(N-2,1));
        cloudList.add(new Position(N-1,0));
        cloudList.add(new Position(N-1,1));

        // 이동 방향+거리 입력 받으면서 풀이 전개
        for (int m = 0; m < M; m++) {
            token = new StringTokenizer(br.readLine());
            int dm = Integer.parseInt(token.nextToken())-1;
            int sm = Integer.parseInt(token.nextToken());

            // 1. 구름 이동
            for (int i = 0, end= cloudList.size(); i < end; i++) {
                // 현재 구름 위치 받아서 -> 지정방향으로 이동시키기
                Position cloud = cloudList.get(i);
                cloud.x += dmx[dm] * sm;
                cloud.y += dmy[dm] * sm;
                // 경계 넘어가는 처리
                if(cloud.x < 0) cloud.x = N - ((cloud.x * -1)%N);
                if(cloud.y < 0) cloud.y = N - ((cloud.y * -1)%N);
                if(cloud.x >= N) cloud.x %= N;
                if(cloud.y >= N) cloud.y %= N;
            }


            // 2. 각 구름이 있는 칸 물 1 증가 + 구름 있음 처리
            for (int i = 0, end= cloudList.size(); i < end; i++) {
                Position cloud = cloudList.get(i);
                map[cloud.x][cloud.y].bucket++;
                map[cloud.x][cloud.y].cloud = true;
            }

            // 3. 구름이 사라진다. -> map에서는 4,5번 작업을 위해 지금 없애지 않는다.

            // 4. 물복사버그 마법
            for (int i = 0, end= cloudList.size(); i < end; i++) {
                Position cloud = cloudList.get(i);
                map[cloud.x][cloud.y].bucket += copyWater(cloudList.get(i));
            }
            // 구름 목록에서 없애기(지도에는 남아있다.)
            cloudList = new ArrayList<>();

            // 5. 물이 2 이상인 칸에 구름 생성 + 단 원래 구름이 있던 자리는 제외
            //^^ priorityQueue쓰면 시간 줄일수 있으려나
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 물이 2이상인 곳이 + 원래 구름이 없없으면
                    Node now = map[i][j];
                    if(now.bucket >= 2 && !now.cloud) {
                            cloudList.add(new Position(i,j));
                            now.bucket -= 2;
                    }
                    now.cloud = false;
                }
            }
        }

        // final. M번의 이동이 모두 끝난 후 Sum(map.bucket)
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += map[i][j].bucket;
            }
        }

        System.out.println(sum);
    }

    private static int copyWater(Position position) {
        int x = position.x;
        int y = position.y;

        int cnt = 0;
        // count(구름칸의 대각선 4방 중 물이 있는 칸)
        for (int i = 0; i < 4; i++) {
            int nx = x + dcx[i];
            int ny = y + dcy[i];

            // 경계검사
            if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
            if(map[nx][ny].bucket>0) cnt++;
        }

        return cnt;
    }
}
