import java.io.*;
import java.util.*;

/*
BOJ_S5_11723_집합
 */
public class Main {
    static int M;
    String ADD="add", RMV="remove", CHCK="check", TGL="toggle", ALL="all", EMT="empty";
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());

        Set<Integer> set = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            String cmd = token.nextToken();
            if("all".equals(cmd)) for (int j = 1; j <= 20; j++) set.add(j);
            else if("empty".equals(cmd)) set.clear();
            else {
                int num = Integer.parseInt(token.nextToken());

                if("add".equals(cmd)) set.add(num);
                else if("remove".equals(cmd)) set.remove(num);
                else if("check".equals(cmd)) {
                    if(set.contains(num)) builder.append(1).append("\n");
                    else builder.append(0).append("\n");
                }
                else if("toggle".equals(cmd)) {
                    if(set.contains(num)) set.remove(num);
                    else set.add(num);
                }
            }
        }
        System.out.println(builder);
    }
}
