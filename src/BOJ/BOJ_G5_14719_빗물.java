import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(token.nextToken());
        int W = Integer.parseInt(token.nextToken());


        int[][] map = new int[H][W];    // 0:빈공간  1:블록  2:물


        token = new StringTokenizer(br.readLine());
        for (int w = 0; w < W; w++) {
            int h = Integer.parseInt(token.nextToken());
            for (int i = H-1; i >= H-h; i--) {
                map[i][w] = 1;
            }
        }

        int res = 0;
        int cnt = 0;
        int flag =0;
        for (int h = H-1; h >= 0 ; h--) {
            flag = 0;
            cnt = 0;
            for (int w = 0; w < W; w++) {
                if(map[h][w]==1) {
                    if(flag==0) flag=1;
                    else if(flag==1) {
                        res += cnt;
                        cnt = 0;
                    }
                }else if(flag==1 && map[h][w]==0) {
                    cnt++;
                }
            }
        }

        System.out.println(res);
    }
}


