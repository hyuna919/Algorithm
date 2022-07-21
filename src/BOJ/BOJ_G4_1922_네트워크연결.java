import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/*
최소신장 Kruskal 문제

 */

public class Main {
    static class Edge implements Comparable<Edge> {
        int a, b, weight;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    static int[] parents;
    static void make(){
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    static int findParents(int a){
        if(parents[a] == a) return a;
        else return findParents(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = findParents(a);
        int bRoot = findParents(b);
        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

    static int N, M;
    static Edge[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        N = Integer.parseInt(br.readLine())+1;
        M = Integer.parseInt(br.readLine());

        list = new Edge[M];

        int a, b, cost;
        for (int i = 0; i < M; i++) {
            token = new StringTokenizer(br.readLine());
            a = Integer.parseInt(token.nextToken());
            b = Integer.parseInt(token.nextToken());
            cost = Integer.parseInt(token.nextToken());

            list[i] = new Edge(a,b,cost);
        }
        Arrays.sort(list);

        make();

        int res = 0;
        int cnt = 0;
        for (Edge e:list) {
            if(union(e.a, e.b)) {
                res += e.weight;
                if(++cnt >= N-1) break;
            }
        }
        System.out.println(res);
    }
}
