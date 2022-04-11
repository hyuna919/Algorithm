import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/*
* map을 거의 안쓰다보니 연습하려고 map 푸는 중
* 1- 그냥 순열로 풀었다. map적용 방안조차 안떠오름
* 2- 우선 문자열로 쓰는거 
* 3- set으로 바꿨다. 백준 기록 보면 알겠지만 메모리, 시간 모두 엄청 단축됐다. 깨달음을 얻음
*/

public class Main {
	static int N, K, cnt;
	static int[] list;
	static Set<Integer> isChecked = new HashSet<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		list = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		// 순열
		perm(0, 0, 0);
		System.out.println(isChecked.size());
	}
	
	private static void perm(int flag, int now, int num) {
		if(now==K) {
			isChecked.add(num);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if((flag&1<<i)!=0) continue;
			if(list[i]>9) perm(flag|1<<i, now+1, (num*100)+list[i]);
			else perm(flag|1<<i, now+1, (num*10)+list[i]);
		}
		
	}
}
