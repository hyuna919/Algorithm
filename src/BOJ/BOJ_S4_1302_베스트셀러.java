import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



/*
 * map을 거의 안쓰다보니 연습하려고 map 푸는 중 3
 * 엊그제는 set을 풀었고 오늘은 드디어 map이다.
 * entry란 무엇인지 알아야겠다.
 * 
*/

public class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		String name;
		int value;
		while(N-->0) {
			name = br.readLine();
			if(map.containsKey(name)) {
				value = map.get(name);
				map.put(name, value+1);
			}else {
				map.put(name, 1);
			}
		}

		
		Entry<String, Integer> max = null;
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> e : entrySet) {
			if(max == null || e.getValue() > max.getValue()) {
				max = e;
			}else if(e.getValue() == max.getValue()) {
				int res = e.getKey().compareTo(max.getKey());
				if(res<0) max = e;
			}
		}
		
		System.out.println(max.getKey());
	}
}
