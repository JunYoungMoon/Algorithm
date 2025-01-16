import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Node {
    String word;
    int distance;

    public Node(String word, int distance) {
        this.word = word;
        this.distance = distance;
    }
}

class Solution {
        public static int solution(String begin, String target, String[] words) {
        if (!Arrays.asList(words).contains(target)) {
            return 0;
        }

        boolean[] visited = new boolean[words.length];

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(begin, 1));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && wordCheck(node.word, words[i])) {
                    if (words[i].equals(target)) {
                        return node.distance;
                    }

                    queue.offer(new Node(words[i], node.distance + 1));
                    visited[i] = true;
                }
            }
        }
        return 0;
    }

    private static boolean wordCheck(String word1, String word2) {
        int different = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                different++;
            }

            if (different > 1) return false;
        }

        return true;
    }
}