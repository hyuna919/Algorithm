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
*/

public class BOJ_G2_17825_주사위윷놀이 {
	/* 말 */
	static class Player{
		int map, position;
		boolean arrival;
		
		public Player() {
			super();
			this.map = 0;
			this.position = 0;
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
			return;
		}
		
		Player player;
		int res;
		for (int i = 0; i < 4; i++) {
			player = list.get(i);
			
			// 도착한 말이면 제외
			if(player.arrival) continue;
			
			// 도착 위치에 다른 말이 있는지확인 + 이동
//			System.out.println(player.toString());
			player.position += movement[now];
			if(!changeMap(player)) {
				player.position -= movement[now];
				continue;
			}
			
			// 점수 계산
			res = cnt;
			if(!player.arrival) {
				switch (player.map) {
				case 0:
					res += map0.get(player.position).score;
					break;
				case 1:
					if(player.position == -1) res += map0.get(5).score;
					else res += map1.get(player.position).score;
					break;
				case 2:
					if(player.position == -1) res += map0.get(10).score;
					else res += map2.get(player.position).score;
					break;
				case 3:
					if(player.position == -1) res += map0.get(15).score;
					else res += map3.get(player.position).score;
					break;
				case 4:
					res += map4.get(player.position).score;
					break;
				}
			}
			
			
			dfs(now+1, res);
		}
		
	}

	/* 이동 이후 */
	static boolean changeMap(Player player) {
		/* map0이면*/
		if(player.map == 0) {	
			// 5단위 => true
			if(player.position %5 ==0) {
				switch (player.position) {
				case 5:
					player.map = 1;
					player.position = -1;
					break;
				case 10:
					player.map = 2;
					player.position = -1;
					break;
				case 15:
					player.map = 3;
					player.position = -1;
					break;
				case 20:
					player.map = 0;
					player.position = 19;
					break;
				default:
					break;
				}
				return true;
			}else {
				return isArrive(player);
			}
		} else if(player.map == 1) {
			if(player.position >= map1.size()) {
				player.map = 4;
				player.position -= map1.size();
				if(!isArrive(player)) {
					player.map = 1;
					player.position += map1.size();
					return false;
				}
			}
		} else if(player.map == 2) {
			if(player.position >= map2.size()) {
				player.map = 4;
				player.position -= map2.size();
				if(!isArrive(player)) {
					player.map = 2;
					player.position += map2.size();
					return false;
				}
			}
		} else if(player.map == 3) {
			if(player.position >= map3.size()) {
				player.map = 4;
				player.position -= map3.size();
				if(!isArrive(player)) {
					player.map = 3;
					player.position += map3.size();
					return false;
				}
			}
		} else if(player.map == 4) {
			if(player.position >= map4.size()) {
				player.map = 0;
				player.position = player.position - map4.size() + 19;
				if(!isArrive(player)) {
					player.map = 4;
					player.position = player.position + map4.size() + 19;
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isArrive(Player player) {
		if(player.map == 0) {
			if(player.position >= map0.size()) {
				player.arrival = true;
				return true;
			}else if(map0.get(map0.size()-1).isFull){
				return false;
			}
			
		} else if(player.map == 4) {
			if(player.position >= map4.size()) {
				player.arrival = true;
				return true;
			}else if(map4.get(map4.size()-1).isFull){
				return false;
			}
		}
		return true;
	}

	
	
}
