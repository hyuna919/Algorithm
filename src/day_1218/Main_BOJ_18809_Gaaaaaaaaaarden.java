package day_1218;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.InputMap;

// 조합 + 조합 => 순열
// !!!!!! 턴마다 맵을 나누지 말고 다음엔 맵에 턴 정보를 기록하자  !!!!!!!


public class Main_BOJ_18809_Gaaaaaaaaaarden{
	static class Position{
		int x,y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, R, G, max;
	static int[][] groundMap;
	static int[] choice;
	static char[][] nowMap, confirmMap, startMap;
	static List<Position> canList;
	// 상하좌우
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		G = Integer.parseInt(token.nextToken());
		R = Integer.parseInt(token.nextToken());
		
		groundMap = new int[N][M];
		canList = new ArrayList<Position>();
		
		int tmp;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tmp = Integer.parseInt(token.nextToken());
				groundMap[i][j] = tmp;
				// 배양액 뿌릴 수 있는 땅은 따로 저장
				if(tmp == 2) canList.add(new Position(i, j));
			}
		}
		
		// 배양액 뿌릴 조합
		// 초록끼리  조합 -> 남은 자리에 빨강끼리 조합
		// 어떤 자리를 선택했는지는 canList의 인덱스로 기억한다
		choice = new int[canList.size()];
		startMap = new char[N][M];
		confirmMap = new char[N][M];
		nowMap = new char[N][M];
		
		combG(canList.size(), G);
		
		System.out.println(max);
	}

	//rCn nCr
	private static void combG(int n, int r) {
		if(r<=0) {
			combR(canList.size(), R);
			return;
		}
		if(n<=0) return;
		
		Position now = canList.get(n-1);
		
		startMap[now.x][now.y] = 'g';
		choice[n-1] = 1;
		combG(n-1,r-1);	// 선택한 경우
		startMap[now.x][now.y] = '\0';
		choice[n-1] = 0;
		combG(n-1,r); // 선택 안한 경우
	}
	
	private static void combR(int n, int r) {
		if(r<=0) {
			for (int i = 0; i < N; i++) {
				confirmMap[i] = startMap[i].clone();
				nowMap[i] = startMap[i].clone();
			}
			bfs();
			return;
		}
		if(n<=0) return;
		
		if(r>0) {
			if(choice[n-1]==0) {
				startMap[canList.get(n-1).x][canList.get(n-1).y] = 'r';
				choice[n-1] = 1;
				combR(n-1,r-1);	// 선택한 경우
				startMap[canList.get(n-1).x][canList.get(n-1).y] = '\0';
				choice[n-1] = 0;
			}
			combR(n-1,r); // 선택 안한 경우
		}	
		
	}

	private static void bfs() {
		Queue<Position> q = new LinkedList<Position>();
		int x, y;
		Position now;
		for (int i = 0, end = choice.length; i < end; i++) {
			if(choice[i] == 0 ) continue;
			now = canList.get(i);
			x = now.x;
			y = now.y;
			q.add(new Position(x, y));
		}
		
		int nx, ny, flower=0, turn;
		char color;
		List<Position> addList;
		while(!q.isEmpty()) {
			turn = q.size();
			addList = new ArrayList<Position>();
			for (int t = 0; t < turn; t++) {
				now = q.poll();
				x = now.x;
				y = now.y;
				color = confirmMap[x][y];
				
				for (int i = 0; i < 4; i++) {
					nx = x + dx[i];
					ny = y + dy[i];
					
					// 경계값
					if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
					// 이미 꽃이거나 || 방문했거나 || 호수라면
					if(nowMap[nx][ny] == 'f' || confirmMap[nx][ny]!='\0' || groundMap[nx][ny]==0) continue;
					
					if(nowMap[nx][ny] == '\0') {
						nowMap[nx][ny] = color;
						addList.add(new Position(nx, ny));
					}else if(nowMap[nx][ny] != color) {	// 비어있지 않은데 색이 다르면
						nowMap[nx][ny] = 'f';
						flower++;
						for (Position position : addList) {
							if(position.x==nx && position.y==ny) {
								
								// 공간복잡도
								// 이번에 추가돼야하는 걸 넣어두고
								addList.remove(position);
								break;
							}
						}
						continue;
					}
				}
			}
			// 턴이 끝나면 confirmap으로 옯긴다.
			for (int i = 0; i < N; i++) {
				confirmMap[i] = nowMap[i].clone();
			}
			for (Position position : addList) {
				q.add(position);
			}
		}
		max = (max<flower)? flower: max;
	}
}
