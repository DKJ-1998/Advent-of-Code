package code.of.advent.diveshj21.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day10 {
    private static final Logger logger = LoggerFactory.getLogger(Day10.class);

    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Path.of("input-10.txt"));
        var map = lines.stream()
            .map(line -> Arrays.stream(line.split(""))
                .mapToInt(Integer::parseInt)
                .toArray())
            .toArray(int[][]::new);

        var trailheadScorer = new TrailheadScorer(map);

        var part1Answer = trailheadScorer.getTrailheadScore(HashSet::new);
        logger.info("Part 1: {}", part1Answer);

        var part2Answer = trailheadScorer.getTrailheadScore(ArrayList::new);
        logger.info("Part 2: {}", part2Answer);
    }
}
