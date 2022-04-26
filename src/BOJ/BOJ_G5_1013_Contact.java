import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 100000000000001111 + 01111111
 * 011111+ 10000000001111
 * 구현문제. 정규표현식 관련 문제인줄
 */

public class Main {
    static int N,size;
    static char[] str;
    static String res;
    static String[] patterns = {"100","1","01"};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringBuilder builder = new StringBuilder();
        while(N-->0) {
            str = br.readLine().toCharArray();
            size = str.length;

            res = "NO";
            if(str[0]=='1' && str[1]=='0' && str[2]=='0') check(3,100);
            else if(str[0]=='0' && str[1]=='1') check(2, 1);


            builder.append(res+"\n");
        }

        System.out.println(builder.toString());
    }

    private static void check(int idx, int now) {
        if(now==100){
            // 0반복
            while (idx<size && str[idx]=='0') idx++;

            // 1반복
            int flag = 0;
            while (idx<size && str[idx]=='1') {
                idx++;
                flag++;
                if(idx==size) {
                    res = "YES";
                    return;
                }
            }

            if(flag>1 && idx+1<size && str[idx+1]=='0') check(idx+2, 100);
            else if(idx+1<size && str[idx+1] == '1') check(idx+2, 1);
        }

        else{
            if(idx>=size){
                res = "YES";
                return;
            }
            if(idx+2<size && str[idx]=='1' && str[idx+1]=='0' && str[idx+2]=='0') check(idx+3,100);
            else if(idx+1<size && str[idx]=='0' && str[idx+1]=='1') check(idx+2, 1);
        }

    }
}
