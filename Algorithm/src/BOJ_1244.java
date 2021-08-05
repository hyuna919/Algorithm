

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1244 {
	// 테스트 케이스가 1개인 문제
	// 스위치 수 받기
	// 스위치 상태
	// 학생수
		// 남자라면 - 배수 반전
		// 여자라면 - 좌우대칭 반전
			// n-1과 n+1이 같은가 반복확인 ->재귀
	// 스위치 상태 출력
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 테스트 케이스가 1개인 문제
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// 스위치 수 받기
		int size = Integer.parseInt(in.readLine());
		// 스위치 상태 - 토큰으로 구분자 처리해보기
		String [] switchs = new String[size];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < size; i++) {
			switchs[i] = st.nextToken();
		}
		
		// 학생수
		int students = Integer.parseInt(in.readLine());
		for (int i = 0; i < students; i++) {
			// split으로 구분자 처리해보기
			String [] student = in.readLine().split(" ");
			
			if(student[0].equals("1")) {		// 남자라면 - 배수 반전
				for (int j = 1; j <= size; j++) { 	// 배수 계산해야해서 +1(문제에서 스위치는 1번부터)
					if(j % Integer.parseInt(student[1]) == 0) {
						switchs[j-1] = (switchs[j-1].equals("0"))?"1":"0";
					}
				}
			}else if(student[0].equals("2")) {					// 여자라면 - 좌우대칭 반전
				// n-1과 n+1이 같은가 반복확인 ->재귀
				int idx = Integer.parseInt(student[1])-1;
				if(idx>0 && idx<size-1) {
					girl(switchs, idx, size,1);
				}
				switchs[idx] = (switchs[idx].equals("0"))?"1":"0";	// 기준 위치 반전
			}else {
				break;
			}
		}
			
		// 스위치 상태 출력
		//한 줄에 20개씩 출력
		for(int i=0; i<size; i++) {
			System.out.print(switchs[i] + " ");
			if((i+1) % 20 == 0)
				System.out.println();
		}
	}
	
	// 위치 받아서 좌우로 얼마나 확장 가능한지 리턴
	static void girl(String[] switchs, int i, int size,int cnt) {
		if(switchs[i-cnt].equals(switchs[i+cnt])) {
			switchs[i-cnt] = (switchs[i-cnt].equals("0"))?"1":"0";
			switchs[i+cnt] = (switchs[i+cnt].equals("0"))?"1":"0";
			cnt++;
			if(i-cnt>=0 && i+cnt<size) girl(switchs, i, size, cnt);
			else return;
		}
		return ;
	}
}
