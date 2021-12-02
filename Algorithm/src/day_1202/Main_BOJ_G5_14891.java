package day_1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 톱니바퀴
// 구조는 복잡하지만 확장성을 생각하면 괜찮은것같음

public class Main_BOJ_G5_14891 {
	
	static char[][] gears = new char[4][8];
	static boolean[] gearsTurn = new boolean[4];
	static int N = 8, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		for (int i = 0; i < 4; i++) {
			gears[i] = br.readLine().toCharArray();
		}
		
		K = Integer.parseInt(br.readLine());
		
		int gearNo, direction;
		while (K-->0) {
			token = new StringTokenizer(br.readLine());
			gearNo = Integer.parseInt(token.nextToken())-1; 	// gears가 배열이라 0부터 카운트
			direction = Integer.parseInt(token.nextToken());
			
			// 각 톱니 회전 여부 먼저 확인
			isTurn(gearNo);
			
			// 지정 톱니 회전
			turnGears(gearNo, direction);
		}
		
		// 점수 카운트
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			if(gears[i][0]=='1') sum |= 1<<i;
		}
		
		System.out.println(sum);
		
	}
	private static void turnGears(int gearNo, int direction) {
		// 기준 톱니
			if(direction==1) turnRight(gearNo);
			else turnLeft(gearNo);
		// 기준 좌측
		for (int left = gearNo-1; left >= 0; left--) {
			int tmp = direction;
			if(gearsTurn[left]) {
				if((gearNo-left)%2 == 1) tmp *= -1;

				if(tmp==1) turnRight(left);
				else turnLeft(left);
			}
			else {
				break;
			}
		}
		// 기준 우측
		for (int right = gearNo+1; right < 4; right++) {
			int tmp = direction;
			if(gearsTurn[right]) {
				if((right - gearNo)%2 == 1) tmp *= -1;

				if(tmp==1) turnRight(right);
				else turnLeft(right);
			}
			else {
				break;
			}}
		
	}
	private static void turnRight(int gearNo) {
		char tmp = gears[gearNo][7];
		for (int i = 7; i > 0; i--) {
			gears[gearNo][i] = gears[gearNo][i-1];
		}
		gears[gearNo][0] = tmp;
	}
	private static void turnLeft(int gearNo) {
		char tmp = gears[gearNo][0];
		for (int i = 0; i < 7; i++ ) {
			gears[gearNo][i] = gears[gearNo][i+1];
		}
		gears[gearNo][7] = tmp;
	}
	private static void isTurn(int center) {
		// N=0 , S=1
		// 좌[2] 우[6] 비교
		// 극이 다르면 반대로 회전, 같으면 회전 안함
		// 회전시킬 기어 왼쪽으로
		for (int left = center-1; left >= 0; left--) {
			if(gears[left][2] != gears[left+1][6]) {
				gearsTurn[left] = true;
			}else {
				gearsTurn[left] = false;
			}
		}

		// 회전시킬 기어 오른쪽
		for (int right = center+1; right < 4; right++) {
			if(gears[right-1][2] != gears[right][6]) {
				gearsTurn[right] = true;
			}else {
				gearsTurn[right] = false;
			}
		}
	}
}
