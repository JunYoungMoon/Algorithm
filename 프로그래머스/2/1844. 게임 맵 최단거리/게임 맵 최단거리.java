import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int bfs(int[][] maps) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int n = maps.length;
        int m = maps[0].length;

        // 시작점이 도착점인 경우 바로 반환
        if (n == 1 && m == 1) {
            return maps[0][0] == 1 ? 1 : -1;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 1});
        maps[0][0] = 0; // 방문 처리

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], distance = current[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] == 1) {
                    if (nx == n - 1 && ny == m - 1) {
                        return distance + 1; // 도착시 반환
                    }

                    queue.offer(new int[]{nx, ny, distance + 1});
                    maps[nx][ny] = 0; // 방문 처리
                }
            }
        }

        return -1; // 도착 실패
    }

    public int solution(int[][] maps) {
        return bfs(maps);
    }
}