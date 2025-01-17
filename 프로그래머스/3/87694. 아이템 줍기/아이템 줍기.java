import java.util.LinkedList;
import java.util.Queue;

class Node {
    int x;
    int y;
    int distance;

    public Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

class Solution {
    char[][] maps = new char[102][102];
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int i = 0; i < rectangle.length; i++) {
            int y1 = rectangle[i][0]; //1 3
            int x1 = rectangle[i][1]; //1 2
            int y2 = rectangle[i][2]; //7 5
            int x2 = rectangle[i][3]; //4 5

            draw(x1 * 2, y1 * 2, x2 * 2, y2 * 2);
        }

        return bfs(characterY * 2, characterX * 2, itemY * 2, itemX * 2);
    }

    public int bfs(int cX, int cY, int iX, int iY) {
        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(cX, cY, 1));
        maps[cX][cY] = '1';

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (maps[nx][ny] == '2') {
                    if (nx == iX && ny == iY) {
                        return (current.distance + 1) / 2;
                    }
                    queue.offer(new Node(nx, ny, current.distance + 1));
                    maps[nx][ny] = '1';
                }
            }
        }

        return 0;
    }

    public void draw(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (maps[i][j] == '1') continue;
                maps[i][j] = '1';
                if (i == x1 || i == x2 || j == y1 || j == y2) {
                    maps[i][j] = '2';
                }
            }
        }
    }
}