import java.util.*;

/*
2022 KAKAO BLIND RECRUITMENT
*/


class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Boolean> userList = new HashMap<>();
        Map<String, ArrayList<String>> reportList = new HashMap<>();
        Map<String, Integer> cntList = new HashMap<>();
        
        // 목록생성
        for(int i = 0, end=id_list.length; i<end; i++){
            userList.put(id_list[i],false);
            reportList.put(id_list[i],new ArrayList<String>());
            cntList.put(id_list[i],0);
        }
        
        // 신고 누적
        StringTokenizer token;
        int cntCnt;
        String who, whom;
        for(int i = 0, end=report.length; i<end; i++){
            token = new StringTokenizer(report[i]);
            who = token.nextToken();
            whom = token.nextToken();
            
            // 신고 경력 확인 후 추가
            if(reportList.get(who).contains(whom)) continue;
            reportList.get(who).add(whom);
            
            cntCnt = cntList.get(whom)+1;
            if(cntCnt>=k) userList.put(whom, true);
            cntList.put(whom, cntCnt);
        }
        
        // 신고 결과 답안으로
        int[] answer = new int[id_list.length];
        ArrayList<String> list;
        for(int i = 0, end=id_list.length; i<end; i++){
            int cnt = 0;
            list = reportList.get(id_list[i]);
            for(String target:list){
                if(userList.get(target)) cnt++;
            }
            
            answer[i] = cnt;
        }
        
        return answer;
    }
}
