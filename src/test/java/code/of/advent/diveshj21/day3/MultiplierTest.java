package code.of.advent.diveshj21.day3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MultiplierTest {

    @Test
    void multiply() {
        var multiplier = new Multiplier();
        var actual = multiplier.multiply("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))");
        assertEquals(161, actual);
    }

    @Test
    void multiplyWithConditional() {
        var multiplier = new Multiplier();
        var actual = multiplier.multiplyWithConditional("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))");
        assertEquals(48, actual);
    }
}
