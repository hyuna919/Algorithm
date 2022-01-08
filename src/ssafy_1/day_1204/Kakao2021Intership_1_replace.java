package day_1204;

// 2021 카카오 채용연계형 인턴십
// 숫자 문자열과 영단어
// replaceAll은 정규표현식을 이용할 수 있다.
// 자매품 replace, replaceFirst


public class Kakao2021Intership_1_replace {
	public static void main(String[] args) {
		String s = "one4seveneight";

        String[] alphabets = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        
        for (int i = 0; i < 10; i++) {
			s = s.replaceAll(alphabets[i], Integer.toString(i));
		}
        
        System.out.println(s);
        
        
        /* replace, replaceFirsr 테스트*/
        String replaceTest = "우리의 리플레이스 리플레이스테스트";
        System.out.println(replaceTest.replace("리플레이스","replace"));
//      우리의 replace replace테스트
        System.out.println(replaceTest.replaceFirst("리플레이스","replaceFirst"));
//      우리의 replaceFirst 리플레이스테스트
	}
}
