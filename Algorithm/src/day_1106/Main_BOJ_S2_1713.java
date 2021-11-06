package day_1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_S2_1713 {
	static int N, M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		int[] recommend = new int[101];
		
		token = new StringTokenizer(in.readLine());
		
		// 1. 학생들이 추천을 시작하기 전에 모든 사진틀은 비어있다.
		List<Integer> frames = new ArrayList<Integer>();
		
		// 추천 입력 받을떄마다 카운트
		int candidate, idx;
		for (int i = 0; i < M; i++) {
			candidate = Integer.parseInt(token.nextToken());
			// 2. 어떤 학생이 특정 학생을 추천하면, 추천받은 학생의 사진이 반드시 사진틀에 게시되어야 한다.
			
			// 4. 게시된 학생이 추천을 받은 경우에는 추천받은 횟수만 증가시킨다.
			idx = frames.indexOf(candidate);
			if(idx != -1) {
				recommend[candidate]++;
				continue;
			}
			
			// 게시되지 않은 학생이 추천 받았는데 
			int min, minIdx, minCandidate, now;
			if(frames.size()>=N) {	// 3. 현제 사진틀이 만석 				
				// 3.1. 추천 받은 횟수가 가장 적은 학생의 사진을 삭제하고, 그 자리에 새롭게 추천받은 학생의 사진을 게시
				minCandidate = frames.get(0);
				min = recommend[minCandidate];
				minIdx = 0;
				for (int j = 1; j < N; j++) {
					now = recommend[frames.get(j)];
					if(now<min) {	// 3.2. 두 명 이상일 경우에는 그러한 학생들 중 게시된 지 가장 오래된 사진을 삭제(앞에 있는게 오래된거)
						minCandidate = frames.get(j);
						min = now;
						minIdx = j;
					}
				}
				// 삭제
				frames.remove(minIdx);
				recommend[minCandidate] = 0;
				// 추가
				frames.add(candidate);
				recommend[candidate]++;
			}else {			// 만석 아님
				frames.add(candidate);
				recommend[candidate]++;
			}
		}
		
		Collections.sort(frames);
		
		StringBuilder builder = new StringBuilder();
		for (Integer integer : frames) {
			builder.append(integer+" ");
		}
		System.out.println(builder.toString());
	}
}
