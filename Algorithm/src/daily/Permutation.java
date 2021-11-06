package daily;

import java.util.Arrays;

public class Permutation {
	static int n=8, r=8, cnt;
	static int [] inputs , nums;
	public static void main(String[] args) {
		nums = new int[r];
		inputs = new int[] {1,2,3,4,5,6,7,8};
		
		perm(0,0);
		System.out.println("케 "+cnt);
		
	}
	
	public static void perm(int now, int flag){
		if(now==8) {
			cnt++;
			return;
		}
		
		for (int i = 1; i < 9; i++) {
			if((flag&1<<i)==0) {
				if(now>0) System.out.println(nums[now-1]);
				nums[now] = i;
				perm(now+1, flag|1<<i);
			}
		}
	}
//	
//	public static void perm(int now, int flag){
//		if(now==r) {
//			cnt++;
//			System.out.println(Arrays.toString(nums));
//			return;
//		}
//		
//		for (int i = 0; i < n; i++) {
//			if((flag&1<<i)==0) {
//				nums[now] = i;
//				perm(now+1, flag|1<<i);
//			}
//		}
//	}
	

}
