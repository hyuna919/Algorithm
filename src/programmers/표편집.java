package programmers;

import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/81303
// 표 목록을 boolean[]으로 할 지, ArrayList<Integer>로 할 지 고민했는데 많은 행이 삭제된 상황에서 여러칸을 이동하는 문제 때문에 ArrayList<Integer>를 선택.
// ArrayList의 추가시 시간복잡도가 마음에 걸리긴 했는데 역시 이것 때문에 효율성에서 걸렸다.
// 자바를 오랜만에 하니 LinkedList의 존재를 완전히 잊고 있었다. 다시 시도해봐야겠다.

public class 표편집 {

	public static void main(String[] args) {
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		String res = solution(n,k,cmd);
		System.out.println(res);
	}
	

    public static String solution(int n, int k, String[] cmd) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Stack<int[]> removed = new Stack<>();  // 0: 위치 1: 행번호
        
        // list에 행 밀어넣기
        for(int i=0; i<n; i++){
            list.add(i);
        }
        
        // k그대로 써도 되겠지만 의미 문제로 pointer로 사용
        int pointer = k;
        
        // 명령어 처리
        int length = cmd.length;
        int distance = 0, value;
        int[] tmp = new int[2];
        char command;
        StringTokenizer token;
        for(int t=0; t<length; t++){
        	token = new StringTokenizer(cmd[t]);
            command = token.nextToken().charAt(0);
            switch(command){
                case 'U':
                    distance = Integer.parseInt(token.nextToken());
                    pointer -= distance;    // 표 범위를 벗어나는 입력은 주어지지 않음
                    break;
                case 'D':
                    distance = Integer.parseInt(token.nextToken());
                    pointer += distance;
                    break;
                case 'C':
                    // 삭제하고 아래행 가르킨다->arraylist라서 안움직여도 된다
                    removed.push(new int[]{pointer, list.get(pointer)});
                    list.remove(pointer);
                    // 가장 마지막 행 삭제했다면 현재 마지막행을 가르킨다.
                    if(pointer>=list.size()) pointer = list.size()-1;
                    break;
                case 'Z':
                    value = list.get(pointer);
                    tmp = removed.pop();
                    list.add(tmp[0], tmp[1]);
                    // 포인터 앞쪽(위)에 삽입되어서 한칸씩 밀렸으면 포인터를 한칸 아래로
                    if(value != list.get(pointer)) pointer++;
                    break;
            }
            // System.out.println(list.toString() + " " + pointer);
        }
        
        // 결과를 문자열로
        StringBuffer result = new StringBuffer();
        int now = 0;
        length = list.size();
        for(int i=0; i<n; i++){
            if(now<length && list.get(now)==i){
                result.append("O");
                now++;
            }else{
                result.append("X");
            }
        }
        
        
        return result.toString();
    }

}
