package day_1207;


// 2020 카카오 인턴십
// 키패드 누르기


public class Kakao2020Intership_1 {
	static class Position {
		int x,y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static String solution(int[] numbers, String hand) {
		StringBuilder builder = new StringBuilder();
		
        int length = numbers.length;
        int leftPre = 10, rigthPre = 12;
        
        String leftKey = "147";
        String rightKey = "369";        
        
        Position nowPos, leftPos, rightPos;
        
        int now;
        String nowString;
        for (int i = 0; i < length; i++) {
        	now = numbers[i];
        	nowString = Integer.toString(now);
        	if(leftKey.contains(nowString)) {
        		builder.append("L");
        		leftPre = now;
        	}
        	else if(rightKey.contains(nowString)) {
        		builder.append("R");
        		rigthPre = now;
        	}
        	else {
        		// 맨해튼 거리 계산 위해 좌표화
        		// 누를 숫자
        		if(now==0) nowPos = new Position(3, 1);
        		else nowPos = new Position((now-1)/3, (now-1)%3);
        		// 왼손
        		if (leftPre == 0) leftPos = new Position(3, 1);
				else leftPos =new Position((leftPre-1)/3, (leftPre-1)%3);
        		// 오른손
        		if (rigthPre == 0) rightPos = new Position(3, 1);
				else rightPos = new Position((rigthPre-1)/3, (rigthPre-1)%3);
        		
        		// 가까운것 비교
        		int leftGap = Math.abs(nowPos.x - leftPos.x) + Math.abs(nowPos.y - leftPos.y);
        		int rightGap =  Math.abs(nowPos.x - rightPos.x) + Math.abs(nowPos.y - rightPos.y);
        		
        		if(leftGap > rightGap) {
        			rigthPre = now;
            		builder.append("R");
        		} else if(leftGap < rightGap) {
        			leftPre = now;
            		builder.append("L");
            	} else {
            		if(hand.equals("right")) {
            			rigthPre = now;
                		builder.append("R");
            		}else {
            			leftPre = now;
                		builder.append("L");
            		}
            	}
        	}
		}
        return builder.toString();
    }

	
	public static void main(String[] args) {
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand ="right";
		String res = solution(numbers, hand);
		System.out.println(res);
	}
}
