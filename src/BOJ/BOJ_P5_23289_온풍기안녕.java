import java.io.*;
import java.util.*;

/*
삼성역테
시뮬레이션
온풍기, 온도조절 하나하나가 문제 하나로 만들 수 있는 케이스.
디버깅하기 어렵고, 잔실수가 많았다.
1. 온풍기 바람 부분에서 if문 조건을 잘못다는 실수가 두 번 있었음
1-1. if(!flag)로 해야하는데 if(flag)로 함
1-2. toVertical()은 두 사이드가 별개인데 첫 사이드의 경계검사에서 return을 하는 일이 있었다.
 */
public class Main {
    static class Position{
        int x,y,dir;

        public Position(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int R,C,GOAL_TEMP;
    static int[][] temperMap, eachHeaterMap, controlMap;
    static ArrayList<Integer>[][] wallMap;
    static ArrayList<Position> heaterList, checkList;
    // 히터방향 : 우좌상하
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    // 온도 조절용 : 우,하
    static int[] dcx = {0, 1};
    static int[] dcy = {1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        R = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());
        GOAL_TEMP = Integer.parseInt(token.nextToken());

        temperMap = new int[R][C];
        wallMap = new ArrayList[R][C];
        heaterList = new ArrayList<>();
        checkList = new ArrayList<>();

        // 집 배치 입력(히터, 온도확인지점)
        for (int i = 0; i < R; i++) {
            token = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int input = Integer.parseInt(token.nextToken());

                if (input==0) continue;

                if (input==5) checkList.add(new Position(i,j,0));
                else heaterList.add(new Position(i,j,input-1));
            }
        }

