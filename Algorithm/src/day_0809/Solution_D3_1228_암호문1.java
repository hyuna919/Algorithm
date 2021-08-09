package day_0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Solution_D3_1228_암호문1 {
	static LinkedList<String> crypto;
	static int T=10, len, x, y;	// len은 암호문 길이랑 명령어 수 두가지로 활용
	static String [] tmp;
	static String s;
	static int idx;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// 테케 10개
		for (int t = 1; t < 10+1; t++) {
			crypto = new LinkedList<String>();
			// 원본 암호문 길이 받기
			len = Integer.parseInt(in.readLine());
			// 암호문 받아서 링크드 리스트에 넣기
			tmp = in.readLine().split(" ");
			for (int i = 0, end=(len>10)?10:len; i < end; i++) {
				crypto.addLast(tmp[i]);
			}
			// 명령어의 수 받기
			len = Integer.parseInt(in.readLine());
			// 명령어 받기
			tmp = in.readLine().split(" ");
			// 다음 I찾아서 
			idx=0;
			while(len>0) {
				if(tmp[idx].equals("I")) {
					len--;
					x = Integer.parseInt(tmp[++idx]);
					if(x>10)continue;
					y = Integer.parseInt(tmp[++idx]);
					
					for(int i=0, end=((10-x)>y)?y:(10-x); i<end; i++){
						crypto.add(x++, tmp[++idx]);
					}
				}else {
					idx++;
				}
			}
			

			System.out.printf("#"+t+" ");
			for (int i = 0; i < 10; i++) {
				System.out.print(crypto.getFirst()+" ");
				crypto.removeFirst();
			}
			System.out.println();
			
			
			
		}
	}
}
