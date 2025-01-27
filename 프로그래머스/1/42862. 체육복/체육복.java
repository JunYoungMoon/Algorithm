import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        List<Integer> lostList = new ArrayList<>();
        List<Integer> reserveList = new ArrayList<>();

        for (int l : lost) lostList.add(l);
        for (int r : reserve) reserveList.add(r);

        lostList.sort(Integer::compareTo);
        reserveList.sort(Integer::compareTo);

        List<Integer> intersection = new ArrayList<>(lostList);
        intersection.retainAll(reserveList);

        lostList.removeAll(intersection);
        reserveList.removeAll(intersection);

        int answer = n - lostList.size();

        for (int r : reserveList) {
            int front = r - 1;
            int end = r + 1;

            if (lostList.contains(front)) {
                lostList.remove(Integer.valueOf(front));
                answer++;
            } else if (lostList.contains(end)) {
                lostList.remove(Integer.valueOf(end));
                answer++;
            }
        }
        return answer;
    }
}