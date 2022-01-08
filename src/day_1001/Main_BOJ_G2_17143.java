package day_1001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 낚시왕
 */
public class Main_BOJ_G2_17143 {
	static class Shark{
		int r,c,speed,dir,size;

		public Shark(int r, int c, int speed, int dir, int size) {
			super();
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	static int R,C,M;
	static int[][] map, clearMap;
	static ArrayList<Shark> list = new ArrayList<Shark>();
	static ArrayList<Integer> livingShark = new ArrayList<Integer>();
	
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token;
		

		token = new StringTokenizer(in.readLine());
		R = Integer.parseInt(token.nextToken());
		C = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		map = new int[R+1][C+1];
		
		list.add(new Shark(-1,-1,-1,-1,-1));	// 상어 1번부터 세기 위해서 0번에 더미 넣음, 0번으로 채워진 map에 상어 표기 쉽게하려고
		int r,c,s,d,z;
		for (int i = 1, end = M+1; i < end; i++) {	// 상어의 수만큼 반복
			token = new StringTokenizer(in.readLine());
			r = Integer.parseInt(token.nextToken());
			c = Integer.parseInt(token.nextToken());
			s = Integer.parseInt(token.nextToken());
			d = Integer.parseInt(token.nextToken());
			z = Integer.parseInt(token.nextToken());
			list.add(new Shark(r,c,s,d,z));
			livingShark.add(i);
			map[r][c] = i;
		}
		
		int sum = 0; // 낚시왕이 잡은 상어 크기 합
		clearMap = new int[R+1][C+1];
		for (int king = 1, end = C+1; king < end; king++) {	// 낚시왕이 오른쪽으로 한칸 이동(컬럼만큼 반복)
			sum += fishing(king);							// 낚시왕이 있는 열 중 땅과 제일 가까운 상어 잡기
			move();
			copyMap();
		}
		
		System.out.println(sum);
	}

	private static void copyMap() {
		for (int i = 1, endR= R+1; i < endR; i++) {
			for (int j = 1,endC = C+1; j < endC; j++) {
				map[i][j] = clearMap[i][j];
			}
		}
		clearMap = new int[R+1][C+1];
	}

	private static void move() {
		Shark shark, oldShark;
		int sharkNum, speed;
		for (int i = livingShark.size()-1; i > -1; i--) {	// 상어 1번부터 만들었음
			sharkNum = livingShark.get(i);
			shark = list.get(sharkNum);
			map[shark.r][shark.c] = 0; 		// 원래 위치 지우고
			// 상어의 속도가 높으면 무빙을 압축하기 위해 어떤 단위마다 같은자리+같은 방향을 유지하게되는지 결정
			if(shark.dir == 1 || shark.dir == 2) {	// 상하로 움직임
				speed = shark.speed % ((R-1)*2);
			} else {	// 좌우로 움직음
				speed = shark.speed % ((C-1)*2);
			}
			
			moveReal(shark, speed);
			
			// 지도에 새위치 기록(상어간 사냥고려)
			if(clearMap[shark.r][shark.c] == 0)	clearMap[shark.r][shark.c] = sharkNum;		// 지도에 새 위치 기록
			else {
				oldShark = list.get(clearMap[shark.r][shark.c]);
				if(oldShark.size < shark.size) {// 모든 상어는 크기가 달라서 이 경우만 고려하면 된다.
					
					clearMap[shark.r][shark.c] = sharkNum;
				}else {
					livingShark.remove(Integer.valueOf(sharkNum));
				}
			}
		}
	}

	private static void moveReal(Shark shark, int speed) {
		int nr, nc;
		nr = shark.r;
		nc = shark.c;
		for (int i = 0; i < speed; i++) {
			if(shark.dir==1 && nr==1) shark.dir = 2;
			else if(shark.dir==2 && nr==R) shark.dir = 1;
			else if(shark.dir==4 && nc==1) shark.dir = 3;
			else if(shark.dir==3 && nc==C) shark.dir = 4;
			nr += dr[shark.dir];
			nc += dc[shark.dir];
		}
		shark.r = nr;
		shark.c = nc;		
	}

	private static int fishing(int y) {
		Shark shark;
		for (int i = 1, end = R+1; i < end; i++) {	// 행만큼 반복
			if(map[i][y] != 0) {	// 상어가 있는칸->잡는다
				shark = list.get(map[i][y]);
				livingShark.remove(Integer.valueOf(map[i][y]));
				map[i][y] = 0;	// 지도에서 없앤다
				return shark.size;
			}
		}
		return 0;	// 아무것도 못잡음
	}
}
