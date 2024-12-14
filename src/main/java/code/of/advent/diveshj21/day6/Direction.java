package code.of.advent.diveshj21.day6;

import java.util.function.Function;

import lombok.Getter;

@Getter
public enum Direction {
    UP(i -> i, j -> j - 1, '^'),
    RIGHT(i -> i + 1, j -> j, '>'),
    DOWN(i -> i, j -> j + 1, 'v'),
    LEFT(i -> i - 1, j -> j, '<');

    private final Function<Integer, Integer> iChange;
    private final Function<Integer, Integer> jChange;
    private final char symbol;

    Direction(Function<Integer, Integer> iChange, Function<Integer, Integer> jChange, char symbol) {
        this.iChange = iChange;
        this.jChange = jChange;
        this.symbol = symbol;
    }

}
