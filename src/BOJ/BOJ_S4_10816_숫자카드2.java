import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
	static int N, M;
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer token = new StringTokenizer(br.readLine());
		int num;
		for (int i = 0; i < N; i++) {
			num = Integer.parseInt(token.nextToken());
			if(map.containsKey(num)) map.put(num, map.get(num)+1);
			else map.put(num, 1);
		}

		M = Integer.parseInt(br.readLine());
		token = new StringTokenizer(br.readLine());
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < M; i++) {
			num = Integer.parseInt(token.nextToken());
			if(map.containsKey(num)) builder.append(map.get(num)+" ");
			else builder.append(0+" ");
		}
	
		System.out.println(builder.toString());
	}
}
