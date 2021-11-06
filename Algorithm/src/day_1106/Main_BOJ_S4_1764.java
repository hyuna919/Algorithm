package day_1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 듣보잡
// 개쩌는 함수를 찾고 나의 성공시대 시작왰다
// Collections.binarySearch(List, key); return int
// 여태까지 이진탐색 손으로 짜던 나 반성해(코테에선 못 쓸수도 있긴하지만...)

public class Main_BOJ_S4_1764 {
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		StringBuilder builder = new StringBuilder();
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		// 듣도 못한 사람 명단만들기
		List<String> listen = new ArrayList<String>();
		for (int i = 0; i < N; i++) {
			listen.add(in.readLine());
		}
		
		Collections.sort(listen);
		
		// 보도 못한 사람 입력받으면서 듣도 못한 사람 명단에 있는지 확인
		List<String> offList = new ArrayList<String>();
		String see;
		for (int i = 0; i < M; i++) {
			see = in.readLine();
			int flag = Collections.binarySearch(listen, see);
			if(Collections.binarySearch(listen, see) > -1) {
				offList.add(see);
			}
		}

		Collections.sort(offList);
		builder.append(offList.size()+"\n");
		for (String string : offList) {
			builder.append(string + "\n");
		}
		System.out.println(builder.toString());
	}
}
