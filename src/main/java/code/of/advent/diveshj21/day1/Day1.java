package code.of.advent.diveshj21.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day1 {
    private static final Logger logger = LoggerFactory.getLogger(Day1.class);

    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Path.of("input-1.txt"));

        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        for (var line : lines) {
            var splitLine = line.split(" {3}");
            leftList.add(Integer.parseInt(splitLine[0]));
            rightList.add(Integer.parseInt(splitLine[1]));
        }
        Collections.sort(leftList);
        Collections.sort(rightList);
        var part1Answer = IntStream.range(0, leftList.size())
            .map(index -> Math.abs(leftList.get(index) - rightList.get(index)))
            .sum();
        logger.info("Part 1: {}", part1Answer);

        HashMap<Integer, Integer> leftCount = new HashMap<>();
        HashMap<Integer, Integer> rightCount = new HashMap<>();

        for (var line : lines) {
            var splitLine = line.split(" {3}");
            var leftNumber = Integer.parseInt(splitLine[0]);
            var rightNumber = Integer.parseInt(splitLine[1]);
            leftCount.put(leftNumber, leftCount.getOrDefault(leftNumber, 0) + 1);
            rightCount.put(rightNumber, rightCount.getOrDefault(rightNumber, 0) + 1);
        }
        var part2Answer = leftCount.entrySet().stream()
            .mapToInt(entry -> entry.getKey() * entry.getValue() * rightCount.getOrDefault(entry.getKey(), 0))
            .sum();
        logger.info("Part 2: {}", part2Answer);
    }
}