package code.of.advent.diveshj21.day4;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wordsearcher {
    private static final Logger logger = LoggerFactory.getLogger(Wordsearcher.class);

    private final List<DirectionChange> directionChanges = List.of(
        new DirectionChange(x -> x + 1, y -> y + 1),
        new DirectionChange(x -> x, y -> y + 1),
        new DirectionChange(x -> x - 1, y -> y + 1),
        new DirectionChange(x -> x + 1, y -> y - 1),
        new DirectionChange(x -> x, y -> y - 1),
        new DirectionChange(x -> x - 1, y -> y - 1),
        new DirectionChange(x -> x + 1, y -> y),
        new DirectionChange(x -> x - 1, y -> y)
    );

    public int search(char[][] wordsearch) {
        var count = 0;
        for (int j = 0; j < wordsearch.length; j++) {
            for (int i = 0; i < wordsearch[j].length; i++) {
                for (var directionChange : directionChanges) {
                    if (checkInDirection(wordsearch, i, j, 'X', directionChange.iChange(), directionChange.jChange())) {
                        count++;
//                        logger.info("Found word at {}, {}", i, j);
                    }
                }
            }
        }
        return count;
    }

    private boolean checkInDirection(char[][] wordsearch, int i, int j, char letterToCheck, Function<Integer, Integer> iChange, Function<Integer, Integer> jChange) {
        try {
            if (wordsearch[j][i] == letterToCheck) {
                Optional<Character> optionalNextLetterToCheck = switch (letterToCheck) {
                    case 'X' -> Optional.of('M');
                    case 'M' -> Optional.of('A');
                    case 'A' -> Optional.of('S');
                    default -> Optional.empty();
                };
                if (optionalNextLetterToCheck.isEmpty()) {
                    return true;
                }
                var nextLetterToCheck = optionalNextLetterToCheck.get();
                return checkInDirection(wordsearch, iChange.apply(i), jChange.apply(j), nextLetterToCheck, iChange, jChange);
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public int searchMASinX(char[][] wordsearch) {
        var count = 0;
        for (int j = 0; j < wordsearch.length; j++) {
            for (int i = 0; i < wordsearch[j].length; i++) {
                if (wordsearch[j][i] == 'A' && checkAroundA(wordsearch, i, j)) {
                    count++;
//                    logger.info("Found MAS at {}, {}", i, j);
                }
            }
        }
        return count;
    }

    private boolean checkAroundA(char[][] wordsearch, int i, int j) {
        try {
            var upperLeft = wordsearch[j - 1][i - 1];
            var upperRight = wordsearch[j - 1][i + 1];
            var lowerRight = wordsearch[j + 1][i + 1];
            var lowerLeft = wordsearch[j + 1][i - 1];
            var surrounding = List.of(upperLeft, upperRight, lowerRight, lowerLeft);
            if (surrounding.equals(List.of('M', 'M', 'S', 'S'))) {
                return true;
            }
            if (surrounding.equals(List.of('S', 'M', 'M', 'S'))) {
                return true;
            }
            if (surrounding.equals(List.of('S', 'S', 'M', 'M'))) {
                return true;
            }
            if (surrounding.equals(List.of('M', 'S', 'S', 'M'))) {
                return true;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
