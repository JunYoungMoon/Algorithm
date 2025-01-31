import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        //정렬
        people = Arrays.stream(people).sorted().toArray();

        //최대 2명이 탈수 있으면 2명이 다 탔을때가 효율적
        //무게가 무거운 사람과 같이 타는것이 나음

        int lptr = 0;
        int rptr = people.length - 1;

        //투포인터 방법 사용필요
        while (lptr <= rptr) {
            if (people[lptr] + people[rptr] <= limit) {
                lptr++;
                rptr--;
            } else {
                rptr--;
            }

            answer++;
        }
        return answer;
    }
}