// 조합
// 최악 10^10이라 그냥 돌림

public class Solution {
    static int[] info;
    static int maxGap = 0;
    static int[] rionArrow, maxArrow;
    public int[] solution(int n, int[] info) {
        this.info = info;
        rionArrow = new int[11];
        maxArrow = new int[11];

        comb(-1, 0,0,n);

        if(maxGap==0) return new int[]{-1};
        else return maxArrow;
    }
    
    void comb(int now, int apeach, int rion, int arrow){
        if(++now>10) {
            rionArrow[10] += arrow;
            // 점수 비교
            int gap = rion - apeach;
            if(maxGap<gap) {
                maxGap = gap;
                for (int i = 0; i < 11; i++) maxArrow[i] = rionArrow[i];
            } else if (maxGap==gap) {
                if(isUpdateDetail()) {
                    for (int i = 0; i < 11; i++) maxArrow[i] = rionArrow[i];
                }
            }
            rionArrow[10] -= arrow;
            return;
        }

        int score = 10 - now;

        // 라이언이 화살쏘기
        for (int i = 0; i <= arrow; i++) {
            rionArrow[now] = i;
            // 점수 무효
            if(info[now]==0 && i==0) comb(now,apeach,rion,arrow);
            // 어피치가 득점(동점, 어피치가 많음)
            else if(info[now]>=i) {
                comb(now,apeach+score,rion,arrow-i);
            }
            // 라이언이 득점
            else if(info[now]<i){
                comb(now,apeach,rion+score,arrow-i);
            }
            rionArrow[now] = 0;
        }
        
    }

    private boolean isUpdateDetail() {
        // maxArrow vs rionArrow
        for (int i = 10; i > -1 ; i--) {
            if(maxArrow[i] > rionArrow[i]) return false;
            else if (maxArrow[i] < rionArrow[i]) return true;
            else continue;
        }
        return false;
    }
}
