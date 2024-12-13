package code.of.advent.diveshj21.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day4 {
    private static final Logger logger = LoggerFactory.getLogger(Day4.class);

    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Path.of("input-4.txt"));
        var wordsearch = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        var wordsearcher = new Wordsearcher();
        var part1Answer = wordsearcher.search(wordsearch);

        logger.info("Part 1: {}", part1Answer);

        var part2Answer = wordsearcher.searchMASinX(wordsearch);
        logger.info("Part 2: {}", part2Answer);
    }
}
