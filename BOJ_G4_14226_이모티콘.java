import java.io.*;
import java.util.*;

/*
이모티콘
bfs

 */

public class Main {
    static class Node {
        int cnt, message, clipboard;

        public Node(int cnt, int message, int clipboard) {
            this.cnt = cnt;
            this.message = message;
            this.clipboard = clipboard;
        }
    }
    static int S;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        visited = new boolean[S+1][S+1];

        int res = bfs();

        System.out.println(res);
    }

    private static int bfs() {
        Queue<Node> q = new LinkedList();
        q.add(new Node(0,1,0));

        int cnt, message, clipboard;
        while (!q.isEmpty()){
            Node now = q.poll();
            for (int i = 0; i < 3; i++) {
                cnt = now.cnt;
                message = now.message;
                clipboard = now.clipboard;
                if(message==S) return cnt;
                if(message<1) break;

                if(i==0){           // copy
                    if(message>S) continue;
                    if(visited[message][message]) continue;
                    q.add(new Node(cnt+1, message, message));
                } else if (i==1) {  // paste
                    if(clipboard<1) continue;
                    if(message+clipboard>S || visited[message+clipboard][clipboard]) continue;
                    visited[message+clipboard][clipboard] = true;
                    q.add(new Node(cnt+1, message+clipboard, clipboard));
                } else {            // delete
                    if(message-1<1 || visited[message-1][clipboard]) continue;
                    visited[message][clipboard] = true;
                    q.add(new Node(cnt+1, message-1, clipboard));
                }
            }
        }
        return -1;
    }
}
