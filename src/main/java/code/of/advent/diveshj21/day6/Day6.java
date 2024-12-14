package code.of.advent.diveshj21.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import code.of.advent.diveshj21.day4.Wordsearcher;

public class Day6 {
    private static final Logger logger = LoggerFactory.getLogger(Day6.class);

    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Path.of("input-6.txt"));
        var map = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        var router = new Router();

        var part1Answer = router.routeGuard(map);
        logger.info("Part 1: {}", part1Answer);

        var mapPart2 = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        var part2Answer = router.blockGuard(mapPart2);
        logger.info("Part 2: {}", part2Answer);
    }
}
