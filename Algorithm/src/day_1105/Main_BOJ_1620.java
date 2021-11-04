package day_1105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 나는야 포켓몬 마스터 이다솜
// 시간제한에 걸려서 빠르게 찾기위해 리스트를 두개 유지함
// 인텍스 기준 정렬된 dicIdx, 이름 기준 정렬된 dicName
// dicName은 이름기준 정렬하고나서 원래 idx알기 위해 Monster 클래스의 ArrayList로 만들었다
// 근데 이거 HashMap으로 쓰면 훤씬 쉽고 빠른가보네;

public class Main_BOJ_1620 {
	static class Monster implements Comparable<Monster>{
		int idx;
		String name;
		
		public Monster(int idx, String name) {
			super();
			this.idx = idx;
			this.name = name;
		}

		@Override
		public int compareTo(Monster o) {
			int res = this.name.compareTo(o.name);
			return res;
		}
		
	}
	static int N, M;
	static List<Monster> dicName = new ArrayList<Monster>();
	static List<String> dicIdx = new ArrayList<String>();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken())+1;
		M = Integer.parseInt(token.nextToken());
		
		dicName.add(new Monster(0, "null"));
		dicIdx.add("empty");
		String name;
		for (int i = 1; i < N; i++) {
			name= in.readLine();
			dicIdx.add(name);
			dicName.add(new Monster(i, name));
		}
		
		Collections.sort(dicName);
		
		String target;
		for (int i = 0; i < M; i++) {
			target = in.readLine();
			if(target.charAt(0)<'A') {	// 숫자입력
				builder.append(dicIdx.get(Integer.parseInt(target)));
				builder.append("\n");
			}else {				
				builder.append(binarySearch(target));
				builder.append("\n");// 문자입력
			}
		}
		System.out.println(builder);
	}
	
	private static int binarySearch(String target) {
		int start=0, end=N, now=end/2, compare=0;
		String monster;
		
		while(true) {
			monster = dicName.get(now).name;
			compare = monster.compareTo(target);
			if(compare == 0) {	// 찾음
				return dicName.get(now).idx;
			}else if(compare < 0) {	// 작다
				start = now;
			}else {
				end = now;
			}
			now = (start+end)/2;
			
			if(start>end) return -1;
		}
	}
}