package daily;

public class Combination {

	static int n ,r, numbers[], inputs[];
	public static void main(String[] args) {
			n = 5;
			r = 3;
			inputs = new int[] {1,2,3,4,5};
			numbers = new int[r];

			comb(n,r);
	}
	
	private static void comb(int n, int r) {
		if(r==0) {
			return;
		}
		
		if(n<r) return;
		
		numbers[r-1] = inputs[n-1];
		comb(n-1,r-1);
		comb(n-1,r);
	}

}
