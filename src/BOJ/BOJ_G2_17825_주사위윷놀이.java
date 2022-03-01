import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
* 정현님 추천문제
* 오랜만에 시뮬레이션 풀려니 눈물이 난다
* 머리가 딱딱
* 1차 풀이 : 일단 돌아는 간다 -> 답 하나도 안맞음ㅋㅋㅋㅋㅋㅋㅋㅋㅋ
* 2차 풀이 : 3번 테케가 211이 나온다(정답214) -> 이런 문제는 디버깅을 어떻게해야할지 조차 감이 안온다;;;
*/


public class BOJ_G2_17825_주사위윷놀이 {
	/* 말 */
	static class Player{
		int map, position;
		boolean arrival;
		
		public Player() {
			super();
			this.map = 0;
			this.position = -1;
			this.arrival = false;
		}

		@Override
		public String toString() {
			return "Player [map=" + map + ", position=" + position + ", arrival=" + arrival + "]";
		}
	}

	static class Position{
		int score;
		boolean isFull;
		public Position(int score) {
			super();
			this.score = score;
			this.isFull = false;
		}		
	}
	
	/* static */
	static int max = 0;
	static List<Player> list;
	static List<Position> map0, map1, map2, map3, map4;
	static int[] movement;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		/* 입력 데이터 */
		movement = new int[10];
		for (int i = 0; i < 10; i++) {
			movement[i] = Integer.parseInt(token.nextToken());
		}
		
		/* 말 생성 */
		list = new ArrayList<Player>();
		for (int i = 0; i < 4; i++) {
			list.add(new Player());
		}
		
		/* 지도 생성 */
		map0 = new ArrayList<Position>();
		map1 = new ArrayList<Position>();
		map2 = new ArrayList<Position>();
		map3 = new ArrayList<Position>();
		map4 = new ArrayList<Position>();
		
		for (int i = 1; i < 21; i++) map0.add(new Position(i*2));
		for (int i = 1; i < 4; i++) map1.add(new Position(10 + 3*i));
		for (int i = 1; i < 3; i++) map2.add(new Position(20 + 2*i));
		for (int i = 1; i < 4; i++) map3.add(new Position(29 - i));
		for (int i = 1; i < 4; i++) map4.add(new Position(20 + 5*i));
		

		/* 게임시작 */
		dfs(0, 0);
		
		System.out.println(max);
		
	}
	
	private static void dfs(int now, int cnt) {
		if(now>=10) {
			max = (max<cnt)?cnt:max;
//			System.out.println();
//			if(max>200) {
//				System.out.println();
//			}
			return;
		}
		
		Player player;
		int res;
		int map, position;
		boolean arrival;
		for (int i = 0; i < 4; i++) {
			player = list.get(i);
			
			// 도착한 말이면 제외
			if(player.arrival) continue;
			
			
//			System.out.println(player.toString());
			map = player.map;
			position = player.position;
			arrival = player.arrival;
			
			// 도착 위치에 다른 말이 있는지확인 + 이동
			player.position += movement[now];
			if(!changeMap(player, i)) {
				// 원복
				player.map = map;
				player.position = position;
				player.arrival = arrival;
				continue;
			}
			
			// 점수 계산
			res = cnt;
			if(!player.arrival) {
				switch (player.map) {
				case 0:
					if(player.position>=map0.size()) res += 0; // 도착이면 추가점수 0
					else res += map0.get(player.position).score;
					break;
				case 1:
					if(player.position == -1) res += map0.get(5-1).score;
					else res += map1.get(player.position).score;
					break;
				case 2:
					if(player.position == -1) res += map0.get(10-1).score;
					else res += map2.get(player.position).score;
					break;
				case 3:
					if(player.position == -1) res += map0.get(15-1).score;
					else res += map3.get(player.position).score;
					break;
				case 4:
					res += map4.get(player.position).score;
					break;
				}
			}
			
//			System.out.println(i+" "+now +" "+ (res-cnt) +" "+ cnt);
			dfs(now+1, res);
			
			// 원복
			player.map = map;
			
			player.position = position;
			player.arrival = arrival;
		}
		
	}

	/* 이동 이후 */
	static boolean changeMap(Player player, int idx) {
		/* map0이면*/
		if(player.map == 0) {	
			// 5단위 => true
			if(player.position<map0.size() && (player.position+1) %5 ==0) {
				switch (player.position) {
				case 5-1:
					player.map = 1;
					player.position = -1;
					break;
				case 10-1:
					player.map = 2;
					player.position = -1;
					break;
				case 15-1:
					player.map = 3;
					player.position = -1;
					break;
				case 20-1:
					player.map = 0;
					player.position = 19;
					break;
				default:
					break;
				}
				return isFull(player, idx);
			}else {
				return isArrive(player, idx);
			}
		} else if(player.map == 1) {
			if(player.position >= map1.size()) {
				player.map = 4;
				player.position -= map1.size();
				if(!isArrive(player, idx)) {
					player.map = 1;
					player.position += map1.size();
					return false;
				}
			}
		} else if(player.map == 2) {
			if(player.position >= map2.size()) {
				player.map = 4;
				player.position -= map2.size();
				if(!isArrive(player, idx)) {
					player.map = 2;
					player.position += map2.size();
					return false;
				}
			}
		} else if(player.map == 3) {
			if(player.position >= map3.size()) {
				player.map = 4;
				player.position -= map3.size();
				if(!isArrive(player, idx)) {
					player.map = 3;
					player.position += map3.size();
					return false;
				}
			}
		} else if(player.map == 4) {
			if(player.position >= map4.size()) {
				player.map = 0;
				player.position = player.position - map4.size() + 19;
				if(!isArrive(player, idx)) {
					player.map = 4;
					player.position = player.position + map4.size() + 19;
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isFull(Player player, int idx) {
		Player now;
		for (int i = 0; i < 4; i++) {
			if(i==idx) continue;
			now = list.get(i);
			if(now.position == player.position && now.map == player.map) return false;
		}
		return true;
	}

	private static boolean isArrive(Player player, int idx) {
		if(player.map == 0) {
			if(player.position >= map0.size()) {
				player.arrival = true;
				return true;
			}else if(!isFull(player, idx)){
				return false;
			}
			
		} else if(player.map == 4) {
			if(player.position >= map4.size()) {
				player.arrival = true;
				return true;
			}else if(!isFull(player, idx)){
				return false;
			}
		}
		return true;
	}

	
	
}
