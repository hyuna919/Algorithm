import java.util.*;


class Solution {
    static int[][] list;
    static int[] connectedCnt;
    static boolean[] stateList, isLeaf;
    static ArrayList<Integer>[] connected;
    static Queue<Integer> q = new LinkedList<>();

    public int solution(int n, int[][] lighthouse) {
        list = lighthouse;

        stateList = new boolean[n+1];
        isLeaf = new boolean[n+1];
        connectedCnt = new int[n+1];
        connected = new ArrayList[n+1];
        for (int i = 0, end=connected.length; i < end; i++) {
            connected[i] = new ArrayList<>();
        }


        // 트리만들기
        for (int i = 0, end=list.length; i < end; i++) {
            int a = list[i][0];
            int b = list[i][1];
            connectedCnt[a]++;
            connectedCnt[b]++;
            connected[a].add(b);
            connected[b].add(a);
        }

        // 리프로부터 필수인 등대 켜두기
        for (int i = 0, end=connectedCnt.length; i < end; i++) {
            // 연결된 등대가 1개 -> 리프노드 -> 연결된 등대 추가
            if(connectedCnt[i]==1) {
                isLeaf[i] = true;
                if(!stateList[i]){
                    int next = connected[i].get(0);
                    stateList[next] = true;
                    q.offer(next);
                }
            }
        }

        turnOn();

        // 켜져있는 등대 세기
        int answer = 0;
        for (boolean state:stateList) if(state) answer++;
        return answer;
    }

    private void turnOn() {
        // q에 있는 등대 키고+2다리 건너 등대 추가하기
        while (!q.isEmpty()){
            int now = q.poll();
            if(stateList[now]){ // 현재 등대가 켜져있으면
                // 다다음 등대
                for(int next : connected[now]){
                    for(int nextnext : connected[next]){
                        if(nextnext==now) continue; // 다다음이 지금이면 continue;
                        q.offer(nextnext);
                    }
                }
            } else {    // 현재 등대가 꺼져있으면
                // 
            }


        }
    }
}
