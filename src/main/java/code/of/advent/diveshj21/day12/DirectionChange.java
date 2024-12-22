package code.of.advent.diveshj21.day12;

import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DirectionChange {
    UP(i -> i, j -> j - 1),
    RIGHT(i -> i + 1, j -> j),
    LEFT(i -> i - 1, j -> j),
    DOWN(i -> i, j -> j + 1);

    private final Function<Integer, Integer> iChange;
    private final Function<Integer, Integer> jChange;


}
