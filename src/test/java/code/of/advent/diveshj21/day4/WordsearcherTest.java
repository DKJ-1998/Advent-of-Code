package code.of.advent.diveshj21.day4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class WordsearcherTest {

    @Test
    void search() {
        var wordsearcher = new Wordsearcher();
        var input = """
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX
            """;
        var wordsearch = Arrays.stream(input.split("\n")).map(String::toCharArray).toArray(char[][]::new);
        var actual = wordsearcher.search(wordsearch);
        assertEquals(18, actual);
    }

    @Test
    void searchMASinX() {
        var wordsearcher = new Wordsearcher();
        var input = """
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX
            """;
        var wordsearch = Arrays.stream(input.split("\n")).map(String::toCharArray).toArray(char[][]::new);
        var actual = wordsearcher.searchMASinX(wordsearch);
        assertEquals(9, actual);
    }
}
