package code.of.advent.diveshj21.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day2 {
    private static final Logger logger = LoggerFactory.getLogger(Day2.class);

    public static void main(String[] args) throws IOException {
        var safetyCalculator = new SafetyCalculator();
        var lines = Files.readAllLines(Path.of("input-2.txt"));

        var part1Answer = lines.stream()
            .map(line -> {
                var values = line.split(" ");
                return Arrays.stream(values).map(Integer::parseInt).toList();
            })
            .filter(safetyCalculator::isSafe)
            .count();

        logger.info("Part 1 Answer: {}", part1Answer);

        var part2Answer = lines.stream()
            .map(line -> {
                var values = line.split(" ");
                return Arrays.stream(values).map(Integer::parseInt).toList();
            })
            .filter(safetyCalculator::isSafeWithDampener)
            .count();

        logger.info("Part 2 Answer: {}", part2Answer);
        logger.info("Part 2 Answer is actually 413");
    }
}
