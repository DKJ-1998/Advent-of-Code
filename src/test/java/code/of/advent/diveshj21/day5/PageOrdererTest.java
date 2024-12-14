package code.of.advent.diveshj21.day5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class PageOrdererTest {

    @Test
    void sum() {
        var rules = Map.of(
            47, Set.of(53, 13, 61, 29),
            97, Set.of(53, 75, 13, 61, 29, 47),
            75, Set.of(53, 29, 61, 13, 47),
            61, Set.of(53, 13, 29),
            29, Set.of(13),
            53, Set.of(29, 13)
        );
        var updates = List.of(
            new ArrayList<>(List.of(75,47,61,53,29)),
            new ArrayList<>(List.of(97,61,53,29,13)),
            new ArrayList<>(List.of(75,29,13)),
            new ArrayList<>(List.of(75,97,47,61,53)),
            new ArrayList<>(List.of(61,13,29)),
            new ArrayList<>(List.of(97,13,75,29,47))
        );
        var pageOrderer = new PageOrderer();
        var actual = pageOrderer.sum(rules, updates);
        assertEquals(143, actual);
    }

    @Test
    void reorderedSum() {
        var rules = Map.of(
            47, Set.of(53, 13, 61, 29),
            97, Set.of(53, 75, 13, 61, 29, 47),
            75, Set.of(53, 29, 61, 13, 47),
            61, Set.of(53, 13, 29),
            29, Set.of(13),
            53, Set.of(29, 13)
        );
        var updates = List.of(
            new ArrayList<>(List.of(75,47,61,53,29)),
            new ArrayList<>(List.of(97,61,53,29,13)),
            new ArrayList<>(List.of(75,29,13)),
            new ArrayList<>(List.of(75,97,47,61,53)),
            new ArrayList<>(List.of(61,13,29)),
            new ArrayList<>(List.of(97,13,75,29,47))
        );
        var pageOrderer = new PageOrderer();
        var actual = pageOrderer.reorderedSum(rules, updates);
        assertEquals(123, actual);
    }
}
