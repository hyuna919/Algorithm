import java.io.*;
import java.util.*;

/*
BOJ_S2_1074_Z

N번 4분면으로 쪼개기
 */
public class Main {

    static int N, r, c;
    public static void main(String[] args) throws IOException {
        // 0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        r = Integer.parseInt(token.nextToken());
        c = Integer.parseInt(token.nextToken());

        int pow = (int)Math.pow(2,N);
        find(N, 0,0, pow, pow, 0, pow*pow-1);

    }

    private static void find(int depth, int r1, int c1, int r2, int c2, int start, int end) {
        if(depth==0) {
            System.out.println(start);
            return;
        }
        int rm = (r1+r2)/2;
        int cm = (c1+c2)/2;
        int gap = (end-start+1)/4;

        if(r<rm) {    // 2, 1사분면
            if(c<cm) {   // 2사분면
                find(depth-1,r1,c1,rm,cm, start, start+gap-1);
            } else {        // 1사분면
                find(depth-1,r1,cm,rm,c2, start+gap, start+(gap*2)-1);
            }
        } else {        // 3, 4사분면
            if(c<cm) {   // 3사분면
                find(depth-1,rm,c1,r2,cm, start+(gap*2), start+(gap*3)-1);
            } else {        // 4사분면
                find(depth-1,rm,cm,r2,c2, start+(gap*3), end);
            }
        }
    }

}
