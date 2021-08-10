package day_0810;

import java.util.Arrays;

public class Subset {
	static int n;
	static int [] inputs = {1,2,3,4,5}, numbers;
	public static void main(String[] args) {
		n = 5;
		numbers = new int[n];
		
		
		for (int i = 0, end = 1<<n; i < end; i++) {
			for (int j = 0; j < n; j++) {
				if((i&1<<j)!=0) {
					numbers[j] =1;
				}
			}
			System.out.println(Arrays.toString(numbers));
			Arrays.fill(numbers,0);
		}
		
		System.out.println();
		
	}

}
