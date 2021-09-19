package daily;
import java.util.Arrays;

public class NextPermutation {
	static int N=5-1, tmp;
	static int[] input;
	
	
	 public static void main(String[] args) {

		 input = new int[] {1,2,3,4,5};
		 
		 do {
			 System.out.println(Arrays.toString(input));
		 }while(np());
	 
}


	private static boolean np() {
//		int i =N;
//		while(i>0 && input[i-1] >= input[i]) i--;
//		if(i==0) return false;
//		
//		int j =N;
//		while(input[i-1] >= input[j]) j--;
//		swap(i-1,j);
//		
//		int k =N;
//		while(i<k) {
//			 swap(i++,k--);
//		}
//		return true;
		int i = N;
		while(i>0 && input[i-1] >=input[i]) i--;
		if(i==0) return false;
		
		int j = N;
		while(input[i-1]>=input[j])j--;
		swap(i-1,j);
		
		int k = N;
		while(i<k)	swap(i++,k--);
		return true;
	}


	private static void swap(int i, int j) {
		tmp = input[i];
		input[i] = input[j];
		input[j] = tmp;
		
	}
}
