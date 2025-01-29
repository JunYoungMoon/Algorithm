class Solution {
    public int solution(String name) {
        int length = name.length();
        int totalMoves = 0;

        for (int i = 0; i < length; i++) {
            char c = name.charAt(i);
            int up = c - 'A';
            int down = 'Z' - c + 1;
            totalMoves += Math.min(up, down);
        }

        int minMove = length - 1;

        for (int i = 0; i < length; i++) {
            int next = i + 1;

            while (next < length && name.charAt(next) == 'A') {
                next++;
            }

            int move = i + (length - next); // i + (끝까지 가는 거리)

            move += Math.min(i, length - next);
            minMove = Math.min(minMove, move);
        }

        totalMoves += minMove;

        return totalMoves;
    }
}