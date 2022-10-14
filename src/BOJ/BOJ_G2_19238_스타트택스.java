import java.util.*;
import java.io.*;

/*
 * 스타트택시
 * 
 * 예제 틀림 - 손님한테 가는 길 연료 소모를 안해서 틀렸다
 * dfs - 시간초과
 * bfs - 틀림
 * 고객구할떄 -> minDis갱신과 도착여부가 따로 놀고있음
 */
public class Main {
	static class Person{
		int sx, sy, dx, dy;
		int distance;
		public Person(int sx, int sy, int dx, int dy, int distance) {
			super();
			this.sx = sx;
			this.sy = sy;
			this.dx = dx;
			this.dy = dy;
			this.distance = distance;
		}

	}
	static class Taxi{
		int x,y, fuel;

		public Taxi(int x, int y, int fuel) {
			super();
			this.x = x;
			this.y = y;
			this.fuel = fuel;
		}
	}
	
	static int N, M, minDis;
	static int[][] map, visited;
	static Taxi taxi;
	static ArrayList<Person> guestList;
	// 상하좌우
	static int[] ddx = {-1,1,0,0};
	static int[] ddy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		int fuel = Integer.parseInt(token.nextToken());
		
		// 지도 입력
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		// 택시 입력
		token = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(token.nextToken())-1;
		int y = Integer.parseInt(token.nextToken())-1;
		taxi = new Taxi(x, y, fuel);
		
		// 손님 목록 입력
		guestList = new ArrayList<>();
		int sx, sy, dx, dy;
		for (int i = 0; i < M; i++) {
			token = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(token.nextToken())-1;
			sy = Integer.parseInt(token.nextToken())-1;
			dx = Integer.parseInt(token.nextToken())-1;
			dy = Integer.parseInt(token.nextToken())-1;
			guestList.add(new Person(sx, sy, dx, dy,0));
		}
		
		// 손님목록 돌면서 운전시작
		int idx, distance = 0;
		Person guest;
		while(!guestList.isEmpty()) {
			// 가장 가까이 있는 손님 고르기
			minDis = Integer.MAX_VALUE;
			idx = getGuest();
			if(idx==-1) {
				minDis = Integer.MAX_VALUE;
				break;
			}
			guest = guestList.get(idx);
			taxi.fuel -= minDis;	// 손님하테 가는 길 영ㄴ료 소모
			
			// 목적지 최단거리 고르기
			visited = new int[N][N];
			minDis = Integer.MAX_VALUE;
			getRoute(guest.sx, guest.sy,guest.dx, guest.dy);
			
			if(minDis==Integer.MAX_VALUE) break;
			
			// 성공
			taxi.x = guest.dx;
			taxi.y = guest.dy;
			taxi.fuel += minDis;
			guestList.remove(idx);
		}
		if(minDis==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(taxi.fuel);
	}
	
	private static void getRoute(int sx, int sy, int dx, int dy) {
		Queue<Taxi> q = new LinkedList<Taxi>();
		q.add(new Taxi(sx,sy,0));
		
		Taxi now;
		int nx, ny, distance;
		while(!q.isEmpty()) {
			now = q.poll();
			
			distance = now.fuel; 	// 변수 섞여버려서;;
			// 도착
			if(now.x==dx && now.y==dy) {
				minDis = distance;
				return;
			}
			// 연료 다 썼는데 도착 못한 상태
			if(distance==taxi.fuel) return;
			// 도착못했는데 최단보다 크거나 같다
			if(distance>=minDis) return;
			distance++;
			for (int i = 0; i < 4; i++) {
				nx = now.x+ddx[i];
				ny = now.y+ddy[i];
				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				if(map[nx][ny]==1) continue;
				if(visited[nx][ny]!=0 && visited[nx][ny]<=distance) continue;	// 더 적은 거리로 방문한 적 있으면
				
				
				
				visited[nx][ny] = distance;
				q.add(new Taxi(nx,ny,distance));
			}
		}
	}

	private static int getGuest() {
		int minGuestIdx=0;
		int tx = taxi.x;
		int ty = taxi.y;
		visited = new int[N][N];
		minDis = Integer.MAX_VALUE;
		getRoute(tx,ty, guestList.get(0).sx, guestList.get(0).sy);
		int minGuestDis = minDis;
		for (int idx = 1, end=guestList.size(); idx < end; idx++) {
			// 이번 고객과 택시 거리 구하기
			Person guest = guestList.get(idx);
			minDis = Integer.MAX_VALUE;
			visited = new int[N][N];
			getRoute(tx,ty, guest.sx, guest.sy);
			// min과 비교
			if(minDis < minGuestDis) {
				minGuestDis = minDis;
				minGuestIdx = idx;
			}
			// min과 같으면 행열 비교
			else if(minDis == minGuestDis) {
				if(guestList.get(minGuestIdx).sx>guest.sx) {
					minGuestIdx = idx;
				}else if(guestList.get(minGuestIdx).sx==guest.sx) {	// 행같으면 열비교
					if(guestList.get(minGuestIdx).sy>guest.sy) minGuestIdx = idx;
				}
			}
		}
		
		if(minGuestDis==Integer.MAX_VALUE) return -1;
		minDis = minGuestDis;
		return minGuestIdx;
	}
}
