package day_0813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_1062 {
	static int N, K, max, flag;
	static String[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());
		
		list = new String[N];
		
		// K가 5미만이면 읽을 수 있는 글자 없음 -> 출력 + 종료
		if(K<5) {
			System.out.println(0);
			return;
		}
		// K가 26이면 전부 읽을 수 있음 -> 출력 + 종료
		if(K==26) {
			System.out.println(N);
			return;
		}
		
		// 입력 중복 확인 위한 플래그
		int flag = 0;
		flag = flag|1<<('a'-'a');
		flag = flag|1<<('n'-'a');
		flag = flag|1<<('t'-'a');
		flag = flag|1<<('i'-'a');
		flag = flag|1<<('c'-'a');
		
		// 입력 처리, 이미 나온건 flag에 체크하면서 추가한다.
		for(int i = 0; i<N ; i++) {
			StringBuilder builder = new StringBuilder();
			String tmpList = in.readLine();
			int length = tmpList.length();
			
			for (int j = 4 ; j < length-4; j++) {
				// 선되어ㅣㅇㅆ으면 패스
				char tmp = tmpList.charAt(j);
				if((flag & 1<<(tmp-'a')) !=0) continue;
				else {
					flag = flag|1<<(tmp-'a');
					builder.append(String.valueOf(tmp));
				}
			}
			list[i] = builder.toString();
		}	
		
		comb(26-5, K-5);
	}

	private static void comb(int n, int r) {
		// 기저조건
		if(r==0) {
			check(list[]);
		}
		
	}

	private static void check(String word) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if((flag & word.charAt(i))==word.charAt(i)) {
				cnt++;
			}
		}
		max = Math.max(max, cnt);
		
	}
	
	
	
	
}
