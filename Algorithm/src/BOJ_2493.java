

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2493 {
	public static void main(String[] args) throws IOException {
		int N;
		int [] tower;
		int [] res;
		
		// 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		String [] tmp = in.readLine().split(" ");
		
		tower = new int[N+1];	// 탑 번호가 1부터 시작함
		res = new int[N+1];
		for (int i = 0; i < N; i++) {
			tower[i+1] = Integer.parseInt(tmp[i]);
		}
		
		// 스택
		// 스택에 각 타워의 인덱스를 넣을 것
		Stack<Integer> stack = new Stack<>();
		
		while(N>0) {
			while((!stack.isEmpty())&&(tower[stack.peek()] <= tower[N])) {
				res[stack.pop()] =N ;
			}
			stack.push(N);
			N--;
		}
		
		for (int i = 1, end = res.length; i < end; i++) {
			System.out.print(res[i]+" ");
		}
	}
}
