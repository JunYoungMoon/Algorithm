import java.util.*;

class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();

    public int solution(int n, int[][] wires) {
        for (int[] wire : wires) {
            graph.putIfAbsent(wire[0], new ArrayList<>());
            graph.get(wire[0]).add(wire[1]);
            graph.putIfAbsent(wire[1], new ArrayList<>());
            graph.get(wire[1]).add(wire[0]);
        }

        int answer = Integer.MAX_VALUE;

        for (int[] wire : wires) {
            // 간선 제거
            graph.get(wire[0]).remove(Integer.valueOf(wire[1]));
            graph.get(wire[1]).remove(Integer.valueOf(wire[0]));

            // DFS 실행
            boolean[] visited = new boolean[n + 1];
            int count = dfs(wire[0], visited); // 한쪽 부분 그래프의 노드 개수

            // 두 전력망의 차이 계산
            int otherCount = n - count;
            answer = Math.min(answer, Math.abs(count - otherCount));

            // 간선 복구
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }

        return answer;
    }

    public int dfs(int node, boolean[] visited) {
        visited[node] = true;
        int count = 1; // 현재 노드 포함

        // 인접 노드 탐색
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                count += dfs(neighbor, visited); // 하위 노드의 개수를 누적
            }
        }

        return count;
    }
}