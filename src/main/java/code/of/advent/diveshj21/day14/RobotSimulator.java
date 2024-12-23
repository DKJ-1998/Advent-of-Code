package code.of.advent.diveshj21.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RobotSimulator {
    private static final Logger logger = LoggerFactory.getLogger(RobotSimulator.class);

    private static final List<DirectionChange> DIRECTION_CHANGES = List.of(
        new DirectionChange(x -> x + 1, y -> y + 1),
        new DirectionChange(x -> x, y -> y + 1),
        new DirectionChange(x -> x - 1, y -> y + 1),
        new DirectionChange(x -> x + 1, y -> y - 1),
        new DirectionChange(x -> x, y -> y - 1),
        new DirectionChange(x -> x - 1, y -> y - 1),
        new DirectionChange(x -> x + 1, y -> y),
        new DirectionChange(x -> x - 1, y -> y)
    );
    
    private final BathroomSize bathroomSize;
    private final int halfwayXCoordinate;
    private final int halfwayYCoordinate;

    public RobotSimulator(BathroomSize bathroomSize) {
        this.bathroomSize = bathroomSize;
        this.halfwayXCoordinate = (bathroomSize.width() - 1) / 2;
        this.halfwayYCoordinate = (bathroomSize.length() - 1) / 2;
    }

    public int calculateSafetyFactor(List<Robot> robots) {
        var numberOfRobotsInEachQuadrant = new HashMap<Quadrant, Integer>();
        for (var robot : robots) {
            var finalXPosition = Math.floorMod(robot.startingPosition().x() + robot.velocities().x() * 100, bathroomSize.width());
            var finalYPosition = Math.floorMod(robot.startingPosition().y() + robot.velocities().y() * 100, bathroomSize.length());
//            logger.info("Final Position: ({}, {})", finalXPosition, finalYPosition);
            if (finalXPosition != halfwayXCoordinate && finalYPosition != halfwayYCoordinate) {
                var xSection = finalXPosition < halfwayXCoordinate ? 0 : 1;
                var ySection = finalYPosition < halfwayYCoordinate ? 0 : 1;
                var quadrant = new Quadrant(xSection, ySection);
                numberOfRobotsInEachQuadrant.put(quadrant, numberOfRobotsInEachQuadrant.getOrDefault(quadrant, 0) + 1);
            }
        }
        return numberOfRobotsInEachQuadrant.values().stream().reduce((a, b) -> a * b).orElse(0);
    }

    public int checkForTree(List<Robot> robots) {
        var originalRobots = robots.stream().toList();

        var horizontalBar = -1;
        var verticalBar = -1;

        var secondsElapsed = 0;
        var map = new char[bathroomSize.length()][bathroomSize.width()];
        for (var row : map) {
            Arrays.fill(row, '.');
        }
        for (var robot : robots) {
            map[robot.startingPosition().y()][robot.startingPosition().x()] = 'O';
        }
        for (var row : map) {
            System.out.println(row);
        }
        System.out.println("Seconds elapsed: " + secondsElapsed);
        System.out.println("Is there a horizontal (h) or vertical (v) bar?");
        var in = new Scanner(System.in);
        var input = in.nextLine();
        if (input.equals("h")) {
            horizontalBar = secondsElapsed;
        } else if (input.equals("v")) {
            verticalBar = secondsElapsed;
        }

        while (secondsElapsed < 103) {
            secondsElapsed ++;
            var robotsWithNextPosition = new ArrayList<Robot>();
            for (var row : map) {
                Arrays.fill(row, '.');
            }
            for (var robot : robots) {
                var newXPosition = Math.floorMod(robot.startingPosition().x() + robot.velocities().x(), bathroomSize.width());
                var newYPosition = Math.floorMod(robot.startingPosition().y() + robot.velocities().y(), bathroomSize.length());
                robotsWithNextPosition.add(new Robot(new Position(newXPosition, newYPosition), robot.velocities()));
                map[newYPosition][newXPosition] = 'O';
            }
            robots = robotsWithNextPosition;
            for (var row : map) {
                System.out.println(row);
            }
            System.out.println("Seconds elapsed: " + secondsElapsed);
            System.out.println("Is there a horizontal (h) or vertical (v) bar?");
            in = new Scanner(System.in);
            input = in.nextLine();
            if (input.equals("h")) {
                horizontalBar = secondsElapsed;
            } else if (input.equals("v")) {
                verticalBar = secondsElapsed;
            }
        }

        if (horizontalBar == -1 || verticalBar == -1) {
            throw new RuntimeException("Horizontal or vertical bar not found");
        }

        while (horizontalBar != verticalBar) {
            if (verticalBar > horizontalBar) {
                horizontalBar += bathroomSize.length();
            } else {
                verticalBar += bathroomSize.width();
            }
        }

        var secondsElapsedToTree = horizontalBar;

        for (var row : map) {
            Arrays.fill(row, '.');
        }
        for (var robot : originalRobots) {
            var newXPosition = Math.floorMod(robot.startingPosition().x() + robot.velocities().x() * 7520, bathroomSize.width());
            var newYPosition = Math.floorMod(robot.startingPosition().y() + robot.velocities().y() * 7520, bathroomSize.length());
            map[newYPosition][newXPosition] = 'O';
        }
        for (var row : map) {
            System.out.println(row);
        }
        System.out.println("Seconds elapsed: " + secondsElapsedToTree);

        return secondsElapsedToTree;
    }
}
