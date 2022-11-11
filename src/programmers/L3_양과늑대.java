import java.util.*;

/*
양과 늑대

구현문제 포인트) 방문처리 어떻게 할지 고려할 것

문제점) 2문제 틀림 why?
 */
class Solution {
    static class State{
        int sheep, wolf;

        public State(int sheep, int wolf) {
            this.sheep = sheep;
            this.wolf = wolf;
        }
    }
    static int N, M, max = 0, maxSheep;
    static int[] parents, info;
    static int[][] children;
    static ArrayList<State>[] visited;
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        N = info.length;

        // 전체 양의 수 얻기, 늑대가 1
        maxSheep = N;
        for(int i:info) maxSheep -= i;

        // 트리 관계 만들기
        M = N-1;    // == edges.length
        parents = new int[N];
        children = new int[N][2];
        for (int i = 0; i < M; i++) {
            int p = edges[i][0];
            int c = edges[i][1];

            // 부모 관계
            parents[c] = p;

            // 자식 관계
            if(children[p][0]==0) children[p][0] = c;   // 자식을 0으로 두는 경우는 없다.
            else children[p][1] = c;
        }

        // 양 몰이
        visited = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            visited[i] = new ArrayList<>();
        }
        dfs(0,1,0);

        return max;
    }

    private void dfs(int node, int sheep, int wolf) {
        // 같은 상태로 왔던 곳인가
        ArrayList<State> records = visited[node];
        for(State r:records) {
            if(r.sheep==sheep && r.wolf==wolf) return;
        }
        State now = new State(sheep,wolf);
        records.add(now);

        // 늑대가 같거나 더 많아지는 경우 -> stop
        if(sheep<=wolf){
            max = Math.max(max, sheep);
            return;
        }

        // 전체 양을 모두 획득
        if(sheep == maxSheep) {
            max = maxSheep;
            return;
        }
        if(max == maxSheep) return;

        // 자식으로 이동
        for (int child:children[node]) {
            // 자식이 있다면
            if (child != 0) {
                if (info[child] == 0) {
                    info[child] = -1;
                    dfs(child, sheep + 1, wolf+0);
                    info[child] = 0;
                } else if (info[child] == 1) {
                    info[child] = -1;
                    dfs(child, sheep, wolf + 1);
                    info[child] = 1;
                } if (info[child] == -1) {
                    dfs(child, sheep, wolf);
                }
            }
        }

        // 부모로 이동
        if(node!=0) dfs(parents[node], sheep, wolf);
    }


}
