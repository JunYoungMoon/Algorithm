import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class WordNode {
        String word;
        int depth;

        WordNode(String word, int depth) {
            this.word = word;
            this.depth = depth;
        }
    }

    public int solution(String begin, String target, String[] words) {
        if (!Arrays.asList(words).contains(target)) {
            return 0;
        }

        Queue<WordNode> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];

        queue.offer(new WordNode(begin,0));

        while(!queue.isEmpty()){
            WordNode current = queue.poll();
            String currentWord = current.word;
            int currentDepth  = current.depth;

            if(currentWord.equals(target)){
                return currentDepth;
            }

            for(int i = 0; i < words.length; i++){
                if(!visited[i] && canTransform(currentWord, words[i])){
                    visited[i] = true;
                    queue.offer(new WordNode(words[i], currentDepth + 1));
                }
            }
        }

        return 0;
    }

    private boolean canTransform(String word1, String word2){
        int diffCount = 0;
        for(int i = 0; i< word1.length(); i++){
            if(word1.charAt(i) != word2.charAt(i)){
                diffCount++;

                if(diffCount > 1){
                    return false;
                }
            }
        }
        return diffCount == 1;
    }
}