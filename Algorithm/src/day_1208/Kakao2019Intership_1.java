package day_1208;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 2019 카카오 인턴십
// 크레인 인형뽑기 게임


public class Kakao2019Intership_1 {
	
	public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 2, 1, 4};
		int res = solution(board, moves);
		System.out.println(res);
	}
	
	public static int solution(int[][] board, int[] moves) {
		int removed = 0;	// 제거된 수
		
		Stack<Integer> stack = new Stack<Integer>();
		
        int N = board.length;
        int[] tops = new int[N];
        
        // 각 열의 top 위치 기록
        // 열별로 가장 윗 행부터 보면서 0이 아닌경우 tops에 기록하고 다음 열로 이동
        for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if(board[i][j] != 0) {
					tops[j] = i;
					break;
				}
			}
		}
        
        // moves따라하기
        int i, j, now;
        for (int n = 0, end = moves.length; n < end; n++) {
        	j = moves[n]-1;
			i = tops[j]++;	// top을 i에 넣자마자 아래칸으로 바꾸기
			
			if(i>=N) continue;
			
			now = board[i][j];
			board[i][j] = 0;
			
			if(!stack.isEmpty() && stack.peek() == now) {
				stack.pop();
				removed += 2;
			}else {
				stack.push(now);
			}
		}
        
        return removed;
    }
}
