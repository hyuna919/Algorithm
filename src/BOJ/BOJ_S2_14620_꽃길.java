import java.io.*;
import java.util.*;

/*
꽃길
완탐

 */

public class Main {
    static class Pos{
        int x,y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Flower {
        int cost;
        Pos center;
        ArrayList<Pos> pos;

        public Flower(int cost, Pos center, ArrayList<Pos> pos) {
            this.cost = cost;
            this.center = center;
            this.pos = pos;
        }
    }
    static int N;
    static int[][] map;
    static ArrayList<Flower> list = new ArrayList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer token;
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        setAllCost();

        int min = Integer.MAX_VALUE, sum;
        int end = list.size();
        for (int i = 0; i < end-2; i++) {
            for (int j = i+1; j < end-1; j++) {
                for (int k = j+1; k < end; k++) {
                    sum = sum(i,j,k);
                    if(sum >= min) continue;

                    if(isSeperate(i,j,k)) min = sum;
                }
            }
        }

        System.out.println(min);
    }

    private static boolean isSeperate(int i, int j, int k) {
        Pos f1 = list.get(i).center;
        Pos f2 = list.get(j).center;
        Pos f3 = list.get(k).center;

        // 각 중점간 맨해튼 거리가 3미만이면 겹친다
        if(Math.abs(f1.x-f2.x)+Math.abs(f1.y-f2.y)<3) return false;
        if(Math.abs(f1.x-f3.x)+Math.abs(f1.y-f3.y)<3) return false;
        if(Math.abs(f3.x-f2.x)+Math.abs(f3.y-f2.y)<3) return false;

        return true;
    }

    private static int sum(int i, int j, int k) {
        int f1 = list.get(i).cost;
        int f2 = list.get(j).cost;
        int f3 = list.get(k).cost;

        return f1+f2+f3;
    }


    private static void setAllCost() {
        for (int i = 1, end=N-1; i < end; i++) {
            for (int j = 1; j < end; j++) {
                setCost(i,j);
            }
        }
    }

    private static void setCost(int x, int y) {
        int cost = map[x][y];
        ArrayList<Pos> pos = new ArrayList<>();

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x+dx[i];
            ny = y+dy[i];

            cost += map[nx][ny];
            pos.add(new Pos(nx,ny));
        }


        Flower flower = new Flower(cost, new Pos(x,y), pos);
        list.add(flower);
    }
}
