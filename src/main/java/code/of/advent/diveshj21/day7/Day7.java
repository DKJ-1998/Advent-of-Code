package code.of.advent.diveshj21.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import code.of.advent.diveshj21.day6.Router;

public class Day7 {
    private static final Logger logger = LoggerFactory.getLogger(Day7.class);

    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Path.of("input-7.txt"));
        var calibrations = lines.stream()
            .map(line -> {
                var parts = line.split(": ");
                var target = Long.parseLong(parts[0]);
                var numbers = Arrays.stream(parts[1].split(" ")).map(Long::parseLong).toArray(Long[]::new);
                return Map.entry(target, numbers);
            })
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        var equationFinder = new EquationFinder(List.of(Operator.ADD, Operator.MULTIPLY));
        var part1Answer = equationFinder.find(calibrations);
        logger.info("Part 1: {}", part1Answer);

        var equationFinder2 = new EquationFinder(List.of(Operator.ADD, Operator.MULTIPLY, Operator.CONCATENATE));
        var part2Answer = equationFinder2.find(calibrations);
        logger.info("Part 2: {}", part2Answer);
    }
}
