import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes,Comparator.comparingInt(route->route[1]));
        
        int answer = 0;
        int camera = Integer.MIN_VALUE;
        
        for(int[] route : routes){
            if(camera < route[0]){
                camera = route[1];
                answer++;
            }
        }
        
        return answer;
    }
}