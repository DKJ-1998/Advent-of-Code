package code.of.advent.diveshj21.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day9 {
    private static final Logger logger = LoggerFactory.getLogger(Day9.class);

    public static void main(String[] args) throws IOException {
        var diskMap = Files.readString(Path.of("input-9.txt"));

        var fileCompactor = new FileCompactor();

        var part1Answer = fileCompactor.compact(diskMap);
        logger.info("Part 1: {}", part1Answer);

        var part2Answer = fileCompactor.compactWholeFile(diskMap);
        logger.info("Part 2: {}", part2Answer);
    }
}