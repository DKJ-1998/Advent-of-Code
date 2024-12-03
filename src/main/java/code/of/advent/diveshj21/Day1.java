package code.of.advent.diveshj21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day1 {
    private static final Logger logger = LoggerFactory.getLogger(Day1.class);

    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Path.of("input.txt"));
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        for (var line : lines) {
            var splitLine = line.split("   ");
            leftList.add(Integer.parseInt(splitLine[0]));
            rightList.add(Integer.parseInt(splitLine[1]));
        }
        Collections.sort(leftList);
        Collections.sort(rightList);
        var answer = IntStream.range(0, leftList.size())
            .map(index -> Math.abs(leftList.get(index) - rightList.get(index)))
            .sum();
        logger.info("Answer: {}", answer);
    }
}