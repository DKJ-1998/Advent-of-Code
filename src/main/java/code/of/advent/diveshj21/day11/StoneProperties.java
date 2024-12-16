package code.of.advent.diveshj21.day11;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StoneProperties {

    private final List<Long> nextStones;
    private long count;

    public void increaseCount(long increase) {
        count += increase;
    }

    @Override
    public String toString() {
        return String.format("(count:%s, nextStones:{%s})", count, nextStones);
    }
}
