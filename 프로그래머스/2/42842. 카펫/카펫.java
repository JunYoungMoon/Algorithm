import java.util.Arrays;

class Solution {
    public int[] solution(int brown, int yellow) {
        int carpet = brown + yellow;

        for (int x = 3; x <= carpet; x++) {
            if (carpet % x == 0) {
                int y = carpet / x;

                if(x >= y){
                    int center = (x - 2) * (y - 2);

                    if(center == yellow && carpet - center == brown){
                        return new int[]{x, y};
                    }
                }
            }
        }
        return new int[]{};
    }
}
