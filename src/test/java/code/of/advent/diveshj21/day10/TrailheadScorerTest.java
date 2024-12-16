package code.of.advent.diveshj21.day10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TrailheadScorerTest {

    @ParameterizedTest
    @MethodSource
    void getTrailheadScore(int[][] map, int expected) {
        var trailheadFinder = new TrailheadScorer(map);
        var actual = trailheadFinder.getTrailheadScore(HashSet::new);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getTrailheadScore() {
        var map1 = convertTextBlockToMap(
            """
9990999
9991999
9992999
6543456
7000007
8000008
9000009
                """);
        var map2 = convertTextBlockToMap(
            """
8890889
8881898
8882887
6543456
7650987
8760000
9870000
                """);
        var map3 = convertTextBlockToMap(
            """
1011911
2111811
3111711
4567654
1118113
1119112
1111101
                """);
        var map4 = convertTextBlockToMap(
            """
89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732
                """);
        return Stream.of(
            Arguments.of(map1, 2),
            Arguments.of(map2, 4),
            Arguments.of(map3, 3),
            Arguments.of(map4, 36)
        );
    }

    @ParameterizedTest
    @MethodSource
    void findAllTrailheadScores(int[][] map, int expected) {
        var trailheadFinder = new TrailheadScorer(map);
        var actual = trailheadFinder.getTrailheadScore(ArrayList::new);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> findAllTrailheadScores() {
        var map1 = convertTextBlockToMap(
            """
9999909
9943219
9959929
9965439
0070040
0087650
0090000
                """);
        var map2 = convertTextBlockToMap(
            """
6690669
6661798
0062637
6543456
7650987
8760000
9870000
                """);
        var map3 = convertTextBlockToMap(
            """
012345
123456
234567
345678
406789
567890
                """);
        var map4 = convertTextBlockToMap(
            """
89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732
                """);
        return Stream.of(
            Arguments.of(map1, 3),
            Arguments.of(map2, 13),
            Arguments.of(map3, 227),
            Arguments.of(map4, 81)
        );
    }

    private static int[][] convertTextBlockToMap(String textBlock) {
        return Arrays.stream(textBlock.split("\n"))
            .map(line -> Arrays.stream(line.split(""))
                .mapToInt(Integer::parseInt)
                .toArray())
            .toArray(int[][]::new);
    }
}