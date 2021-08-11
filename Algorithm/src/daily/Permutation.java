package daily;

public class Permutation {
	static int n=3, r=3;
	static int [] inputs = {1,2,3}, numbers = new int[r];
	public static void main(String[] args) {
		
		
		
	}
	
	public void permutation(int cnt, int flag) {
		if(cnt ==0) {
			return;
		}
		
		for(int i = 1 ;i<=n;i++) {
			if((flag & 1<<i)==0) {
				numbers[cnt] = i;
				permutation(cnt+1, (flag|1<<i));
			}
		}
	}
}
