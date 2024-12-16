package code.of.advent.diveshj21.day11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StoneSimulatorTest {

    @ParameterizedTest
    @MethodSource
    void getFinalStones(List<Long> stones, int blinks, int expected) {
        var stoneSimulator = new StoneSimulator();
        var actual = stoneSimulator.getFinalStones(stones, blinks);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getFinalStones() {
        return Stream.of(
            Arguments.of(List.of(0L, 1L, 10L, 99L, 999L), 1, 7),
            Arguments.of(List.of(125L, 17L), 6, 22),
            Arguments.of(List.of(125L, 17L), 25, 55312)
        );
    }
}
