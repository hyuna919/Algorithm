import java.io.*;
import java.util.*;

/*
BOJ_P5_1028_다이아몬드광산

틀림1) 시간복잡도 줄이려다 1번 범위설정 잘못함 -> 7%
틀림2) 다이아몬드 좌상단 길이재고나서 한번 되돌아가는 거 무조건 해야하는데 밖으로 나갈때만 했다. -> 30%
틀림3) 범위안에서 다이아가 완성되는지 확인하는 isPossible()에 문제가 있는듯. 주석처리 -> 32%, 시간초과
틀림4) isPossible()이 아니어도 한칸 전으로 돌려야하는데 안하고 바로 continue하고 있었다.

 */
public class Main {

    static int N, M, max=0;
    static char[][] map;
    static int[] dr = {1,1,-1,-1};
    static int[] dc = {-1,1,1,-1};
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            if(max==1) continue;
            for (int j = 0; j < M; j++) {
                if(map[i][j]=='1') max=1;
            }
        }

        // 1이 하나도 없으면
        if(max==0) {
            System.out.println(0);
            return;
        }
        
        // 1. 다이아찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]=='1') isDiamond(i,j);
            }
        }
        
        // 결과
        System.out.println(max);
    }

    private static void isDiamond(int r, int c) {
        int length = 0;
        int nr = r, nc = c;
        while(map[nr][nc]=='1') {
            length++;
            nr += dr[0];
            nc += dc[0];
            if(nr<0 || nr>=N || nc<0 || nc>=M) {
                break;
            }
        }

        nr -= dr[0];
        nc -= dc[0];

        for (int i = length; i > max; i--) {
            // 높이, 너비 되는지 보기
            if(isPossible(r, c, i)) {
                if(isNDiamond(i,nr, nc)) {
                    max = Math.max(max, i);
                    break;
                }
            }
            nr -= dr[0];
            nc -= dc[0];
        }
    }


    private static boolean isPossible(int r, int c, int length) {
        int gap1 = length-1;
        int gap2 = gap1*2;
        // 좌
        if(map[r+gap1][c-gap1] != '1') return false;
        // 하
        if(r+gap2 >= N) return false;
        else if(map[r+gap2][c] != '1') return false;
        // 우
        if(c+gap1 >= M) return false;
        else if(map[r+gap1][c+gap1] != '1') return false;

        return true;
    }


    private static boolean isNDiamond(int n, int nr, int nc) {
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < n; j++) {
                nr += dr[i];
                nc += dc[i];
                if(nr<0 || nr>=N || nc<0 || nc>=M) return false;
                if(map[nr][nc] != '1') return false;
            }
        }
        return true;
    }

}
