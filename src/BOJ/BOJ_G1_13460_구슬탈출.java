import java.util.*;
import java.io.*;

/*
 * 구슬탈출 2
 *  bfs
 *  조건 자체는 어렵지 않음
 *  두 구슬을 합쳐지지 않게 같이 움직이는게 생각보다 손이 많이 간다.
 *  
 *  반례
 *  3 7
	#######
	#B..RO#
	#######
 */
public class Main {
	static class Step{
		int move;
		Ball red, blue;
		public Step(int move, Ball red, Ball blue) {
			super();
			this.move = move;
			this.red = red;
			this.blue = blue;
		}
	}
	static class Ball{
		int x,y;

		public Ball(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int N, M;
	static char[][] map;
	static Ball red, blue;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static ArrayList<Integer> visited = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		map = new char[N][M];
		
		// 보드 입력 + 공 위치(는 비워둔다)
		char now;
		String input ="";
		for (int i = 0; i < N; i++) {
			input ="";
			while(input.isEmpty())input = br.readLine();
			for (int j = 0; j < M; j++) {
				now = input.charAt(j);
				if(now=='R') {
					red = new Ball(i,j);
					map[i][j] = '.';
				}
				else if(now=='B') {
					blue = new Ball(i,j);
					map[i][j] = '.';
				}
				else map[i][j] = now;
			}
		}
		
		int res = bfs();
		System.out.println(res);
	}
	private static int bfs() {
		Queue<Step> q = new LinkedList<>();
		q.add(new Step(0,red,blue));
		
		Step now;
		int rnx, rny, bnx,bny;
		while(!q.isEmpty()) {
			now = q.poll();
			if(now.move>=10) continue;
			for (int dir = 0; dir < 4; dir++) {
				// 한쪽 방향으로 공 계속 굴리기 ~ 다른 공이나 벽을 만날 떄까지 + 파란공 먼저
				rnx = now.red.x;
				rny = now.red.y;
				bnx = now.blue.x;
				bny = now.blue.y;
				map[rnx][rny] = 'R';
				map[bnx][bny] = 'B';
				boolean rFlag=false, bFlag=false, oFlag=false, finish=false;
				while(!rFlag || !bFlag) {
					// 파란공
					if(!bFlag) {
						map[bnx][bny] = '.';
						bnx += dx[dir];
						bny += dy[dir];
						if(map[bnx][bny]=='#') { 		// 벽검사(>벽검사)
							bnx -= dx[dir];
							bny -= dy[dir];
							bFlag = true;
						}
						else if(map[bnx][bny]=='R') {	// 전진 방향에 빨간공
							if(map[bnx+dx[dir]][bny+dy[dir]]=='#') {	// 빨간공 앞에 벽
								bnx -= dx[dir];
								bny -= dy[dir];
								bFlag = true;
							}else {		// 빨간 공 앞이 길이면 같이 전진
								map[rnx][rny] = '.';
								rnx += dx[dir];
								rny += dy[dir];
								if(map[rnx][rny]=='O') {	// 파랑-빨강이 붙어서 같이 움직이는 중에 둘 중 하나가 빠지면 실패인 경우임
									rnx -= dx[dir];
									rny -= dy[dir];
									oFlag = true;
									break;
								}
								map[rnx][rny] = 'R';
								rFlag = true;				// 파랑빨강 같이 움직일테니 빨강은 움직이지 말 것
							}
						}
						// 파란 공이 구멍에 빠짐
						if(map[bnx][bny]=='O') {	
							bnx -= dx[dir];
							bny -= dy[dir];	
							oFlag = true;
							break;
						}
						map[bnx][bny] = 'B';
					}
					
					// 빨간공
					if(!rFlag) {
						map[rnx][rny] = '.';
						rnx += dx[dir];
						rny += dy[dir];
						if(map[rnx][rny]=='#') { 		// 벽검사(>벽검사)
							rnx -= dx[dir];
							rny -= dy[dir];
							rFlag = true;
						}
						else if(map[rnx][rny]=='B') {	// 전진 방향에 파란공
							if(map[rnx+dx[dir]][rny+dy[dir]]=='#') {	// 빨간공 앞에 벽
								rnx -= dx[dir];
								rny -= dy[dir];
								rFlag = true;
							}else {		// 파란 공 앞이 길이면 같이 전진
								map[bnx][bny] = '.';
								bnx += dx[dir];
								bny += dy[dir];
								if(map[bnx][bny]=='O') {	
									bnx -= dx[dir];
									bny -= dy[dir];	
									oFlag = true;
									break;
								}
								map[bnx][bny] = 'B';
								bFlag = true;
							}
						}
						// 빨간 공이 구멍에 빠짐
						if(map[rnx][rny]=='O') {	
							rnx -= dx[dir];
							rny -= dy[dir];
							finish = true;
							rFlag = true;
						}
						map[rnx][rny] = 'R';
					}
				}
				map[bnx][bny] = '.';
				map[rnx][rny] = '.';
				// 파란 공이 구멍에 빠진경우를 제외하고 q에 추가 
//				if(oFlag) {
//					rFlag = true;
//					bFlag = true;
//					continue;
//				}
				if(!oFlag) {	// 파란공이 구멍에 들어가지 않음 + 온적없는 배치 + 공이 움직임
					if(finish) return now.move+1;
					int position = (rnx*1000)+(rny*100)+(bnx*10)+bny;
					if(!visited.contains(position)) {
						visited.add(position);
						q.add(new Step(now.move+1, new Ball(rnx,rny), new Ball(bnx,bny)));
					}
				}
				
			}
		}
		return -1;
	}
}
