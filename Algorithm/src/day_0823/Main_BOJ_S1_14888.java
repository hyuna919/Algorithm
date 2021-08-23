package day_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.omg.CORBA.INTERNAL;

public class Main_BOJ_S1_14888 {
	static int N, max=Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] A, oper, output;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		A = new int[N];
		oper = new int[N-1];
		output = new int[N-1];
		
		StringTokenizer token = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(token.nextToken());
		}
		
		token = new StringTokenizer(in.readLine());
		int tmp, tmp_oper=0, idx=0;
		for (int i = 0; i < 4; i++) {
			tmp = Integer.parseInt(token.nextToken());
			for (int j =0 ; j < tmp; j++) {
				oper[idx++] = tmp_oper;
			}
			tmp_oper++;
		}
		
		perm(0,0);
		System.out.println(max +"\n"+min);
	}
	
	public static void perm(int now, int flag) {
		if(now==N-1) {
			cal(output);
			return;
		}
		
		for (int i = 0; i < N-1; i++) {
			if((flag&1<<i)==0) {
				output[now] = oper[i];
				perm(now+1, flag|1<<i);
			}
		}
	}

	private static void cal(int[] output) {
		int res = A[0], idx=0;
		for (int i = 1; i < N; i++) {
			if(output[idx]==0) res += A[i];
			else if(output[idx]==1) res -= A[i];
			else if(output[idx]==2) res *= A[i];
			else if(output[idx]==3) {
				if(res<0) {
					res*=-1;
					res /= A[i];
					res *= -1;
				}else res /= A[i];
			}
			idx++;
		}
		
		max = Math.max(max,res);
		min = Math.min(min,res);
		
	}

}
