package day_0810;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution_D4_1233 {
    static int T=10, N, ans;
    static String [] currNode;
    static String tmp;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         
        for (int t = 1; t < T+1; t++) {
            ans = 1;    // 기본 값을 정답으로
            N = Integer.parseInt(in.readLine());    // 노드 개수 받기
            if(N%2==0) {                            // 짝수라면 피연산자 부족으로 무효
                ans = 0;
                while(N-->0) {
                    in.readLine();
                }
            }else {
                while(N-->0) {                       // 이번 테케의 노드 순회
                    currNode = in.readLine().split(" ");
                    if(currNode.length == 2) { // 리프인데 연산자면 무효
                        tmp = currNode[1];
                        if((tmp.equals("+")) || (tmp.equals("-")) || (tmp.equals("*")) || (tmp.equals("/")))  {
                            ans = 0;
                            while(--N>0) {
                                in.readLine();
                            }
                            break;
                        }
                    }else { // currNode.length가 4라면, ... 3인 경우는 위의 짝수 거르는 곳에서 걸러짐
                            // 리프가 아닌데 연산자가 아니면(숫자면) 무효
                        tmp = currNode[1];
                        if(!(tmp.equals("+")) && !(tmp.equals("-")) && !(tmp.equals("*")) && !(tmp.equals("/")))  {
                            ans = 0;
                            while(N-->0) {
                                in.readLine();
                            }
                            break;
                        }
                    }
                }
            }
            System.out.println("#"+t+" "+ans);
        }
    }
}