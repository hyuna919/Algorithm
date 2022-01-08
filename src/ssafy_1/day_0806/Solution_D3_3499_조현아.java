package day_0806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D3_3499_조현아 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T, size, half;
		String [] cards, shuffled;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t < T+1; t++) {
			size = Integer.parseInt(in.readLine());
			cards = in.readLine().split(" ");
			
			half = (int) Math.ceil(size/2.0);
			shuffled = new String[size];
			for (int i = 0; i < half; i++) {
				shuffled[2*i] = cards[i];
				if((i+half)<size) shuffled[2*i+1] = cards[i+half];
			}
			
			System.out.printf("#%d ", t);
			for (int i = 0; i < size; i++) {
				System.out.print(shuffled[i]+" ");
			}
			System.out.println();
		}
	}
}