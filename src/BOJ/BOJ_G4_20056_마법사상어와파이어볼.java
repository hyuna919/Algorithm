import java.util.*;
import java.io.*;

/*
 * 마법사 상어와 파이어볼
 * d==0은 짝인가 홀인가
 * 홀짝 갯수 세는 곳에서 뜬금없이 4랑 비교하면서 오류찾는데 시간이 걸렸다. 구조가 복잡해질수록 손코딩 제발..!
 * 🧡경계 넘어가는 아이디어가 아주 아름답다🧡
 */
public class Main {
	static class Position{
		int x,y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static class Fire{
		int idx,x,y,m,s,d;

		public Fire(int idx, int x, int y, int m, int s, int d) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}		
	}
	static int N, M, K, lastIdx=0;
	static ArrayList<Integer>[][] map;
	static Map<Integer,Fire> fireList = new HashMap<Integer, Fire>();
	// 상-상우-우-우하-하-하좌-좌-좌상
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	static int[] dde = {0,2,4,6};
	static int[] ddo = {1,3,5,7};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());

		
		
		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		int x,y,m,s,d;
		for (int i = 0; i < M; i++) {
			token = new StringTokenizer(br.readLine());
			x = Integer.parseInt(token.nextToken())-1;
			y = Integer.parseInt(token.nextToken())-1;
			m = Integer.parseInt(token.nextToken());
			s = Integer.parseInt(token.nextToken());
			d = Integer.parseInt(token.nextToken());
			
			map[x][y].add(lastIdx);
			fireList.put(lastIdx, new Fire(lastIdx, x, y, m, s, d));
			lastIdx++;
		}
		
		while(K-->0) {
			// 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다
			int nx,ny;
			for (Fire fire : fireList.values()) {
				int idx = fire.idx;
				// 기존 자리에서 제거
				for (int i = 0, end=map[fire.x][fire.y].size(); i < end; i++) {
					if(map[fire.x][fire.y].get(i)==idx) {
						map[fire.x][fire.y].remove(i);
						break;
					}
				}
				
				
				// 새자리
				d = fire.d;
				s = fire.s;
				nx = fire.x+(dx[d]*s);
				ny = fire.y+(dy[d]*s);
				
				// 새자리 - 경계넘는 경우
				nx %= N;
				ny %= N;
				if(nx<0) nx = N + nx;
				if(ny<0) ny = N + ny;
				
				
				// 갱신
				map[nx][ny].add(idx);
				fireList.put(idx, new Fire(idx, nx, ny, fire.m, s, d));
			}
			
			// 칸 확인하면서 여러개있는 곳이면 겹치기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 2 미만이면 패스
					if(map[i][j].size()<2) continue;
					
					// 합치기(질량,속도,방향 구하기)
					m = 0;
					s = 0;
					int dirFlag = 0;
					ArrayList<Integer> nowList = map[i][j];
					int size = nowList.size();
					
					for (int k = 0; k < size; k++) {
						int idx = nowList.get(k);
						Fire fire = fireList.get(idx);
						m += fire.m;
						s += fire.s;
						if((fire.d%2)==0) dirFlag++;
						else dirFlag--;
						
						fireList.remove(fire.idx);
					}

					// 합쳤으니 기존 것들 map에서 삭제
					map[i][j] = new ArrayList<>();
					
					// 새 파이어볼
					m /= 5;
					if(m==0) continue;
					s /= size;
					int[] ddd = new int[4];
					// 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다
					if(dirFlag==size || dirFlag==(size*-1)) ddd = dde;
					else ddd = ddo;					
					
					// 합친거 4개로 나누기
					for (int k = 0; k < 4; k++) {
						map[i][j].add(lastIdx);
						fireList.put(lastIdx, new Fire(lastIdx, i, j, m, s, ddd[k]));
						lastIdx++;
					}v
				}
			}
		}
		
		// K번 명령한 후, 남아있는 파이어볼 질량의 합을 구해보자.
		m = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int idx : map[i][j]) {
					m += fireList.get(idx).m;
				}
			}
		}
		
		System.out.println(m);
	}
}
