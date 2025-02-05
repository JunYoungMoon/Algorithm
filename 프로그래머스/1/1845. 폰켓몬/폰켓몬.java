import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int max = nums.length / 2;

        int answer = 0;

        if(set.size() > max){
            answer = max;
        }else{
            answer = set.size();
        }

        return answer;
    }
}