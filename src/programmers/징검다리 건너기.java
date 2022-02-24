## 그리디하게 짰다
## 왜이렇게 어렵지...ㅎ
## 이진으로 풀어볼까?
## 알고리즘 처음 풀때로 돌아간 것 같다. 설계에 구멍이 너무 
    
import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int cnt, dist, res= Integer.MAX_VALUE;
        int n = stones.length;
        int min = Integer.MAX_VALUE, max = 0;
        boolean flag = false;
        
        // 최저, 최고
        for(int now : stones){
            if(now<min) min = now;
            if(now > max) max = now;
        }
        
        // 중간값+gap
        int mid = (min+max)/2;
        int gap = mid;
        
        while(min <= max){
            // gap 빼기
            for(int i=0;i<n;i++){
                stones[i] -= gap;
            }            
            
            dist = 0;
            cnt = mid;
            flag = true;
            
            
            // 건널 수 있는지 아닌지 확인
            for(int i=0;i<n;i++){
                if(stones[i] <= 0) dist++;
                else dist = 0;
                
                // 건널 수 없음
                if(dist >= k) {
                    flag = false;
                    break;
                }
            }
            
            if(flag){   // 끝까지 다 건넌다면
                min = mid + 1;
                gap = (min+max)/2 - mid;
                mid = (min+max)/2;
            }else{      // 다 건너지 못하면
                res = (res>cnt)?cnt:res;
                max = mid - 1;
                gap = (min+max)/2 - mid;
                mid = (min+max)/2;
            }
            
        }
        
        return res;
    }
}
