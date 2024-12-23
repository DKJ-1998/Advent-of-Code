package code.of.advent.diveshj21.day13;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ClawCalculatorTest {

    @Test
    void getMinTokens() {
        var machines = List.of(
            new Machine(new Button(94, 34),  new Button(22, 67), new Location(8400, 5400)),
            new Machine(new Button(26, 66),  new Button(67, 21), new Location(12748, 12176)),
            new Machine(new Button(17, 86),  new Button(84, 37), new Location(7870, 6450)),
            new Machine(new Button(69, 23),  new Button(27, 71), new Location(18641, 10279))
        );
        
        var clawCalculator = new ClawCalculator();
        var actual = clawCalculator.getMinTokens(machines);
        assertEquals(480, actual);
    }
}