        // 벽 입력
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                wallMap[i][j] = new ArrayList<>();
            }
        }

        int wallSize = Integer.parseInt(br.readLine());
        for (int wall = 0; wall < wallSize; wall++) {
            token = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(token.nextToken())-1;
            int y = Integer.parseInt(token.nextToken())-1;
            int d = Integer.parseInt(token.nextToken());

            // 벽으로 갈라진 위치끼리 같은 번호 기록
            wallMap[x][y].add(wall);
            if(d==0){
                wallMap[x-1][y].add(wall);
            }else{
                wallMap[x][y+1].add(wall);
            }
        }

        int chocolate = 0;

        while(chocolate < 101) {
            // 1. 모든 온풍기에서 바람이 한 번 나옴
            for (Position p:heaterList) {
                eachHeaterMap = new int[R][C];

                // 각 온풍기 당 eachHeaterMap에 온도 올리기
                int nx = p.x + dx[p.dir];
                int ny = p.y + dy[p.dir];
                eachHeaterMap[nx][ny] = 5;
                upTemper(nx, ny, p.dir, 4);

                // eachHeaterMap에 기록한 온도를 temperMap에 더한다
                addTemper(eachHeaterMap);
            }

            // 2. 온도 조절
            controlMap = new int[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    controlTemper(i,j);
                }
            }
            // controlMap에 기록한 온도를 temperMap에 더한다
            addTemper(controlMap);

            // 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
            downTemper();

            // 4. 초콜릿을 하나 먹는다.
            chocolate++;

            // 5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K이상이면 테스트를 중단하고 break
            if(checkTemper()) break;
        }


        System.out.println(chocolate);

    }

    private static boolean checkTemper() {
        for (Position now:checkList) {
            if(temperMap[now.x][now.y] < GOAL_TEMP) return false;
        }
        return true;
    }

    private static void downTemper() {
        // 가장 위아래 행
        for (int i = 0; i < C; i++) {
            if(temperMap[0][i]>0) temperMap[0][i]--;
            if(temperMap[R-1][i]>0) temperMap[R-1][i]--;
        }

        // 가장 좌우 열
        for (int i = 1, end = R-1; i < end; i++) {
            if(temperMap[i][0]>0) temperMap[i][0]--;
            if(temperMap[i][C-1]>0) temperMap[i][C-1]--;
        }
    }

    // (우, 하) 방향 칸과의 차이만 쭉 기록하면 된다.
    private static void controlTemper(int x, int y) {
        int nx, ny;
        for (int i = 0; i < 2; i++) {
            nx = x + dcx[i];
            ny = y + dcy[i];

            // 경계검사
            if(nx<0 || nx>=R || ny<0 || ny>=C) continue;

            // 벽검사
            boolean flag = false;
            for(int wall :wallMap[x][y]) {
                if(wallMap[nx][ny].contains(wall)) flag=true;
            }
            if(flag) continue;


            int gap = (temperMap[nx][ny] - temperMap[x][y]) / 4;
            controlMap[x][y] += gap;
            controlMap[nx][ny] += gap * (-1);
        }

    }

    private static void addTemper(int[][] tmpMap) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temperMap[i][j] += tmpMap[i][j];
            }
        }
    }

    private static void upTemper(int x, int y, int dir, int temp) {
        if(temp<=0) return;

        // (1) 직선부 온도
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // 경계검사
        if(nx<0 || nx>=R || ny<0 || ny >=C) return;

        // 벽검사 (두 위치에 일치하는 벽이 있는가)
        boolean flag = false;
        for(int wall :wallMap[x][y]) {
            if(wallMap[nx][ny].contains(wall)) flag=true;
        }
        if(!flag) {
            eachHeaterMap[nx][ny] = temp;
            upTemper(nx, ny, dir, temp-1);
        }

        // (2) 사이드 온도 처리
        if(dir<2) toHorizon(x, y, nx, ny, dir, temp);    // 좌우
        else toVertical(x, y, nx, ny, dir, temp);    // 상하


    }

    private static void toVertical(int x, int y, int nx, int ny, int dir, int temp) {
        // 좌측 사이드) 경계검사
        ny--;
        if(ny>=0 && ny<C) {
            // 벽검사 (두 위치에 일치하는 벽이 있는가)
            boolean flag = false;
            for(int wall :wallMap[x][y]) {
                if(wallMap[x][ny].contains(wall)) flag=true;
            }
            if(!flag) {
                for (int wall : wallMap[x][ny]) {
                    if (wallMap[nx][ny].contains(wall)) flag = true;
                }
            }
            if(!flag) {
                eachHeaterMap[nx][ny] = temp;
                upTemper(nx, ny, dir, temp-1);
            }
        }



        // 사이드 2) 경계검사
        ny += 2;
        if(ny>=0 && ny<C) {

            // 벽검사 (벽이 있는가)
            boolean flag = false;
            for (int wall : wallMap[x][y]) {
                if (wallMap[x][ny].contains(wall)) flag = true;
            }
            if (!flag) {
                for (int wall : wallMap[x][ny]) {
                    if (wallMap[nx][ny].contains(wall)) flag = true;
                }
            }
            if (!flag) {
                eachHeaterMap[nx][ny] = temp;
                upTemper(nx, ny, dir, temp - 1);
            }
        }

    }

    private static void toHorizon(int x, int y, int nx, int ny, int dir, int temp) {
        // 사이드 1) 경계검사
        nx--;
        if(nx>=0 && nx<R) {
            // 벽검사 (두 위치에 일치하는 벽이 있는가)
            boolean flag = false;
            for(int wall :wallMap[x][y]) {
                if(wallMap[nx][y].contains(wall)) flag=true;
            }
            if(!flag) {
                for (int wall : wallMap[nx][y]) {
                    if (wallMap[nx][ny].contains(wall)) flag = true;
                }
            }
            if(!flag) {
                eachHeaterMap[nx][ny] = temp;
                upTemper(nx, ny, dir, temp-1);
            }
        }

        // 사이드 2) 경계검사
        nx += 2;
        if(nx>=0 && nx<R) {
            // 벽검사 (벽이 있는가)
            boolean flag = false;
            for(int wall :wallMap[x][y]) {
                if(wallMap[nx][y].contains(wall)) flag=true;
            }
            if(!flag) {
                for (int wall : wallMap[nx][y]) {
                    if (wallMap[nx][ny].contains(wall)) flag = true;
                }
            }
            if(!flag) {
                eachHeaterMap[nx][ny] = temp;
                upTemper(nx, ny, dir, temp-1);
            }
        }
    }
}
