package code.of.advent.diveshj21.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day5 {
    private static final Logger logger = LoggerFactory.getLogger(Day5.class);

    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Path.of("input-5.txt"));

        var rules = new HashMap<Integer, Set<Integer>>();
        var updates = new ArrayList<ArrayList<Integer>>();
        for (var line : lines) {
            if (line.contains("|")) {
                var splitLine = line.split("\\|");
                var first = Integer.parseInt(splitLine[0]);
                var second = Integer.parseInt(splitLine[1]);
                if (rules.containsKey(first)) {
                    rules.get(first).add(second);
                } else {
                    rules.put(first, new HashSet<>(Set.of(second)));
                }
            } else if (!line.isEmpty()) {
                updates.add(
                    Arrays.stream(line.split(","))
                        .map(Integer::parseInt)
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
            }
        }
        logger.info("{}", rules);
        logger.info("{}", updates);
        var pageOrderer = new PageOrderer();

        var part1Answer = pageOrderer.sum(rules, updates);
        logger.info("Part 1: {}", part1Answer);

        var part2Answer = pageOrderer.reorderedSum(rules, updates);
        logger.info("Part 2: {}", part2Answer);
    }
}
