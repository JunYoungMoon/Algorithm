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

    public int solution(int k, int[][] dungeons) {
        list = new ArrayList<>();

        for (int[] dungeon : dungeons) {
            list.add(new Dungeon(dungeon[0], dungeon[1]));
        }

        return dfs(k, 0);
    }

    private int dfs(int remainingFatigue, int depth) {
        int maxDepth = depth;

        for (Dungeon dungeon : list) {
            if (!dungeon.isVisited && remainingFatigue >= dungeon.minFatigue) {
                dungeon.isVisited = true;

                maxDepth = Math.max(
                        maxDepth,
                        dfs(remainingFatigue - dungeon.fatigueCost, depth + 1)
                );
                dungeon.isVisited = false;

            }
        }
        return maxDepth;
    }
}