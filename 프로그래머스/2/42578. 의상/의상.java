import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, List<String>> map = new HashMap<>();

        for (String[] c : clothes) {
            map.putIfAbsent(c[1], new ArrayList<>());
            map.get(c[1]).add(c[0]);
        }

        int answer = 1;

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            answer = answer * (entry.getValue().size() + 1);
        }

        return answer - 1;
    }
}