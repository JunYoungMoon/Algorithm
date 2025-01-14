class Solution {
    boolean[] visited;

    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                recursive(n, i, computers);
                answer++;
            }
        }

        return answer;
    }

    private void recursive(int n, int i, int[][] computers) {
        if (visited[i]) {
            return;
        }

        visited[i] = true;

        for (int j = 0; j < n; j++) {
            if (i != j && computers[i][j] == 1) {
                recursive(n, j, computers);
            }
        }
    }
}