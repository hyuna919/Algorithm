import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* map을 거의 안쓰다보니 연습하려고 map 푸는 중
* 1- 그냥 순열로 풀었다. map적용 방안조차 안떠오름
* 2- 우선 문자열로 쓰는거 
*/

public class Main {
	static int N, K, cnt;
	static int[] list;
	static boolean[] isChecked;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		cnt = 0;
		
		
		int len=0;
		switch (K) {
		case 2:
			len = 99_99;
			break;
		case 3:
			len = 99_99_99;
			break;
		case 4:
			len = 9999_9999;
			break;
		}
		isChecked = new boolean[len];
		list = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		// 순열
		perm(0, 0, "");
		
		
		System.out.println(cnt);
	}
	private static void perm(int flag, int now, int num) {
		if(now==K) {
			if(!isChecked[num]) {
				isChecked[num] = true;
				cnt++;
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if((flag&1<<i)!=0) continue;
			if(list[i]>9) perm(flag|1<<i, now+1, (num*100)+list[i]);
			else perm(flag|1<<i, now+1, (num*10)+list[i]);
		}
		
	}
}
