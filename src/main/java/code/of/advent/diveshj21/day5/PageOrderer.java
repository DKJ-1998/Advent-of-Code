package code.of.advent.diveshj21.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageOrderer {
    private static final Logger logger = LoggerFactory.getLogger(PageOrderer.class);

    public int sum(Map<Integer, Set<Integer>> rules, List<ArrayList<Integer>> updates) {
        var sum = 0;
        for (var update : updates) {
            var validUpdate = true;
            for (int i = 0; i < update.size(); i++) {
                var page = update.get(i);
                for (int j = 0; j < i; j++) {
                    if (rules.containsKey(page)) {
                        if (rules.get(page).contains(update.get(j))) {
                            validUpdate = false;
                            break;
                        }
                    }
                }
                if (!validUpdate) {
                    break;
                }
            }
            if (validUpdate) {
                var middleIndex = (update.size() - 1) / 2;
                sum += update.get(middleIndex);
            }
        }
        return sum;
    }

    public int reorderedSum(Map<Integer, Set<Integer>> rules, List<ArrayList<Integer>> updates) {
        var sum = 0;
        for (var update : updates) {
            var validUpdate = true;
//            logger.info("Original update: {}", update);
            for (int i = 0; i < update.size(); i++) {
                logger.info("{}", update.get(i));
                var page = update.get(i);
                for (int j = 0; j < i; j++) {
                    if (rules.containsKey(page)) {
                        if (rules.get(page).contains(update.get(j))) {
                            validUpdate = false;
//                            logger.info("Bubbling up {} to {}", update.get(i), j);
                            for (int k = i; k > j; k--) {
                                var last = update.get(k);
                                update.set(k, update.get(k - 1));
                                update.set(k - 1, last);
                            }
                            break;
                        }
                    }
                }
//                logger.info("Mid-ordering update: {}", update);
            }
            if (!validUpdate) {
                var middleIndex = (update.size() - 1) / 2;
                sum += update.get(middleIndex);
//                logger.info("Invalid update: {}", update);
            }
        }
        return sum;
    }
}
