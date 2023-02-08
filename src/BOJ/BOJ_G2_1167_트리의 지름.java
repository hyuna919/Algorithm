import java.io.*;
import java.util.*;

/*
BOJ_G2_1167_트리의 지름
(주의)
참고 : https://bedamino.tistory.com/15
 */
public class Main {
    static class Edge {
        int lid,dis;

        public Edge(int lid, int dis) {
            this.lid = lid;
            this.dis = dis;
        }
    }

    static int V, max=0, start, a;
    static ArrayList<Edge>[] eList;
    static boolean[] isLeaf, visited;

    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        isLeaf = new boolean[V+1];
        eList = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) eList[i] = new ArrayList<>();


        // 1. 간선정보입력, 리프여부확인
        for (int i = 0; i < V; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int cnt=0;
            int id = Integer.parseInt(token.nextToken());
            while(true){
                cnt++;
                int lid = Integer.parseInt(token.nextToken());
                if(lid==-1) break;
                int dis = Integer.parseInt(token.nextToken());
                Edge e = new Edge(lid, dis);
                eList[id].add(e);
            }
            if(cnt==2) isLeaf[id] = true;
        }

        // 2. 임의위치start에서 가장 먼 a찾기
        visited = new boolean[V+1];
        max = 0;
        start = 1;
        dfs(start, 0);

        // 3. a에서 가장 먼 b 찾기
        max = 0;
        dfs(a, 0);

        System.out.println(max);
    }

    private static void dfs(int now, int dis) {
        // 다른 리프로 도착
        if(isLeaf[now] && max<dis) {
            max = dis;
            a = now;
            return;
        }

        for(Edge e:eList[now]){
            int next = e.lid;
            if(!visited[next]) {
                visited[now] = true;
                dfs(next, dis + e.dis);
                visited[now] = false;
            }
        }
    }
}
