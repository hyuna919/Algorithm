// 10진법->2진법으로 바꿀 때 2로 나눠서 바궜는데 Integer 내장함수 쓰자

int N = 10;
String binaryString = Integer.toBinaryString(i); // 2진수
String octalString = Integer.toOctalString(i);   // 8진수
String hexString = Integer.toHexString(i);       // 16진수
