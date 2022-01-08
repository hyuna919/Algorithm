package day_1226;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// 4차원배열(조건 4개: 언어,직군,커리어,음식)에 점수 넣기

public class Programmers_순위검색 {
	static ArrayList<Integer>[][][][] list;

	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		int[] res = solution(info, query);
		System.out.println(res.toString());
	}
	
	public static int[] solution(String[] info, String[] query) {
		int[] res = new int[query.length];
		
		// 언어에 따라 나눠서
		StringTokenizer token;
		String tmp;
		list = new ArrayList[4][3][3][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				for (int j2 = 0; j2 < 3; j2++) {
					for (int k = 0; k < 3; k++) {
						list[i][j][j2][k] = new ArrayList<Integer>();
					}
				}
			}
		}
		int language, job, career, food,score;
		for (int i = 0, end=info.length; i < end; i++) {
			token = new StringTokenizer(info[i]);
			tmp = token.nextToken();
			language = (tmp.equals("cpp"))?0:(tmp.equals("java"))?1:2;
			job = (token.nextToken().equals("backend"))?0:1;
			career = (token.nextToken().equals("junior"))?0:1;
			food = (token.nextToken().equals("chicken"))?0:1;		
			score = Integer.parseInt(token.nextToken());
			addAll(language, job,career,food,score);
		}

		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				for (int j2 = 0; j2 < 3; j2++) {
					for (int k = 0; k < 3; k++) {
						Collections.sort(list[i][j][j2][k]);
					}
				}
			}
		}
		
		
		// 조건 탐색
		int size, cnt;
		ArrayList<Integer> now;
		for (int i = 0, end=query.length; i < end; i++) {
			token = new StringTokenizer(query[i]);
			tmp = token.nextToken();
			language = (tmp.equals("cpp"))?0:(tmp.equals("java"))?1:(tmp.equals("python"))?2:3;
			token.nextToken();
			tmp = token.nextToken();
			job = (tmp.equals("backend"))?0:(tmp.equals("frontend"))?1:2;
			token.nextToken();
			tmp = token.nextToken();
			career = (tmp.equals("junior"))?0:(tmp.equals("senior"))?1:2;
			token.nextToken();
			tmp = token.nextToken();
			food = (tmp.equals("chicken"))?0:(tmp.equals("pizza"))?1:2;
			score = Integer.parseInt(token.nextToken());

			now = list[language][job][career][food];

			if(now.isEmpty()) continue;
			size = now.size();
			cnt = binarySearch(now, size, score);
			res[i] = cnt;
		}
		return res;
	}

	private static void addAll(int language, int job, int career, int food, int score) {
		list[language][job][career][food].add(score);
		list[3][job][career][food].add(score);
		list[language][2][career][food].add(score);
		list[language][job][2][food].add(score);
		list[language][job][career][2].add(score);
		list[3][2][career][food].add(score);
		list[3][job][2][food].add(score);
		list[3][job][career][2].add(score);
		list[language][2][2][food].add(score);
		list[language][2][career][2].add(score);
		list[language][job][2][2].add(score);
		list[3][2][2][food].add(score);
		list[3][2][career][2].add(score);
		list[3][job][2][2].add(score);
		list[language][2][2][2].add(score);
		list[3][2][2][2].add(score);
		
		
	}

	private static int binarySearch(ArrayList<Integer> now, int size, int score) {
		int start = 0, end = size-1, mid;
		while(start<=end) {
			mid = (start+end)/2;
			if(now.get(mid) < score) start = mid+1;
			else end = mid-1;
		}
		return size-start;
	}
}
