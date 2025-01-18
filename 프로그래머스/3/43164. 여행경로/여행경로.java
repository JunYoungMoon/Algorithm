import java.util.*;

class Node implements Comparable<Node> {
    String ticket;
    boolean visited;

    public Node(String ticket) {
        this.ticket = ticket;
        this.visited = false;
    }

    @Override
    public int compareTo(Node other) {
        return this.ticket.compareTo(other.ticket);
    }
}

class Solution {
    Map<String, List<Node>> graph = new HashMap<>();
    List<String> answerList = new ArrayList<>();
    int totalTickets = 0;

    public String[] solution(String[][] tickets) {
        totalTickets = tickets.length;

        for (String[] ticket : tickets) {
            graph.putIfAbsent(ticket[0], new ArrayList<>());
            graph.get(ticket[0]).add(new Node(ticket[1]));
        }

        for (Map.Entry<String, List<Node>> entry : graph.entrySet()) {
            Collections.sort(entry.getValue());
        }

        answerList.add("ICN");
        dfs("ICN");

        return answerList.toArray(String[]::new);
    }

    private boolean dfs(String ticket) {
        // 모든 항공권을 사용한 경우 종료
        if (answerList.size() == totalTickets + 1) {
            return true;
        }

        List<Node> bucket = graph.get(ticket);

        if(bucket != null){
            for (Node next : bucket) {
                if(next.visited) continue;

                // 항공권 사용
                next.visited = true;
                answerList.add(next.ticket);

                // 다음 공항으로 이동
                if (dfs(next.ticket)) {
                    return true;
                }

                // 실패 시 복원
                next.visited = false;
                answerList.remove(answerList.size() - 1);
            }
        }
        return false;
    }
}