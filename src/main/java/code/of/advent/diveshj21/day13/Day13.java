package code.of.advent.diveshj21.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day13 {
    private static final Logger logger = LoggerFactory.getLogger(Day13.class);

    public static void main(String[] args) throws IOException {
        var lines = Files.readString(Path.of("input-13.txt"));
        var machines = new ArrayList<Machine>();
        var pattern = Pattern.compile("Button A: X\\+(\\d+), Y\\+(\\d+)[\\r\\n]+Button B: X\\+(\\d+), Y\\+(\\d+)[\\r\\n]+Prize: X=(\\d+), Y=(\\d+)");
        var matcher = pattern.matcher(lines);
        while (matcher.find()) {
            var buttonA = new Button(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            var buttonB = new Button(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
            var prizeLocation = new Location(Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
            machines.add(new Machine(buttonA, buttonB, prizeLocation));
        }

        var clawCalculator = new ClawCalculator();

        var part1Answer = clawCalculator.getMinTokens(machines);
        logger.info("Part 1: {}", part1Answer);

        var part2Answer = clawCalculator.getMinTokensForRealMachines(machines);
        logger.info("Part 2: {}", part2Answer);
    }
}
