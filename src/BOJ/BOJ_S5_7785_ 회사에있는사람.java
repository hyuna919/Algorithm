import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
* map을 거의 안쓰다보니 연습하려고 map 푸는 중 2
* 1- Hashset을 정렬하는 함수를 모르겠어서 검색했다.
*    List로 생성해버리면 된다.
*/

public class Main {
	static int N;
	static Set<String> set = new HashSet<String>();
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
		
		
		int size = set.size();
		set.iterator();
		List<String> list = new ArrayList(set);
		Collections.sort(list);
		StringBuilder builder =new StringBuilder();
		for (int i = size-1; i > -1; i--) {
			builder.append(list.get(i)+"\n");
		}
		System.out.println(builder.toString());
	}
}
