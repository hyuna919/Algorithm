import java.io.*;
import java.util.*;

/*
BOJ_S2_11279_최대힙

PriorityQueue PQ Comparator
PriorityQueue PQ 간단하게
 */
public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringBuilder builder = new StringBuilder();

//        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        });

        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if(x==0) {
                if(heap.isEmpty()) builder.append(0).append("\n");
                else builder.append(heap.poll()).append("\n");
            }
            else heap.offer(x);
        }

        System.out.println(builder);
    }


}
