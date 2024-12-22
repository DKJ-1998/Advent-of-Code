package code.of.advent.diveshj21.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day12 {
    private static final Logger logger = LoggerFactory.getLogger(Day12.class);

    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Path.of("input-12.txt"));
        var map = lines.stream().map(String::toCharArray).toArray(char[][]::new);

        var gardenGrouper = new GardenGrouper(map);

        var part1Answer = gardenGrouper.getPrice();
        logger.info("Part 1: {}", part1Answer);

        var part2Answer = gardenGrouper.getDiscountedPrice();
        logger.info("Part 2: {}", part2Answer);
    }
}
