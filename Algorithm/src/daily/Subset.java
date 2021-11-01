package daily;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Subset {
	static int n;
	static String[] subset;
	static String[] input;
	
	public static void main(String[] args) {
		n = 5;
		input = "abcde".split("");
		subset = new String[]{"-","-","-","-","-"};
		
		
		
		
		
		
		for (int i = 0, end=1<<n; i < end; i++) {
			for (int j = 0; j < n; j++) {
				if((i&1<<j)!=0) System.out.print(input[j]);
			}
			System.out.println();
		}
	
		
	}

//	private static void print(int[] subset) {
//		int k = 0;
//		System.out.println("[");
//		for (int s : subset) {
//			if(s!=0) {
//				System.out.println(input[k]+" ");
//			}
//			k++;
//		}
//		System.out.println("]");
//		
//		
//	}
	
	

}
