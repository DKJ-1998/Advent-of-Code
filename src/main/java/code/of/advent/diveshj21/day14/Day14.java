package code.of.advent.diveshj21.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day14 {
    private static final Logger logger = LoggerFactory.getLogger(Day14.class);

    public static void main(String[] args) throws IOException {
        var lines = Files.readString(Path.of("input-14.txt"));
        var robots = new ArrayList<Robot>();
        var pattern = Pattern.compile("p=(\\d+),(\\d+) v=(-{0,1}\\d+),(-{0,1}\\d+)");
        var matcher = pattern.matcher(lines);
        while (matcher.find()) {
            var startingPosition = new Position(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            var velocities = new Velocities(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
            var robot = new Robot(startingPosition, velocities);
            robots.add(robot);
        }

        var robotSimulator = new RobotSimulator(new BathroomSize(101, 103));

        var part1Answer = robotSimulator.calculateSafetyFactor(robots);
        logger.info("Part 1: {}", part1Answer);

        var part2Answer = robotSimulator.checkForTree(robots);
        logger.info("Part 2: {}", part2Answer);
    }
}
