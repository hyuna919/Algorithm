package day_1001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 컨베이어벨트 위의 로봇
 * 
 */
public class Main_BOJ_S1_20055 {
	static int N, K, zero;
	static int[] belt;
	static boolean robot[];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());
		robot = new boolean[(2*N)+1];
		belt = new int[(2*N)+1];
		
		token = new StringTokenizer(in.readLine());
		for (int i = 1, end=2*N+1; i < end; i++) {
			belt[i] = Integer.parseInt(token.nextToken());
			if(belt[i] == 0) zero++;
		}
		
		int cnt = 0;
		while(zero<K) {
			cnt++;
			rotation();
			move();
			// 로봇 올리기
			if(belt[1]>0 && !robot[1]) {
				belt[1]--;
				robot[1] = true;
				if(belt[1]==0) {	// 벨트 내구도가 0이되면
					zero++;
					continue;
				}
			}
		}
		
		System.out.println(cnt);
	}
	
	private static void move() {
		for (int i = 2*N; i > 0; i--) {
			if(!robot[i]) continue; 	// 로봇 없으면 패스
			if(i!=2*N) {
				if(!robot[i+1] && belt[i+1]>0) {	// 다음 자리 비어있고 내구도 남았으면 로봇 이동
					robot[i+1] = true;
					robot[i] = false;
					belt[i+1]--;
					if(belt[i+1]==0) {	// 벨트 내구도가 0이되면
						zero++;
					}
					if(i+1 == N) {	// 내리는 자리
						robot[N] = false;
					}
				}
			}else { //2N위치면 1로 이동
				robot[1] = true;
				robot[1] = false;
				belt[1]--;
				if(belt[1]==0) {	// 벨트 내구도가 0이되면
					zero++;
				}
			}
		}
	}

	private static void rotation() {
		int tmpBelt = belt[2*N];
		boolean tmpRobot = robot[2*N];
		for (int i = 2*N; i > 0; i--) {
			// 1. 벨트가 각 칸 위에있는 로봇과 함꼐 한 칸 회전
			belt[i] = belt[i-1];
			robot[i] = robot[i-1];			
		}
		robot[N] = false;
		belt[1] = tmpBelt;
		robot[1] = tmpRobot;
	}
}
