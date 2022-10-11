import java.io.*;
import java.util.*;

/*
상어초등학교
구현

 */

public class Main {
    static class Pos{
        int x,y;
        int cnt, empty; // 좋아하는 인접학생 수, 인접 빈 자리수

        public Pos(int x, int y, int cnt, int empty) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.empty = empty;
        }
    }
    static int N, S, student, max=0;
    static int[][] room;
    static int[][] friend;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Pos maxPos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = N*N;
        room = new int[N][N];
        friend = new int[S+1][4];
        StringTokenizer token;

        int T = S;
        while(T-->0){
            // 이번 학생 입력
            token = new StringTokenizer(br.readLine());
            student = Integer.parseInt(token.nextToken());
            for (int i = 0; i < 4; i++) {
                friend[student][i] = Integer.parseInt(token.nextToken());
            }
            if(T==S-1) {
                room[1][1] = student;
                continue;
            }

            // 배치
            max = 0;
            maxPos = new Pos(-1,-1,-1,-1);
            boolean nextStudent = false;
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    // 빈자리인가
                    if(room[x][y]!=0) continue;
                    // 인접 좋아하는 학생 수(사방탐색)
                    int cnt = 0;
                    int empty = 0;
                    for (int i = 0; i < 4; i++) {
                        int nx = x+dx[i];
                        int ny = y+dy[i];
                        if(nx<0||nx>=N||ny<0||ny>=N) continue;
                        // 인접자리 확인
                        int now = room[nx][ny];
                        if(now==0) {    // 인접자리 비었는지
                            empty++;
                            continue;
                        }
                        for (int j = 0; j < 4; j++) {
                            if(friend[student][j]==now) cnt++;
                        }
                    }
                    // 최선
                    if(cnt==4) {
                        room[x][y] = student;
                        nextStudent = true;
                        break;
                    }
                    // max>cnt -> 빈자리 확인 필요x
                    if(max>cnt) continue;


                    // max갱신 or max와 같으면 인접 빈 곳
                    else if(max<cnt) {
                        max = cnt;
                        maxPos = new Pos(x,y,cnt,empty);
                    }else{  // max==cnt
                        if(maxPos.empty < empty) maxPos = new Pos(x,y,cnt,empty);
                    }
                    // 행->열 적은 곳은 위에서 정리됨
                }
                if (nextStudent) break;
            }

            // 뽑은 자리에 배치
            room[maxPos.x][maxPos.y] = student;
        }

        // 만족도 조사
        int sum=0, cnt;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                cnt = 0;
                student = room[x][y];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    int now = room[nx][ny];
                    for (int j = 0; j < 4; j++) {
                        if(friend[student][j]==now) {
                            cnt++;
                            break;
                        }
                    }
                }
                if(cnt!=0) sum += Math.pow(10,cnt-1);
            }
        }

        // 출력
        System.out.println(sum);
    }
}
