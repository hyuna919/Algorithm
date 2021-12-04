package day_1204;

// 2021 카카오 채용연계형 인턴십
// 숫자 문자열과 영단어


public class Kakao2021Intership_1 {
	public static void main(String[] args) {
		String s = "one4seveneight";
		StringBuilder builder = new StringBuilder();
		
		int length = s.length();
		for (int i = 0; i < length; i++) {
			char key = s.charAt(i);
			if(key>'A') {
				switch (key) {
				case 'z':
					builder.append("0");
					i += 3;
					break;
				case 'o':
					builder.append("1");
					i += 2;
					break;
				case 't':
					if(s.charAt(i+1)=='w') {
						builder.append("2");
						i += 2;
					}else {
						builder.append("3");
						i += 4;
					}
					break;
				case 'f':
					if(s.charAt(i+1)=='o') {
						builder.append("4");
						i += 3;
					}else {
						builder.append("5");
						i += 3;
					}
					break;
				case 's':
					if(s.charAt(i+1)=='i') {
						builder.append("6");
						i += 2;
					}else {
						builder.append("7");
						i += 4;
					}
					break;
				case 'e':
					builder.append("8");
					i += 4;
					break;
				default:
					builder.append("9");
					i += 3;
					break;
				}
			}else {
				builder.append(s.charAt(i));
			}
		}
		System.out.println(builder.toString());		
	}
}
