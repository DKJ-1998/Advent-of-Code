package code.of.advent.diveshj21.day14;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class RobotSimulatorTest {

    @Test
    void calculateSafetyFactor() {
        var robots = List.of(
            new Robot(new Position(0, 4), new Velocities(3, -3)),
            new Robot(new Position(6, 3), new Velocities(-1, -3)),
            new Robot(new Position(10, 3), new Velocities(-1, 2)),
            new Robot(new Position(2, 0), new Velocities(2, -1)),
            new Robot(new Position(0, 0), new Velocities(1, 3)),
            new Robot(new Position(3, 0), new Velocities(-2, -2)),
            new Robot(new Position(7, 6), new Velocities(-1, -3)),
            new Robot(new Position(3, 0), new Velocities(-1, -2)),
            new Robot(new Position(9, 3), new Velocities(2, 3)),
            new Robot(new Position(7, 3), new Velocities(-1, 2)),
            new Robot(new Position(2, 4), new Velocities(2, -3)),
            new Robot(new Position(9, 5), new Velocities(-3, -3))
        );

        var robotSimulator = new RobotSimulator(new BathroomSize(11, 7));
        var actual = robotSimulator.calculateSafetyFactor(robots);

        assertEquals(12, actual);
    }
}
