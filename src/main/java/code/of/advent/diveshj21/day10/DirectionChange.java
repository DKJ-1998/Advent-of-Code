package code.of.advent.diveshj21.day10;

import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DirectionChange {
    UP(i -> i, j -> j - 1),
    RIGHT(i -> i + 1, j -> j),
    DOWN(i -> i, j -> j + 1),
    LEFT(i -> i - 1, j -> j);

    private final Function<Integer, Integer> iChange;
    private final Function<Integer, Integer> jChange;
}
