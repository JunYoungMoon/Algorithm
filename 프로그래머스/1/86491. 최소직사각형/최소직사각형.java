import java.util.Arrays;

class Solution {
    public int solution(int[][] sizes) {

        for(int i = 0 ; i < sizes.length ; i++){
            if(sizes[i][0] < sizes[i][1]){
                int tmp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
            }
        }

        int maxX = Arrays.stream(sizes).mapToInt(size -> size[0]).max().orElse(0);
        int maxY = Arrays.stream(sizes).mapToInt(size -> size[1]).max().orElse(0);

        return maxX * maxY;
    }
}