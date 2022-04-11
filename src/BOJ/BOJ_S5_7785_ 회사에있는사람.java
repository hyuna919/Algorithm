import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


/*
* map을 거의 안쓰다보니 연습하려고 map 푸는 중 2
* 1- Hashset을 정렬하는 함수를 모르겠어서 검색했다.
*    List로 생성해버리면 된다.
* 2- treeset은 정말 처음 써보는듯하다.
*    hashset보다 추가/삭제 시간 걸리지만 검색/정렬 유리
*    이문제에서는 hashset이랑 큰 차이는 안느껴진다
*/

public class Main {
	static int N;
	static TreeSet<String> set = new TreeSet<String>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token;
		
		N = Integer.parseInt(br.readLine());
		
		String name, state;
		for (int i = 0; i < N; i++) {
			token  = new StringTokenizer(br.readLine());
			name = token.nextToken();
			state = token.nextToken();
			if("enter".equals(state)) set.add(name);
			else set.remove(name);
		}
		
		
		StringBuilder builder =new StringBuilder();
		java.util.Iterator<String> itr = set.descendingIterator();
		while(itr.hasNext()) builder.append(itr.next()+"\n");

		System.out.println(builder.toString());
	}
}
