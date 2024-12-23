package code.of.advent.diveshj21.day14;

import java.util.function.Function;

public record DirectionChange(Function<Integer, Integer> iChange, Function<Integer, Integer> jChange) {
}
