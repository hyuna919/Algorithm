import java.util.*;

/*
양과 늑대

구현문제 포인트) 방문처리 어떻게 할지 고려할 것
문제점) 12,15번 틀림 why? -> 방문처리 논리가 안맞았음

풀이) 전체 노드가 최악 17이라서 방문처리를 하지 않음 -> 이동가능한 노드 목록을 만들어서 이동시킴 + 방문한 노드는 제거 -> 중복 방문 방지
 */

class Solution {
    static int N, M, max = 0, maxSheep;
    static int[] parents, info;
    static int[][] children;
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        N = info.length;
        M = N-1;    // == edges.length

        // 전체 양의 수 얻기, 늑대가 1
        maxSheep = N;
        for(int i:info) maxSheep -= i;

        // 트리 관계 만들기
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
        HashSet<Integer> list = new HashSet<>();
        list.add(0);    // 무조건 0번 노드에서 시작
        dfs(0,0,0, list);

        return max;
    }

    private void dfs(int node, int sheep, int wolf, Set<Integer> list) {
        list.remove(node); // 이동 목록에서 자신 제거

        // 현재위치 동물 카운트
        if(info[node]==0) sheep++;
        else wolf++;
        // 갱신
        max = Math.max(max, sheep);

        // 늑대가 같거나 더 많아지는 경우 -> stop
        if(sheep <= wolf) return;
        // 전체 양을 모두 획득
        if(max == maxSheep) return;

        // 이동 목록에 자식 추가
        for (int child:children[node]) if(child!=0) list.add(child);
        // 다음노드로 이동
        for (int child:list) {
            Set<Integer> newList = new HashSet<>();
            for (int c:list) newList.add(c);
            dfs(child, sheep, wolf, newList);
        }
    }
}
