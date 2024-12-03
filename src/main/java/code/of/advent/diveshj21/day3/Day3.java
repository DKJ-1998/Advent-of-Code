package code.of.advent.diveshj21.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day3 {
    private static final Logger logger = LoggerFactory.getLogger(Day3.class);

    public static void main(String[] args) throws IOException {
        var input = Files.readString(Path.of("input-3.txt"));

        var multiplier = new Multiplier();

        var part1Answer = multiplier.multiply(input);
        logger.info("Part 1 Answer: {}", part1Answer);

        var part2Answer = multiplier.multiplyWithConditional(input);
        logger.info("Part 2 Answer: {}", part2Answer);
    }
}
