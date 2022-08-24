import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
평행선 문제의 지문이 모호합니다.
푸실 분들은 꼭 질문 검색의 "맞왜틀 외치실 분들꼐.."를 참고해 푸시는 걸 추천합니다.

같은 위치의 점은 가로선, 세로선 각각 카운트해야합니다.
 */
public class Main {
    static int N;
    static Map<Integer,Integer> xlist, ylist;
    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        N = Integer.parseInt(br.readLine());
        xlist = new HashMap<>();
        ylist = new HashMap<>();

        int cnt = 0;
        int x,y;
        int value;
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(br.readLine());
            x = Integer.parseInt(token.nextToken());
            y = Integer.parseInt(token.nextToken());

            value = 0;
            if(xlist.containsKey(x)) value = xlist.get(x);
            if(value==1) cnt++;
            xlist.put(x,value+1);

            value = 0;
            if(ylist.containsKey(y)) value = ylist.get(y);
            if(value==1) cnt++;
            ylist.put(y,value+1);
        }

        System.out.println(cnt);
    }
}
