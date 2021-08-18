package day_0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_이진 {
	static int N, Q;
	static int[] input;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		StringTokenizer token = new StringTokenizer(in.readLine());
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(token.nextToken());
		}
		
		Q = Integer.parseInt(in.readLine());

		
		long s = System.currentTimeMillis();
		
		token = new StringTokenizer(in.readLine());
		in.close();
		StringBuilder builder = new StringBuilder();
		
		
		int target=Integer.MIN_VALUE, newTarget, start, end,mid = 0, midVal;
		for (int i = 0; i < Q; i++) {
			newTarget = Integer.parseInt(token.nextToken());
			if(target <= newTarget) {
				start = mid;
				end = N-1;
				
			}else {
				start = 0;
				end = mid;
			}
			
			target = newTarget;
			if(target< input[0] || target > input[N-1]) continue;
			while(true) {
				if(start>end) {
					mid = 0;
					builder.append(-1 + " ");
					break;
				}
				mid = (start+end)/2;
				midVal = input[mid];
				
				if(midVal == target) {
					builder.append(mid + " ");
					break;
				}
				else if(midVal < target) start = mid+1;
				else if(midVal > target) end = mid-1;
			}
		}
		
		long e = System.currentTimeMillis();
		System.out.println(builder);
		System.out.println(e-s);
		
	}

}
