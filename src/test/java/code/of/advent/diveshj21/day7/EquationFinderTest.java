package code.of.advent.diveshj21.day7;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class EquationFinderTest {

    @Test
    void find() {
        var calibrations = Map.of(
            190L, new Long[]{10L, 19L},
            3267L, new Long[]{81L, 40L, 27L},
            83L, new Long[]{17L, 5L},
            156L, new Long[]{15L, 6L},
            7290L, new Long[]{6L, 8L, 6L, 15L},
            161011L, new Long[]{16L, 10L ,13L},
            192L, new Long[]{17L, 8L, 14L},
            21037L, new Long[]{9L, 7L, 18L, 13L},
            292L, new Long[]{11L, 6L, 16L, 20L}
        );
        var equationFinder = new EquationFinder(List.of(Operator.ADD, Operator.MULTIPLY));
        var actual = equationFinder.find(calibrations);
        assertEquals(3749L, actual);
    }

    @Test
    void findWithConcatenate() {
        var calibrations = Map.of(
            190L, new Long[]{10L, 19L},
            3267L, new Long[]{81L, 40L, 27L},
            83L, new Long[]{17L, 5L},
            156L, new Long[]{15L, 6L},
            7290L, new Long[]{6L, 8L, 6L, 15L},
            161011L, new Long[]{16L, 10L ,13L},
            192L, new Long[]{17L, 8L, 14L},
            21037L, new Long[]{9L, 7L, 18L, 13L},
            292L, new Long[]{11L, 6L, 16L, 20L}
        );
        var equationFinder = new EquationFinder(List.of(Operator.ADD, Operator.MULTIPLY, Operator.CONCATENATE));
        var actual = equationFinder.find(calibrations);
        assertEquals(11387L, actual);
    }
}
