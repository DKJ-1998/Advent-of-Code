package code.of.advent.diveshj21.day2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SafetyCalculatorTest {

    @ParameterizedTest
    @MethodSource
    void isSafe_SafeReport_ReturnsTrue(List<Integer> report) {
        var safetyCalculator = new SafetyCalculator();
        assertTrue(safetyCalculator.isSafe(report));
    }

    private static Stream<Arguments> isSafe_SafeReport_ReturnsTrue() {
        return Stream.of(
            Arguments.of(List.of(7, 6, 4, 2, 1)),
            Arguments.of(List.of(1, 3, 6, 7, 9))
        );
    }

    @ParameterizedTest
    @MethodSource
    void isSafe_UnsafeReport_ReturnsFalse(List<Integer> report) {
        var safetyCalculator = new SafetyCalculator();
        assertFalse(safetyCalculator.isSafe(report));
    }

    private static Stream<Arguments> isSafe_UnsafeReport_ReturnsFalse() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 7, 8, 9)),
            Arguments.of(List.of(9, 7, 6, 2, 1)),
            Arguments.of(List.of(1, 3, 2, 4, 5)),
            Arguments.of(List.of(8, 6, 4, 4, 1))
        );
    }

    @ParameterizedTest
    @MethodSource
    void isSafeWithDampener_SafeReport_ReturnsTrue(List<Integer> report) {
        var safetyCalculator = new SafetyCalculator();
        assertTrue(safetyCalculator.isSafeWithDampener(report));
    }

    private static Stream<Arguments> isSafeWithDampener_SafeReport_ReturnsTrue() {
        return Stream.of(
            Arguments.of(List.of(7, 6, 4, 2, 1)),
            Arguments.of(List.of(1, 3, 2, 4, 5)),
            Arguments.of(List.of(8, 6, 4, 4, 1)),
            Arguments.of(List.of(1, 3, 6, 7, 9)),
            Arguments.of(List.of(10, 10, 11, 12, 13)),
            Arguments.of(List.of(10, 14, 12, 11, 10)),
            Arguments.of(List.of(1, 14, 15, 16, 17)),
            Arguments.of(List.of(4, 1, 5, 6, 7))
        );
    }

    @ParameterizedTest
    @MethodSource
    void isSafeWithDampener_UnsafeReport_ReturnsFalse(List<Integer> report) {
        var safetyCalculator = new SafetyCalculator();
        assertFalse(safetyCalculator.isSafeWithDampener(report));
    }

    private static Stream<Arguments> isSafeWithDampener_UnsafeReport_ReturnsFalse() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 7, 8, 9)),
            Arguments.of(List.of(9, 7, 6, 2, 1))
        );
    }
}