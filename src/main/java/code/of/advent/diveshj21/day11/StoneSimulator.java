package code.of.advent.diveshj21.day11;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoneSimulator {
    private static final Logger logger = LoggerFactory.getLogger(StoneSimulator.class);

    // brute force
//    public long getFinalStones(ArrayList<Long> stones, int blinks) {
//        var newStones = stones;
//        for (int i = 0; i < blinks; i++) {
////            logger.info("{}", stones.stream().map(Object::toString).collect(Collectors.joining(" ")));
//            logger.info("Blink {}", i + 1);
//            stones = newStones;
//            newStones = new ArrayList<>();
//            for (Long stone : stones) {
//                if (stone == 0) {
//                    newStones.add(stone + 1);
//                } else if (Math.floor(Math.log10(stone)) % 2 == 1) {
//                    var powerOf10 = Math.pow(10, (Math.floor(Math.log10(stone)) + 1) / 2);
//                    var firstStone = Math.floor(stone / powerOf10);
//                    var secondStone = stone - firstStone * powerOf10;
//                    newStones.add((long) firstStone);
//                    newStones.add((long) secondStone);
//                } else {
//                    newStones.add(stone * 2024);
//                }
//            }
//        }
//        return newStones.size();
//    }

    public long getFinalStones(List<Long> stones, int blinks) {
        var stonesByValue = new HashMap<Long, StoneProperties>();
        for (var stoneValue : stones) {
            if (stonesByValue.containsKey(stoneValue)) {
                stonesByValue.get(stoneValue).increaseCount(1);
            } else {
                stonesByValue.put(stoneValue, new StoneProperties(getNextStones(stoneValue), 1));
            }
        }
        var nextStonesByValue = new HashMap<Long, StoneProperties>();
        for (int i = 0; i < blinks; i++) {
            logger.info("Calculating blink {} of {}", i + 1, blinks);
            for (var stoneEntry : stonesByValue.entrySet()) {
                var stoneCount = stoneEntry.getValue().getCount();
                for (var nextStone : stoneEntry.getValue().getNextStones()) {
                    if (nextStonesByValue.containsKey(nextStone)) {
                        nextStonesByValue.get(nextStone).increaseCount(stoneCount);
                    } else {
                        nextStonesByValue.put(nextStone, new StoneProperties(getNextStones(nextStone), stoneCount));
                    }
                }
            }
            stonesByValue = nextStonesByValue;
            nextStonesByValue = new HashMap<>();
            logger.info("Completed blink {} of {}", i + 1, blinks);
        }
        var count = 0L;
        for (var stoneEntry : stonesByValue.entrySet()) {
            count += stoneEntry.getValue().getCount();
        }
        return count;
    }

    public List<Long> getNextStones(long stoneValue) {
        if (stoneValue == 0) {
            return List.of(stoneValue + 1);
        } else if (Math.floor(Math.log10(stoneValue)) % 2 == 1) {
            var powerOf10 = Math.pow(10, (Math.floor(Math.log10(stoneValue)) + 1) / 2);
            var firstStone = Math.floor(stoneValue / powerOf10);
            var secondStone = stoneValue - firstStone * powerOf10;
            return List.of((long) firstStone, (long) secondStone);
        } else {
            return List.of(stoneValue * 2024);
        }
    }
}
