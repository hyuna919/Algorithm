import java.util.*;

/*
최단거리, edge를 쓰는 bfs

1번 노드로부터 가장 멀리 떨어진 노드의 수 구하기
가장 멀리 떨어진 노드 : 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드

[풀이]
1.
List<List<int>> 타입을 통해서 a노드에서 갈 수 있는 모든 위치를 기록함
-> 2차원으로 하기에는 노드 최대 20,000 간선 최대 50,000이라서 간선이 적은편이다.
2.
pq써서 가본적 없는 노드 도착하면 distance에 거리 기록 -> 동시에 최대 거리를 계속 max에 갱신

3.
distance에서 max인 노드의 수 

 */

public class Main {
    public static void main(String[] args) {
        int n = 6;
        int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n, vertex));
    }

    static class Node implements Comparable<Node> {
        int position, weight;

        public Node(int position, int weight) {
            this.position = position;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
    static public int solution(int n, int[][] edge) {
        n += 1;
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        int[] distance = new int[n];
        distance[1] = -1;

        // 관계 표시
        int a,b;
        for (int i = 0, end= edge.length; i < end; i++) {
            a = edge[i][0];
            b = edge[i][1];
            list.get(a).add(b);
            list.get(b).add(a);
        }

        int max = 0;
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1, 0));
        while(!q.isEmpty()){
            Node now = q.poll();
            for (int next:list.get(now.position)) {
                if(distance[next] == 0) {
                    distance[next] = now.weight+1;
                    if(max<now.weight+1) max = now.weight+1;
                    q.offer(new Node(next, now.weight+1));
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i < n; i++) {
            if(distance[i]==max) cnt++;
        }

        return cnt;
    }
}
