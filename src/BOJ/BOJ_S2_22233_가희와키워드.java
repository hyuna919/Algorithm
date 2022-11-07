import java.io.*;
import java.util.*;

/*
가희와 키워드

set사용
 */

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        Set set = new HashSet<String>();
        // 메모장 키워드
        for (int i = 0; i < N; i++) set.add(br.readLine());

        // 글쓰면서 키워드 삭제
        for (int i = 0; i < M; i++) {
            token = new StringTokenizer(br.readLine(),",");
            while(token.hasMoreTokens()){
                String keyword = token.nextToken();
                set.remove(keyword);
            }

            // 남은 키워드 확인
            System.out.println(set.size());
        }
    }
}
