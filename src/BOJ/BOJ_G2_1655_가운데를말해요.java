import java.io.*;
import java.util.*;

/*
가운데를 말해요

특징) 시간제한 0.1초

풀이1) 처음엔 시간제한 모르고 Collections.sort()를 매번 하려고 했는데 시간제한 때문에 실패
풀이2) 이진탐색으로 추가 위치 알아내서 정렬 유지하면서 추가함

주의) 중간에 삽입하는거라 LinkedList는 안되나 했는데 LinkedList는 인덱스가 없기 때문에 list.get(n)마다 O(n)이 걸리게된다.
 */

public class Main {
    static int N;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            // 삽입해야하는 위치 알기
            int value = Integer.parseInt(br.readLine());
            int idx = findIndex(value);
            list.add(idx,value);

            // 중간값 -> int 나누기는 어차피 버림연산 자동이라 짝홀 조건 신경 안써도 된다.
            builder.append(list.get(i/2)+"\n");
        }

        System.out.println(builder.toString());

    }

    private static int findIndex(int input) {
        int left = 0;
        int right = list.size()-1;
        int mid = (left + right)/2;

        int value;
        while(left <= right) {
            mid = (left + right)/2;
            value = list.get(mid);

            if(value > input) right = mid - 1;
            else left = (mid++) + 1;
        }

        return mid;
    }
}
