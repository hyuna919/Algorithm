import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		String tmp;
		for (int i = 0; i < N; i++) {
			tmp = br.readLine();
			if(map.containsKey(tmp)) {
				map.put(tmp, map.get(tmp)+1);
			}else {
				map.put(tmp,1);
			}
		}
		
		N--;
		
		int value;
		for (int i = 0; i < N; i++) {
			tmp = br.readLine();
			value = map.get(tmp) - 1;
			if(value == 0)map.remove(tmp);
			else map.put(tmp, value);
		}
		
		Entry<String, Integer> entry = null;
		Set<Entry<String, Integer>> set = map.entrySet();
		
		for (Entry<String, Integer> e : set) {
			System.out.println(e.getKey());
		}
		
	}
}
