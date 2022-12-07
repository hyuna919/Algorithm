import java.util.*;

// n개의 노드, n-1개의 경로 -> 루프x -> 부모노드는 무조건 1개
class Solution {
    static int root = 1, max=1;    // 완전, 이진 트리가 아니라서 루트 정하기 어려움. 임의로 지정할 것.
    static Map<Integer,ArrayList<Integer>> tree;
    static int[] countRoute, parents;
    static ArrayList<Integer> leafList;
    static boolean[] stateList;

    public int solution(int n, int[][] lighthouse) {
        countRoute = new int[n + 1];  // 1~n번 등대
        parents = new int[n + 1];     // 1~n번 등대
        leafList = new ArrayList<>();
        tree = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            tree.put(i, new ArrayList<>());
        }

        // 연결 등대 정리 + 경로 세기
        for (int[] route : lighthouse) {
            int a = route[0];
            int b = route[1];

            tree.get(a).add(b);
            tree.get(b).add(a);

            countRoute[a]++;
            countRoute[b]++;
        }

        // 리프 목록 만들기 + 연결 가장 많은 노드를 루트로 잡기
        for (int i = 1; i <= n; i++) {
            if (countRoute[i] == 1) leafList.add(i);
            if (countRoute[i] > max) {
                root = i;
                max = countRoute[i];
            }
        }


        // 부모배열만들기 -> parents[route]는 0이 됨
        if(n==2){
            parents[2] = 1;
        } else {
            boolean[] visited = new boolean[n + 1];
            Queue<Integer> q = new LinkedList<>();
            for (int node:leafList) {
                q.add(node);
            }

            while(!q.isEmpty()) {
                int now = q.poll();

                if(now==root) continue;

                // 방문처리
                if(visited[now]) continue;
                visited[now] = true;

                ArrayList<Integer> list = tree.get(now);
                for(int next:list) {
                    if(!visited[next]) {
                        parents[now] = next;
                        q.add(next);
                    } else if(next==root) parents[now] = next;
                }
            }
        }


        // 등 켜기(리프->부모방향)
        Queue<Integer> q = new LinkedList<>();
        stateList = new boolean[n+1];
        for (int node:leafList) {
            q.offer(node);

            while(!q.isEmpty()){
                int now = q.poll();
                if(now==root) break;
                // now(자식)이 꺼져있으면 부모를 켠다
                int parent = parents[now];
                if(!stateList[now]){
                    stateList[parent] = true;
                }

                // now의 상태와 무관하게 무조건 조부모 노드가 q에 추가된다.
                // 부모가 루트면 조부모가 없다
                if(parent!=root) q.add(parents[parent]);
            }
        }


        // 켜져있는 등대 세기
        int answer = 0;
        for(boolean s:stateList){
            if(s) answer++;
        }
        return answer;
    }

}
