package day_0928;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 
 * 숫자놀이
 * 
 */
public class Main_BOJ_S4_1755 {
	// 정렬 위해 커스텀 클래스를 만들어 숫자들을 관리한다.
	static class Number implements Comparable<Number>{	// 정렬위해 Comparable을 이용한다.
		int number;	// 정렬대상인 숫자
		int priority1;	// 정렬순위
		int priority2;	// 정렬순위
		public Number(int number, int priority1, int priority2) {	// Number 클래스 생성자
			super();					// 부모클래스 생성자 호출
			this.number = number;		// 초기화
			this.priority1 = priority1;	// 초기화
			this.priority2 = priority2;	// 초기화
		}
		@Override
		public int compareTo(Number o) {		// 정렬기준을 정의하는 함수
			int res = this.priority1 - o.priority1;	// priority를 비교한다.
			if(res==0) res = this.priority2 - o.priority2;
			return res;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	// 줄 단위로 입력받기 위함
		StringTokenizer token = new StringTokenizer(in.readLine());	// 한 줄에 입력되는 M,N을 받기 위해 StringTokenizer사용
		StringBuilder builder = new StringBuilder();				// 한번에 출력하기위해 출력할 문장을 저장할 객체
		int M = Integer.parseInt(token.nextToken());	// 입력M
		int N = Integer.parseInt(token.nextToken());	// 입력N
		
		int[] priorityArr = new int[] {9,4,8,7,2,1,6,5,0,3}; 	// 영어 철자에 따른 각 순자의 우선순위
		
		ArrayList<Number> list = new ArrayList<Number>();		// 정렬해야하는 M이상 N이하 숫자들을 담는 ArrayList
		for (int i = M, end = N+1; i < end; i++) {				//  M이상 N이하
			if(i<10) list.add(new Number(i,priorityArr[i],-1));	// 숫자가 10 미만이면 우선순위배열에서 그대로 검색해 사용한다.
			else list.add(new Number(i,priorityArr[i/10], priorityArr[i%10]));	// 숫자가 10이상이면 10의 자리와 1의 자리 숫자의 우선순위를 따로 검색하고 합하여 저장한다.
		}
		
		Collections.sort(list); 		// 정렬한다.
		
		for (int i = 0, end = list.size(); i < end; i++) {	// 출력을 위해 정렬된 리스트를 순회한다.
			builder.append(list.get(i).number+" ");			// builder에 숫자를 추가한다.
			if(i%10==9)builder.append("\n");				// 10번째 숫자마다 개행문자를 추가한다.
		}
		
		System.out.println(builder);	// 출력한다.
	}

}