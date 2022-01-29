package programmers;

import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/81303
// 표 목록을 boolean[]으로 할 지, ArrayList<Integer>로 할 지 고민했는데 많은 행이 삭제된 상황에서 여러칸을 이동하는 문제 때문에 ArrayList<Integer>를 선택.
// ArrayList의 추가시 시간복잡도가 마음에 걸리긴 했는데 역시 이것 때문에 효율성에서 걸렸다.
// 자바를 오랜만에 하니 LinkedList의 존재를 완전히 잊고 있었다. 다시 시도해봐야겠다.
// 오케이...java의 linkedList는 이중 연결 리스트인데 노드 단위로 움직일 방법이 없구나...? 이럴거면 대체 왜...

// 일요일에 꼭 푼다...
public class 표편집 {

	public static void main(String[] args) {
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		String res = solution(n,k,cmd);
		System.out.println(res);
	}
	

    public static String solution(int n, int k, String[] cmd) {Stack<Integer> removed = new Stack<>();  // 0: 위치 1: 행번호
        int pointer = k;
        
        // 명령어 처리
        int length = cmd.length;
        int distance = 0;
        int value, tmp;
        char command;
        StringTokenizer token;
        for(int t=0; t<length; t++){
        	token = new StringTokenizer(cmd[t]);
            command = token.nextToken().charAt(0);
            switch(command){
                case 'U':
                    distance = Integer.parseInt(token.nextToken());
                    pointer -= distance;
                    break;
                case 'D':
                    distance = Integer.parseInt(token.nextToken());
                    pointer += distance;
                    break;
                case 'C':
                    removed.push(pointer);
                    n--;
                    // 가장 마지막 행 삭제했다면 현재 마지막행을 가르킨다.
                    if(pointer>=n) pointer--;
                    break;
                case 'Z':
                    tmp = removed.pop();
                    // 포인터 앞쪽(위)에 삽입되어서 한칸씩 밀렸으면 포인터를 한칸 아래로
                    if(tmp <= pointer) pointer++;
                    n++;
                    break;
            }
        }
        
        // 결과를 문자열로
        StringBuilder result = new StringBuilder();
        int now = 0;
        for(int i=0; i<n; i++){
            result.append("O");
        }
        while(!removed.isEmpty()){
            result.insert(removed.pop().intValue(), "X");
        }
        
        return result.toString();
    }

}
