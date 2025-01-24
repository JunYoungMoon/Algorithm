import java.util.ArrayList;
import java.util.List;

class Dungeon {
    int minFatigue; //최소 필요 피로도
    int fatigueCost; //소모 피로도
    boolean isVisited;

    public Dungeon(int minFatigue, int fatigueCost) {
        this.minFatigue = minFatigue;
        this.fatigueCost = fatigueCost;
    }
}

class Solution {
    List<Dungeon> list;
    int maxDepth = 0;

    public int solution(int k, int[][] dungeons) {
        list = new ArrayList<>();

        for (int[] dungeon : dungeons) {
            list.add(new Dungeon(dungeon[0], dungeon[1]));
        }

        dfs(k, 0);

        return maxDepth;
    }

    private void dfs(int remainingFatigue, int depth) {
        for (Dungeon dungeon : list) {
            if (!dungeon.isVisited && remainingFatigue >= dungeon.minFatigue) {
                dungeon.isVisited = true;
                depth++;
                if (depth > maxDepth) {
                    maxDepth = depth;
                }
                dfs(remainingFatigue - dungeon.fatigueCost, depth);
                dungeon.isVisited = false;
                depth--;
            }
        }
    }
}