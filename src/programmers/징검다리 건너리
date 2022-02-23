## 그리디하게 짰다
## 왜이렇게 어렵지...ㅎ
## 이진으로 풀어볼까?

import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int cnt = 0;
        int dist = 0;
        int n = stones.length;
        int min = Integer.MAX_VALUE;
        boolean flag = false;
        // 최저값 빼기
        for(int now : stones){
            min = min>now?now:min;
        }
        for(int i=0;i<n;i++){
            stones[i] -= min;
        }
        cnt = min;
        
        // System.out.println(stones[0]);
        
        while(true){
            flag = false;
            dist = 0;
            for(int i=0; i<n ; i++){
                if(dist==k) {
                    flag = true;
                    break;
                }
                if(stones[i] == 0) {
                    dist++;
                }else{
                    dist = 0;
                    stones[i]-=1;
                }
            }
            if(flag) break;
            cnt++;
        }
        
        return cnt;
    }
}
