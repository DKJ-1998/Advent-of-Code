package code.of.advent.diveshj21.day6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class RouterTest {

    @Test
    void routeGuard() {
        var router = new Router();
        var input = """
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
            """;
        var map = Arrays.stream(input.split("\n")).map(String::toCharArray).toArray(char[][]::new);

        var actual = router.routeGuard(map);
        assertEquals(41, actual);
    }

    @Test
    void blockGuard() {
        var router = new Router();
        var input = """
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
            """;
        var map = Arrays.stream(input.split("\n")).map(String::toCharArray).toArray(char[][]::new);

        var actual = router.blockGuard(map);
        assertEquals(6, actual);
    }
}