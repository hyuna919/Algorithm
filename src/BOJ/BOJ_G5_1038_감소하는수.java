import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	static ArrayList<Long> list;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		scan.close();
		list = new ArrayList<>();
		
		if(N <= 10) System.out.println(N);
		else if(N>1022) System.out.println(-1);
		else {
			for (int i = 0; i < 10; i++) {
				getSequantial(i, 0);
			}
			Collections.sort(list);
			System.out.println(list.get(N));
		}
		
	}

	private static void getSequantial(long num, int lv) {
		if(lv>10) return;
		
		list.add(num);
		for (int i = 0; i < num%10; i++) {
			getSequantial((num*10)+i, lv+1);
		}
	}
}
