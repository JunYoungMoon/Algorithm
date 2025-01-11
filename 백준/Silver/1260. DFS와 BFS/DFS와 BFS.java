import java.util.*;

class DFS {
    boolean[] visited;
    Map<Integer, List<Integer>> graph;

    public DFS(int vertices, Map<Integer, List<Integer>> graph) {
        this.visited = new boolean[vertices + 1];
        this.graph = graph;
    }

    public void traverse(int start) {
        if (visited[start]) {
            return;
        }

        System.out.print(start + " ");
        visited[start] = true;

        List<Integer> neighbors = graph.get(start);

        if (neighbors != null) {
            for (int neighbor : neighbors) {
                traverse(neighbor);
            }
        }
    }
}

class BFS {
    boolean[] visited;
    Map<Integer, List<Integer>> graph;

    public BFS(int vertices, Map<Integer, List<Integer>> graph) {
        this.visited = new boolean[vertices + 1];
        this.graph = graph;
    }

    public void traverse(int start) {
        Queue<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            List<Integer> neighbors = graph.get(current);

            if (neighbors != null) {
                for (int neighbor : neighbors) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
        }
    }
}

public class Main {
    static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void solution(int vertices, int start) {
        DFS dfs = new DFS(vertices, graph);
        BFS bfs = new BFS(vertices, graph);

        dfs.traverse(start);
        System.out.println();
        bfs.traverse(start);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();
        int start = scanner.nextInt();

        scanner.nextLine();

        for (int i = 0; i < edges; i++) {
            int vertices1 = scanner.nextInt();
            int vertices2 = scanner.nextInt();
            graph.putIfAbsent(vertices1, new ArrayList<>());
            graph.putIfAbsent(vertices2, new ArrayList<>());

            graph.get(vertices1).add(vertices2);
            graph.get(vertices2).add(vertices1);

            scanner.nextLine();
        }

        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            Collections.sort(entry.getValue());
        }

        solution(vertices, start);
    }
}