import java.io.*;
import java.util.*;

/*
상어 이동때 초기화를 잘못해서 시간을 많이 썼다.
->초기화를 0으로 하니까 모든 경로에서 잡을 물고기가 없는 경우 이동하지 않는 문제가 생겨서 -1로 초기화함.
 */
public class Main {
    static class Shark {
        int x, y;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N = 4, M, S, maxRouteCnt, maxRoute;
    static ArrayList<Integer>[][] map, moveMap, copyMap;
    static int[][] smellMap;
    static Shark shark;

    // 물고기 이동(제시된 방향순서 따름, 1부터)
    static int[] dfx = {0,0,-1,-1,-1,0,1,1,1};
    static int[] dfy = {0,-1,-1,0,1,1,1,0,-1};
    // 상어 이동 + 사전순처리 때문에 1부터 시작하도록 처리 => 상좌하우
    static int[] dsx = {0,-1,0,1,0};
    static int[] dsy = {0,0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        M = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());

        smellMap = new int[N][N]; // 4x4짜리 맵이라 물고기 냄새를 따로 관리 안하고 맵 완탐하기로
        moveMap = new ArrayList[N][N]; // 초기화는 매번한다.
        map = new ArrayList[N][N]; // 4x4짜리 맵이라 ArrayList맵으로
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        // 물고기 위치
        int x, y, d;
        for (int i = 0; i < M; i++) {
            token = new StringTokenizer(br.readLine());
            x = Integer.parseInt(token.nextToken())-1;
            y = Integer.parseInt(token.nextToken())-1;
            d = Integer.parseInt(token.nextToken());

            map[x][y].add(d);
        }
        // 상어위치
        token = new StringTokenizer(br.readLine());
        x = Integer.parseInt(token.nextToken())-1;
        y = Integer.parseInt(token.nextToken())-1;
        shark = new Shark(x, y);

        while(S>0){
            // 1. 복제마법
            copyMagic();
            // 2. 물고기 이동 : map에 기록된 물고기 한마리씩 moveMap으로 이동시킴
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    moveMap[i][j] = new ArrayList<>();
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int fish:map[i][j]) {
                        moveFish(i,j,fish);
                    }
                }
            }
            // moveMap -> map
            finishMove();

            // 3. 상어 이동 : 사전순, 물고기 잡기
            moveShark();

            // 4. 물고기 냄새 -1
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    smellMap[i][j]--;   // 꼭 0일 필요 없어서 이렇게 처리
                }
            }

            // 5. 복제마법 완료
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int fish:copyMap[i][j]) {
                        map[i][j].add(fish);
                    }
                }
            }
            S--;
        }

        // final. 연습을 마쳤을 때 남은 물고기의 수
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cnt += map[i][j].size();
            }
        }

        System.out.println(cnt);

    }

    private static void moveShark() {
        maxRoute = 0;
        maxRouteCnt = -1;

        // 물고기를 많이 제할 수 있으면서 + 사전순으로 앞선 경로 알아내기만 하기
        getBestRoute(shark.x, shark.y, 3, 0, 0);

        // maxRoute에 따라 움직이면서 물고기 제외하기
        int[] route = new int[3];

        route[0] = (maxRoute/100);
        route[1] = (maxRoute%100)/10;
        route[2] = (maxRoute%100)%10;

        for (int dir:route) {
            shark.x += dsx[dir];
            shark.y += dsy[dir];

            if(!map[shark.x][shark.y].isEmpty()) smellMap[shark.x][shark.y] = 3; // 이번 턴에 바로 1이 감소된다 + 두 턴 동안 남아있어야 한다.
            map[shark.x][shark.y].clear();
        }
    }

    private static void getBestRoute(int x, int y, int move, int cnt, int route) {
        // 한 칸에 한마리만 있는게 아니라서 maxCnt로 가지치기할 수 없다.
        if(move==0){
            if(maxRouteCnt<cnt){
                maxRouteCnt = cnt;
                maxRoute = route;
            }
            return;
        }

        int nx, ny, nCnt, nRoute;
        for (int i = 1; i < 5; i++) {
            nx = x + dsx[i];
            ny = y + dsy[i];
            nCnt = cnt;
            nRoute = route;

            // 경계검사
            if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
            // 물고기
            nCnt += map[nx][ny].size();  // 단순히 많이 잡는 경로만 알아내는 거라 실제로 물고기를 잡지 않음
            nRoute += (i*Math.pow(10,move-1));

            ArrayList<Integer> tmp = new ArrayList<>();
            for(int fish:map[nx][ny]) tmp.add(fish);
            map[nx][ny].clear();
            getBestRoute(nx, ny, move-1, nCnt, nRoute);
            for(int fish:tmp) map[nx][ny].add(fish);
        }
    }

    private static void finishMove() {
        for (int i = 0; i < N; i++) {
            map[i] = moveMap[i].clone();
        }
    }

    private static void moveFish(int x, int y, int d) {
        // 이동가능할 때까지 회전 =>못하면 return
        // 8방(1부터 8까지)
        // 현재 위치부터 반시계방향으로
        int nx, ny;
        int nd = d+1;
        for (int i = 0; i < 8; i++) {
            nd--;
            if(nd<1) nd = 8;
            nx = x+dfx[nd];
            ny = y+dfy[nd];

            // 이동 가능한가 : 경계검사
            if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
            // 이동 가능한가 : 물고기 냄새가 있는가
            if(smellMap[nx][ny]>0) continue;
            // 이동 가능한가 : 상어가 있는가
            if(shark.x==nx && shark.y==ny) continue;

            // 이동가능하면 이동용맵에 기록하고 종료
            moveMap[nx][ny].add(nd);
            return;
        }
        // 끝까지 이동 못했으면 원래 방향 그대로 기록
        moveMap[x][y].add(d);
    }

    private static void copyMagic() {
        copyMap = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }
    }
}
