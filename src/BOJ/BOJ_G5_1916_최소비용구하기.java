import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/*
최소경로문제 - 계속 시뮬이나 dp만 풀다보니 최소경로, 최소신장 문제를 잊어서 풀었다.
1회차) 다익스트라(PriorityQueue x) : 값 범위 고려 안함 + 나중에 초기화 과정 추가하면서 버스 여부를 확인하는 경로문이 틀리게됨
2회차) 다익스트라(PriorityQueue o) : map이 아니라 graph이용(start에서 어디로 얼마에 갈 수 있는지 기록)


 */

public class Main {
    static class Node implements Comparable<Node>{
        int end, cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
    static int N, M, INF = 100_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine())+1;
        M = Integer.parseInt(br.readLine());
        StringTokenizer token;

        // 도시간 비용을 그래프로 나타냄
        ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<Node>());
        }
        int start, end, cost;
        for (int i = 0; i < M; i++) {
            token = new StringTokenizer(br.readLine());
            start = Integer.parseInt(token.nextToken());
            end = Integer.parseInt(token.nextToken());
            cost = Integer.parseInt(token.nextToken());
            // start에서 어디로 얼마에 갈 수 있는지 기록
            graph.get(start).add(new Node(end, cost));
        }

        // 목표로 하는 출도착점
        token = new StringTokenizer(br.readLine());
        start = Integer.parseInt(token.nextToken());
        end = Integer.parseInt(token.nextToken());

        boolean[] visited = new boolean[N];
        int[] distance = new int[N];
        
        // 초기화
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Node> q = new PriorityQueue();
        q.offer(new Node(start, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            // 시작점에서 현재도시로의 비용보다 > 시작점에서 다음 도시로의 비용이 적으면 continue
            if(distance[now.end] < now.cost) continue;

            // 선택된 노드(pq니까 최저비용 노드)의 인접 노드 확인
            for (int i = 0; i < graph.get(now.end).size(); i++) {
                Node next = graph.get(now.end).get(i);
                if(distance[next.end] > now.cost+next.cost) {
                    distance[next.end] = now.cost+next.cost;
                    q.offer(new Node(next.end, distance[next.end]));
                }
            }
            
        }

        System.out.println(distance[end]);
    }
}
