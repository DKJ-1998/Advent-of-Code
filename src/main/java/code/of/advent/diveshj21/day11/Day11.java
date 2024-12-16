package code.of.advent.diveshj21.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day11 {
    private static final Logger logger = LoggerFactory.getLogger(Day11.class);

    public static void main(String[] args) throws IOException {
        var input = Files.readString(Path.of("input-11.txt"));
        var stones = Arrays.stream(input.split(" "))
            .map(Long::parseLong)
            .toList();

        var stoneSimulator = new StoneSimulator();

        var part1Answer = stoneSimulator.getFinalStones(stones, 25);
        logger.info("Part 1: {}", part1Answer);

        var part2Answer = stoneSimulator.getFinalStones(stones, 75);
        logger.info("Part 2: {}", part2Answer);
    }
}
