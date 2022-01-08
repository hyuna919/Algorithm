package day_0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
    private static boolean [][] map;
    private static int X, Y, sc, sr, ec, er, min;
    private static StringTokenizer token;
     
    // 상하좌우
    private static int[] dr = {-1,1,0,0};
    private static int[] dc = {0,0, -1,1};
     
    public static void main(String[] args) throws Exception {
        // 입력 처리
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        token = new StringTokenizer(in.readLine());
        X = Integer.parseInt(token.nextToken());
        Y = Integer.parseInt(token.nextToken());
         
        // 배열 생성
        map = new boolean[Y][X];
//      visited = new boolean[Y][X];
         
        // 시작, 도착 위치 받기
        String [] tmp = in.readLine().split("\\s+");
        sc = Integer.parseInt(tmp[0])-1;
        sr = Integer.parseInt(tmp[1])-1;
        ec = Integer.parseInt(tmp[2])-1;
        er = Integer.parseInt(tmp[3])-1;
         
        // 미로 정보
        for (int i = 0; i < Y; i++) {
            tmp = in.readLine().split("");
            for (int j = 0; j < X; j++) {
                map[i][j] = (tmp[j].equals("0"))?true:false;
            }
             
        }
         
        // 탐색 시작
        bfs();
        System.out.println(min);
 
    }
 
    private static void bfs() {
        Queue<int []> q = new LinkedList<int []>();
         
        q.offer(new int[] {sr, sc, min});
        map[sr][sc] = false;
         
        min = 100;
         
         
        top:
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int cnt = curr[2];
             
            // 인접노드(사방)
            for (int i = 0; i < 4; i++) {
                int nr = r+dr[i];
                int nc = c+dc[i];
                 
                 
                // 경계값 안이고, 길이거나 간적 없는곳
                if(nr>-1 && nr<Y && nc>-1 &&nc<X && map[nr][nc]) {
                    if(nr==er && nc == ec) {
                    	System.out.println(cnt);
                        min = Math.min(min, cnt);
                        break top;
                    }else {
                        q.offer(new int[] {nr,nc, ++cnt});
                        map[nr][nc] =false;
                    }
                }
            }
             
        }
    }
 
}