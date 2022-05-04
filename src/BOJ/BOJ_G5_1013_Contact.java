import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 100000000000001111 + 01111111
 * 011111+ 10000000001111
 * 구현문제. 정규표현식 관련 문제인줄
 * 정규표현식으로는 이렇게 푼다
 */

public class Main {
    static int N;
    static String res;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringBuilder builder = new StringBuilder();
        while(N-->0) {
            String str = br.readLine();

            res = "NO";

            String pattern = "(100+1+|01)+";
            if(Pattern.matches(pattern,str)) res = "YES";

            builder.append(res+"\n");
        }

        System.out.println(builder.toString());
    }
}
