import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ,;");

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        String defaultType = token.nextToken();
        while (token.hasMoreTokens()){
            String name = token.nextToken();
            char[] arr = name.toCharArray();

            StringBuilder type = new StringBuilder();
            String value = "";
            type.append(defaultType);
            int flag = 0;
            for (int i = arr.length-1; i >= 0 ; i--) {
                switch (arr[i]) {
                    case ']':
                        type.append("[]");
                        i--;
                        break;
                    case '&':
                        type.append("&");
                        break;
                    case '*':
                        type.append("*");
                        break;
                    default:
                        flag = 1;
                        break;
                }

                // 중간종료
                if (flag==1) {
                    value = name.substring(0, i+1);
                    // 추가
                    keys.add(type.toString());
                    values.add(value);
                    break;
                }
            }

        }


        for (int i = 0; i < keys.size(); i++) {
            System.out.println(keys.get(i) + " " + values.get(i) + ";");
        }
    }
}


