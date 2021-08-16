package daily;

import java.util.Arrays;

public class Subset {
	static int n;
	static int[] subset;
	static String[] inputs = "abcd".split("");
	
	public static void main(String[] args) {
		n = 5;
		subset = new int[n];
		
		for (int i = 0, end=1<<n ; i<end ; i++) {
			for (int j = 0; j < n; j++) {
				if((i&1<<j)!=0) {
					subset[j] = 1;
				}
			}
			print(subset);
			Arrays.fill(subset, 0);
		}
		
	}

	private static void print(int[] subset) {
		int k = 0;
		System.out.println("[");
		for (int s : subset) {
			if(s!=0) {
				System.out.println(inputs[k]+" ");
			}
			k++;
		}
		System.out.println("]");
		
		
	}
	
	

}
