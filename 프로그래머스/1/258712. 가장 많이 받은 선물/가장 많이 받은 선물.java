import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> giftRecord = new HashMap<>();
        Map<String, Integer> nextMonthGiftCounts = new HashMap<>();
        Map<String, Integer> giftScore = new HashMap<>();

        for (String friend : friends) {
            giftRecord.put(friend, new HashMap<>());
            nextMonthGiftCounts.put(friend, 0);
            giftScore.put(friend, 0);
        }

        for (String gift : gifts) {
            String giver = gift.split(" ")[0];
            String receiver = gift.split(" ")[1];

            giftRecord.get(giver).put(receiver, giftRecord.get(giver).getOrDefault(receiver, 0) + 1);
            giftScore.put(giver, giftScore.get(giver) + 1);
            giftScore.put(receiver, giftScore.get(receiver) - 1);
        }

        for (String giver : friends) {
            for (String receiver : friends) {
                if (!giver.equals(receiver)) {
                    int giftsFromGiver = giftRecord.get(giver).getOrDefault(receiver, 0);
                    int giftsFromReceiver = giftRecord.get(receiver).getOrDefault(giver, 0);

                    if (giftsFromGiver > giftsFromReceiver) {
                        nextMonthGiftCounts.put(giver, nextMonthGiftCounts.get(giver) + 1);
                    } else if (giftsFromGiver == giftsFromReceiver) {
                        if (giftScore.get(giver) > giftScore.get(receiver)) {
                            nextMonthGiftCounts.put(giver, nextMonthGiftCounts.get(giver) + 1);
                        }
                    }
                }
            }
        }

        return Collections.max(nextMonthGiftCounts.values());
    }
}