package code.of.advent.diveshj21.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import code.of.advent.diveshj21.day7.EquationFinder;
import code.of.advent.diveshj21.day7.Operator;

public class Day8 {
    private static final Logger logger = LoggerFactory.getLogger(Day8.class);

    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Path.of("input-8.txt"));
        var map = lines.stream().map(String::toCharArray).toArray(char[][]::new);

        var antinodeDetector = new AntinodeDetector(map);

        var part1Answer = antinodeDetector.detect();
        logger.info("Part 1: {}", part1Answer);

        var part2Answer = antinodeDetector.detectManyAntinodes();
        logger.info("Part 2: {}", part2Answer);
    }
}
