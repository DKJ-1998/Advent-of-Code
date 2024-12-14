package code.of.advent.diveshj21.day7;

import java.util.function.BiFunction;

import lombok.Getter;

@Getter
public enum Operator {
    ADD(Long::sum),
    MULTIPLY((a, b) -> a * b),
    CONCATENATE((a, b) -> Long.parseLong(a.toString() + b.toString()));

    private final BiFunction<Long, Long, Long> operation;

    Operator(BiFunction<Long, Long, Long> operation) {
        this.operation = operation;
    }
}
