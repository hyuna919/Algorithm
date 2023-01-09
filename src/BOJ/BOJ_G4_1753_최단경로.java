import java.io.*;
import java.util.*;

/*
BOJ_G4_1753_최단경로
플로이드워셜
 */
public class Main {
    static class Vertex implements Comparable<Vertex>{
        int end, w;

        public Vertex(int end, int w) {
            this.end = end;
            this.w = w;
        }

        @Override
        public int compareTo(Vertex o) {
            return w-o.w;
        }
    }
    static int V, E, K;
    static ArrayList<Vertex>[] list;
    static int[] weights;

    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());
        K = Integer.parseInt(token.nextToken());
        list = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            token = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(token.nextToken());
            int v = Integer.parseInt(token.nextToken());
            int w = Integer.parseInt(token.nextToken());

            list[u].add(new Vertex(v,w));
        }

        // 1. 최단경로
        int end = V+1;
        weights = new int[V+1];
        for (int i = 1; i < end; i++) weights[i] = Integer.MAX_VALUE;
        weights[K] = 0;

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.offer(new Vertex(K,0));
        while (!pq.isEmpty()){
            Vertex now = pq.poll();
            if(weights[now.end] < now.w) continue;
            for (Vertex next:list[now.end]) {
                if(weights[next.end] > weights[now.end]+next.w) {
                    weights[next.end] = Math.min(weights[next.end], weights[now.end]+next.w);
                    Vertex tmp = new Vertex(next.end, weights[next.end]);
                    pq.offer(tmp);
                }
            }
        }


        // 출력
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < end; i++) {
            if(weights[i]==Integer.MAX_VALUE) builder.append("INF\n");
            else builder.append(weights[i]).append("\n");
        }

        System.out.println(builder);
    }

}
