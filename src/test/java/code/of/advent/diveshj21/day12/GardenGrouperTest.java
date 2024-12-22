package code.of.advent.diveshj21.day12;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GardenGrouperTest {

    @ParameterizedTest
    @MethodSource
    void getPrice(char[][] map, int expected) {
        var gardenGrouper = new GardenGrouper(map);
        var actual = gardenGrouper.getPrice();
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getPrice() {
        var map1 = convertTextBlockToMap(
            """
AAAA
BBCD
BBCC
EEEC
                """);
        var map2 = convertTextBlockToMap(
            """
OOOOO
OXOXO
OOOOO
OXOXO
OOOOO
                """);
        var map3 = convertTextBlockToMap(
            """
RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE
                """);
        return Stream.of(
            Arguments.of(map1, 140),
            Arguments.of(map2, 772),
            Arguments.of(map3, 1930)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getDiscountedPrice(char[][] map, int expected) {
        var gardenGrouper = new GardenGrouper(map);
        var actual = gardenGrouper.getDiscountedPrice();
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getDiscountedPrice() {
        var map1 = convertTextBlockToMap(
            """
AAAA
BBCD
BBCC
EEEC
                """);
        var map2 = convertTextBlockToMap(
            """
OOOOO
OXOXO
OOOOO
OXOXO
OOOOO
                """);
        var map3 = convertTextBlockToMap(
            """
EEEEE
EXXXX
EEEEE
EXXXX
EEEEE
                """);
        var map4 = convertTextBlockToMap(
            """
AAAAAA
AAABBA
AAABBA
ABBAAA
ABBAAA
AAAAAA
                """);
        var map5 = convertTextBlockToMap(
            """
RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE
                """);
        var map6 = convertTextBlockToMap(
            """
FIND WHERE ITS BROKEN
                """);
        return Stream.of(
            Arguments.of(map1, 80),
            Arguments.of(map2, 436),
            Arguments.of(map3, 236),
            Arguments.of(map4, 368),
            Arguments.of(map5, 1206),
            Arguments.of(map6, 84)
        );
    }

    private static char[][] convertTextBlockToMap(String textBlock) {
        return Arrays.stream(textBlock.split("\n")).map(String::toCharArray).toArray(char[][]::new);
    }
}
