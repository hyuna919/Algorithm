package day_1213;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 2019 카카오 블라인드
// 실패율


public class Kakao2019Blind_실패율{
	static class Stage implements Comparable<Stage>{
		int stage;
		double rate;

		public Stage(int stage, double rate) {
			super();
			this.stage = stage;
			this.rate = rate;
		}

		@Override
		public int compareTo(Stage o) {
			if(rate < o.rate) return 1;
			else if(rate > o.rate) return -1;
			else {
				if(stage > o.stage) return 1;
				else return -1;
			}
		}
	}
	
	public static void main(String[] args) {
		int N = 5;
		int[] stages = {2,1,2,6,2,4,3,3};
		int[] res = solution(N, stages);
		System.out.println(Arrays.toString(res));
	}
	
	public static int[] solution(int N, int[] stages) {
		List<Stage> list = new ArrayList<Stage>();
		int[] stage_total = new int[N +1];
		int[] challenge = new int[N + 2];
		
		for (int i = 0, end = stages.length; i < end; i++) {
			challenge[stages[i]]++;
		}
		
		stage_total[N] = challenge[N] + challenge[N+1];
		for (int i = N-1; i >= 1; i--) {
			stage_total[i] = challenge[i] + stage_total[i+1];
		}
		
		for (int i = 1, end=N+1; i < end; i++) {
			if(stage_total[i] == 0) {
				list.add(new Stage(i, 0));
				continue;
			}
			double rate = (double)challenge[i] / stage_total[i];
			list.add(new Stage(i, rate));
		}
		
		Collections.sort(list);
		
		int[] res = new int[N];
		for (int i = 0, end = list.size(); i < end; i++) {
			res[i] = list.get(i).stage;
		}
		
		return res;
    }
}
