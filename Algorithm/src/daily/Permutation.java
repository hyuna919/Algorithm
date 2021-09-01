package daily;

import java.util.Arrays;

public class Permutation {
	static int n=3, r=2, cnt;
	static int [] inputs , nums;
	public static void main(String[] args) {
		nums = new int[r];
		inputs = new int[] {1,2,3};
		
		perm(0,0);
		System.out.println("케 "+cnt);
		
	}
	
	public static void perm(int now, int flag){
		if(now==r) {
			cnt++;
			System.out.println(Arrays.toString(nums));
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if((flag&1<<i)==0) {
				nums[now] = i;
				perm(now+1, flag|1<<i);
			}
		}
	}
	

}
