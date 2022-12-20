import java.io.*;
import java.util.*;

/*
플로이드 워샬
*/


public class Main {
    static class Node{
        int l,r;

        public Node(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    static int N, Q, MAX = 301;
    static Node[] nodeList; // 노드(선분) 리스트
    static int[][] map;   // 관계 표시
    public static void main(String[] args) throws IOException {
        // 0. 입력
        StringTokenizer token;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nodeList = new Node[N+1];   // 노드는 1번부터 시작
        map = new int[N+1][N+1];
        for (int i = 1, end=N+1; i < end; i++) {
            Arrays.fill(map[i], MAX);
            map[i][i] = 0;
        }

        // 노드 위치 정보 -> nodeList
        for (int i = 1, end=N+1; i < end; i++) {
            token = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(token.nextToken());
            int r = Integer.parseInt(token.nextToken());
            nodeList[i] = new Node(l,r);
        }

        // 1. 인접 친구 -> 이번 노드 범위 안에 속하는 노드가 있는지 확인
        for (int i = 1, end=N+1; i < end; i++) {
            Node now = nodeList[i];
            for (int j = i+1; j < end; j++) {
                Node next = nodeList[j];

                if((next.r < now.l) || (now.r < next.l)) continue;  // 범위가 겹치지 않는 경우 제외
                map[i][j] = map[j][i] = 1;
            }
        }

        // 2. 최단 거리 친구(플로이드 워샬)
        // a->b vs a->c->b
        for (int c = 1, end=N+1; c < end; c++) {
            for (int a = 1; a < end; a++) {
                for (int b = 1; b < end; b++) {
                    map[a][b] = Math.min(map[a][b], map[a][c]+map[c][b]);
                }
            }
        }

        // 3. 질문
        Q = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());

            int res = map[a][b];
            builder.append((res==MAX?-1:res)+"\n");

       }

        System.out.println(builder);
    }
}
