import java.io.*;
import java.util.*;
/*
    그래프 탐색, bfs

    (bfs)
    병이 3개니까 병들의 상태를 int[]배열 이용해서 계속 기록함
    이 기록이 일치하는 경우는 더이상 진행하지 않음

    (A병이 0)
    bfs에서는 이 조건을 고려하지 않고, 다 끝나고 나서 main함수로 돌아와서 적용함.
    visited에서 A==0인 경우에 한해 C값을 set에 저장함

    (개선방안-방문처리)
    vis[now[0]][now[1]][now[2]]

 */

public class Main {
    static int A,B,C;
    static ArrayList<int[]> visited = new ArrayList<>();
    static int[] sizeList = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        A = Integer.parseInt(token.nextToken());
        B = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());
        sizeList = new int[] {A,B,C};

        move();

        // 만들어낸 경우 중 A가 0인 경우의 C값 모으기
        HashSet<Integer> cList = new HashSet<>();
        for (int[] now:visited) {
            if(now[0]==0) cList.add(now[2]);
        }

        // c목록 정렬
        ArrayList<Integer> res = new ArrayList<>();
        for (int now:cList) {
            res.add(now);
        }
        Collections.sort(res);

        // 출력
        for (int now:res) {
            System.out.print(now+" ");
        }

    }

    private static void move() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0,C});
        visited.add(new int[]{0,0,C});

        while (!q.isEmpty()){
            int[] now = q.poll();
            for (int start = 0; start < 3; start++) {
                for (int end = 0; end < 3; end++) {
                    if(start==end) continue;
                    if(now[start]==0) continue;
                    int left = sizeList[end] - now[end];
                    if(left==0) continue;


                    int[] item = new int[]{now[0],now[1],now[2]};
                    // 물 옮기기
                    if((left-item[start])<0) {   // start를 다 수용할 수 없는 경우
                        item[end] += left;
                        item[start] -= left;
                    }else{                      // start를 다 수용할 수 있는 경우
                        item[end] += item[start];
                        item[start] = 0;
                    }

                    // 기록
                    if(isVisited(item)) continue;
                    else {
                        visited.add(item);
                        q.offer(item);
                    }
                }
            }
        }
    }

    private static boolean isVisited(int[] now) {
        for (int[] item :visited) {
            int flag = 0;
            for (int i = 0; i < 3; i++) if(item[i]==now[i]) flag++;
            if(flag==3) return true;
        }
        return false;
    }
}
