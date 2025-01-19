import java.util.*;
import java.util.stream.Collectors;

class Puzzle {
    List<Node> blocks;
    boolean visited;

    public Puzzle(List<Node> blocks) {
        this.blocks = blocks;
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        List<Puzzle> puzzles = extractPuzzlesFromTable(table);
        List<Puzzle> spaces = extractSpacesFromGameBoard(game_board);

        for (Puzzle space : spaces) {
            for (Puzzle puzzle : puzzles) {
                if (puzzle.visited) continue;  // 퍼즐이 이미 사용되었으면 넘어감

                if (space.blocks.size() == puzzle.blocks.size() && matches(space, puzzle)) {
                    puzzle.visited = true;  // 퍼즐을 사용했음을 표시
                    answer += puzzle.blocks.size();
                    break;
                }
            }
        }

        return answer;
    }

    public List<Node> rotate(List<Node> blocks, int angle) {
        List<Node> rotated = new ArrayList<>();
        for (Node block : blocks) {
            int x = block.x;
            int y = block.y;

            // 회전 각도에 따라 새로운 좌표 계산
            if (angle == 90) {
                rotated.add(new Node(-y, x));
            } else if (angle == 180) {
                rotated.add(new Node(-x, -y));
            } else if (angle == 270) {
                rotated.add(new Node(y, -x));
            }
        }
        return rotated;
    }

    private List<Puzzle> extractPuzzlesFromTable(int[][] table) {
        List<Puzzle> puzzles = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j] == 1) {
                    puzzles.add(new Puzzle(normalize(bfs(i, j, table, 1))));
                }
            }
        }
        return puzzles;
    }

    private List<Puzzle> extractSpacesFromGameBoard(int[][] game_board) {
        List<Puzzle> spaces = new ArrayList<>();
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board[0].length; j++) {
                if (game_board[i][j] == 0) {
                    spaces.add(new Puzzle(normalize(bfs(i, j, game_board, 0))));
                }
            }
        }
        return spaces;
    }

    public boolean matches(Puzzle space, Puzzle puzzle) {
        List<List<Node>> rotations = new ArrayList<>();
        rotations.add(normalize(puzzle.blocks)); // 0도 (기본 상태)
        rotations.add(normalize(rotate(puzzle.blocks, 90)));
        rotations.add(normalize(rotate(puzzle.blocks, 180)));
        rotations.add(normalize(rotate(puzzle.blocks, 270)));

        return rotations.stream().anyMatch(rotation ->
                rotation.stream().allMatch(node ->
                        space.blocks.stream().anyMatch(spaceNode ->
                                spaceNode.x == node.x && spaceNode.y == node.y
                        )
                )
        );
    }


    public List<Node> bfs(int x, int y, int[][] map, int targetValue) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));

        List<Node> bucket = new ArrayList<>();
        bucket.add(new Node(x, y));
        map[x][y] = 1 - targetValue;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + current.x;
                int ny = dy[i] + current.y;

                if (nx >= 0 && ny >= 0 &&
                        nx < map.length &&
                        ny < map[0].length &&
                        map[nx][ny] == targetValue) {
                    queue.offer(new Node(nx, ny));
                    bucket.add(new Node(nx, ny));
                    map[nx][ny] = 1 - targetValue;
                }
            }
        }
        return bucket;
    }

    public List<Node> normalize(List<Node> bucket) {
        int minX = bucket.stream().mapToInt(node -> node.x).min().orElse(0);
        int minY = bucket.stream().mapToInt(node -> node.y).min().orElse(0);

        return bucket.stream()
                .map(node -> new Node(node.x - minX, node.y - minY))
                .collect(Collectors.toList());
//                .toList();
    }
}