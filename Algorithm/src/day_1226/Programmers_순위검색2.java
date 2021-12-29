package day_1226;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 클래스를 만들기
// 모든 지원자 정보와 조건 정보를 저장해두고 
// 조건이 -이거나 지원자 정보와 맞는지 비교해서 수를 센다
// 효율성테스트 하나도 통과 못함

public class Programmers_순위검색2 {
	static List<Candidate> cpp, java, python;
	static class Candidate implements Comparable<Candidate>{
		String language, job, career, food;
		int score;
		public Candidate(String language, String job, String career, String food, int score) {
			super();
			this.language = language;
			this.job = job;
			this.career = career;
			this.food = food;
			this.score = score;
		}
		
		// 내림차순
		@Override
		public int compareTo(Candidate o) {
			return o.score - this.score;
		}
	}
	
	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		int[] res = solution(info, query);
		System.out.println(res.toString());
	}
	
	public static int[] solution(String[] info, String[] query) {
		cpp = new ArrayList<Candidate>();
		java = new ArrayList<Candidate>();
		python = new ArrayList<Candidate>();
		int[] res = new int[query.length];
		
		// 언어에 따라 나눠서
		StringTokenizer token;
		Candidate candidate;
		for (int i = 0, end=info.length; i < end; i++) {
			token = new StringTokenizer(info[i]);
			candidate = new Candidate(token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken(), Integer.parseInt(token.nextToken()));
			switch (candidate.language) {
			case "cpp":
				cpp.add(candidate);
				break;
			case "java":
				java.add(candidate);
				break;
			case "python":
				python.add(candidate);
				break;
			default:
				System.out.println("잘못된입력");
				break;
			}
		}
		
		// 성적순 정렬
		Collections.sort(cpp);
		Collections.sort(java);
		Collections.sort(python);
		
		// 고득점자부터 조건 탐색
		int cnt;
		Candidate condition;
		String language, job, career, food;
		int score;
		for (int i = 0, end = query.length; i < end; i++) {
			token = new StringTokenizer(query[i]);
			language = token.nextToken();
			token.nextToken();
			job = token.nextToken();
			token.nextToken();
			career = token.nextToken();
			token.nextToken();
			food = token.nextToken();
			score = Integer.parseInt(token.nextToken());
			condition = new Candidate(language, job, career, food, score);
			switch (condition.language) {
			case "cpp":
				cnt = findCpp(condition);
				break;
			case "java":
				cnt = findJava(condition);
				break;
			case "python":
				cnt = findPython(condition);
				break;
			default:
				cnt = findCpp(condition);
				cnt += findJava(condition);
				cnt += findPython(condition);
			}
			res[i] = cnt;
		}
		return res;
	}

	private static int findCpp(Candidate condition) {
		int cnt = 0;
		Candidate candidate;
		for (int i = 0, end = cpp.size(); i < end; i++) {
			candidate = cpp.get(i);
			// 점수가 조건 이하면 더이상 다른 지원자도 보지 않는다.
			if(candidate.score < condition.score) break;
			
			if(condition.job.equals("-") || condition.job.equals(candidate.job)) {
				if(condition.career.equals("-") || condition.career.equals(candidate.career)) {
					if(condition.food.equals("-") || condition.food.equals(candidate.food)) cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static int findJava(Candidate condition) {
		int cnt = 0;
		Candidate candidate;
		for (int i = 0, end = java.size(); i < end; i++) {
			candidate = java.get(i);
			// 점수가 조건 이하면 더이상 다른 지원자도 보지 않는다.
			if(candidate.score < condition.score) break;
			
			if(condition.job.equals("-") || condition.job.equals(candidate.job)) {
				if(condition.career.equals("-") || condition.career.equals(candidate.career)) {
					if(condition.food.equals("-") || condition.food.equals(candidate.food)) cnt++;
				}
			}
		}
		return cnt;
	}

	private static int findPython(Candidate condition) {
		int cnt = 0;
		Candidate candidate;
		for (int i = 0, end = python.size(); i < end; i++) {
			candidate = python.get(i);
			// 점수가 조건 이하면 더이상 다른 지원자도 보지 않는다.
			if(candidate.score < condition.score) break;
			
			if(condition.job.equals("-") || condition.job.equals(candidate.job)) {
				if(condition.career.equals("-") || condition.career.equals(candidate.career)) {
					if(condition.food.equals("-") || condition.food.equals(candidate.food)) cnt++;
				}
			}
		}
		return cnt;
	}
}
