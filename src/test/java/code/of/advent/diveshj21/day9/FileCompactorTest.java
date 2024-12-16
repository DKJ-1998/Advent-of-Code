package code.of.advent.diveshj21.day9;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FileCompactorTest {

    @ParameterizedTest
    @MethodSource
    void compact(String diskMap, int expected) {
        var fileCompactor = new FileCompactor();
        var actual = fileCompactor.compact(diskMap);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> compact() {
        return Stream.of(
            Arguments.of("12345", 60),
            Arguments.of("2333133121414131402", 1928));
    }

    @Test
    void compactWholeFile() {
        var diskMap = "2333133121414131402";
        var fileCompactor = new FileCompactor();
        var actual = fileCompactor.compactWholeFile(diskMap);
        assertEquals(2858, actual);
    }
}
