import java.util.*;

/*
k진수에서 소수 개수 구하기

문제1) 숫자가 클걸 걱정하고 에라토스테네스의 채로 풀이 -> 그냥 제곱근까지 나눠보는 걸로 충분히 작아진다.
문제2) 각 후보 숫자가 int를 넘을 수 있음
문제3) **또 범위 실수 : 소수 판별할 때 end=Math.sqrt()에서 1을 더해줘야한다**
 

 */
class Solution {
    static int size;
    static int[] number;
    public int solution(int n, int k) {
        // k진수로 바꾸기 -> static number
        binary2k(n,k);

        // 포함된 숫자
        long sub = 0;
        ArrayList<Long> list = new ArrayList<>();
        for(int i = 0; i<=size;i++){
            sub *= 10;

            if(number[i]==0) {
                sub /= 10;
                if(sub==0 || sub==1) {
                    sub = 0;
                    continue;
                }

                list.add(sub);
                sub = 0;
            } else sub += number[i];
        }
        list.add(sub);

        // 소수인지 확인
        int cnt = 0;
        for(long num:list){
            if(isPrime(num)) cnt++;
        }

        // 결과
        return cnt;
    }

    boolean isPrime(long num){
        if(num == 0 || num == 1) return false;

        for(int i=2, end=(int)Math.sqrt(num)+1; i<end; i++) {
            if(num%i==0) return false;
        }
        return true;
    }

    void binary2k(int n, int k){
        ArrayList<Integer> list = new ArrayList<>();

        while(n>=k){
            list.add(n%k);
            n /= k;
        }
        list.add(n);

        size = list.size();
        number = new int[size];
        size--;
        for(int i=0;i<=size;i++) number[i] = list.get(size-i);
    }
}
