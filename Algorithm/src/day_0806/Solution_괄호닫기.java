package day_0806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_괄호닫기 {
	// ( 40 ) 41
	// { 123 } 125
	// [ 91 ] 93
	// < 60 > 62
	
	
	
	public static void main(String[] args) throws IOException {
		int T = 10, size;
		char [] datas;
		char tmp;
		
		char [] open = {'(','{','[','<'};
		char [] close = {')','}',']','>'};
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack;
		
		
		testCase:for (int t = 1; t < T+1; t++) {

			size = Integer.parseInt(in.readLine());
			datas = in.readLine().toCharArray();
			
			stack = new Stack<>();
			inCase:for (int i = 0; i < size; i++) {
				tmp = datas[i];
				
				//여는 괄호
				for (int j = 0; j < 4; j++) {
					if(tmp==open[j]) {
						stack.push(tmp);
						continue inCase;
					}
				}
				
				// 닫는 괄호
				if(stack.isEmpty()) {							// 닫는 괄호인데 스택이 비었으면
					System.out.printf("#%d 0%n",t);
					continue testCase;
				}else {							// 닫는 괄호고 스택에 내용물도 있으면
					for (int j = 0; j < 4; j++) {
						if((tmp==close[j]) && (stack.peek()==open[j])) {	//짝이 맞으면
							stack.pop();
							continue inCase;
						}
					}
					System.out.printf("#%d 0%n",t);
					continue testCase;
					
				
				}
			}
			System.out.printf("#%d 1%n",t);
//			continue testCase;

		}
	}
}
