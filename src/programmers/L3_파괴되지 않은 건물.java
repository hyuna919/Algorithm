/*
bruth force로 풀면 최악 O(1000*1000*250000)
누적합을 이용한다.
 */
class Solution {
    static int N, M;
    static int[][] map;
    public int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
        map = new int[N+1][M+1];

        // 변화량 기록
        for(int i=0, end= skill.length; i<end; i++){
            int[] s = skill[i];
            if(s[0] == 1) process(s[1], s[2], s[3]+1, s[4]+1, s[5]*-1);
            else process(s[1], s[2], s[3]+1, s[4]+1, s[5]);
        }

        // 누적합 상->하
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                map[i][j] += map[i-1][j];
            }
        }

        // 누적합 좌->우
        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] += map[i][j-1];
            }
        }

        // 남은 건물 확인
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j]+map[i][j]>0) answer++;
            }
        }

        return answer;
    }

    private void process(int r1, int c1, int r2, int c2, int degree) {
        map[r1][c1] += degree;
        map[r1][c2] += degree * -1;
        map[r2][c1] += degree * -1;
        map[r2][c2] += degree;
    }
}
