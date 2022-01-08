class Solution {
    public String solution(int n) {
        StringBuilder builder = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        
        while(n>0){
            if(n%3 == 0) {
                builder.append(4);
                n /= 3;
                n--;
            }else{
                builder.append(Integer.toString(n%3));
                n /= 3;  
            } 
        }
        
        char[] arr = builder.toString().toCharArray();
        int length = arr.length;
        
        
        for(int i = length-1; i>=0; i--){
            if(arr[i]=='3') answer.append('4');
            else answer.append(arr[i]);
        }
        
        return answer.toString();
    }
}