import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Position{
	int x, y, dist;

	public Position(int x, int y, int dist) {
		super();
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}


class Solution {
    static int N=5;
    static String input;
    static char[][] place;
    static boolean[][] visited;
    static boolean flag;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    
    public static int[] solution(String[][] places) {
        int[] res = {1,1,1,1,1};
        
		for (int t = 0; t < N; t++) {
			flag = true;
			place = new char[N][N];
			
			for (int i = 0; i < N; i++) {
				place[i] = places[t][i].toCharArray();				
			}
			
			top:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(place[i][j] != 'P') continue;
					if(!bfs(new Position(i,j,0))) {
						res[t] = 0;
						break top;
					}
				}
			}	
		}
        return res;
    }
    
    private static boolean bfs(Position curr) {		
		Queue<Position> q = new LinkedList<Position>();
		visited = new boolean[N][N];
		
		q.offer(curr);
		visited[curr.x][curr.y] = true;
		
		
		while(!q.isEmpty()) {
			curr = q.poll();
			curr.dist += 1;
			
			if(curr.dist > 2) return true;
			
			for (int i = 0; i <4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny]) continue;
                if(place[nx][ny]=='X') continue;
                
				if(place[nx][ny]=='P') {
					return false;
				}
				
			}
		}
		return true;
	}
}