package code.of.advent.diveshj21.day10;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Supplier;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrailheadScorer {

    private int[][] map;

    public int getTrailheadScore(Supplier<Collection<Coordinate>> positionStoreSupplier) {
        var score = 0;
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[0].length; i++) {
                if (map[j][i] == 0) {
                    score += findTrailheads(i, j, positionStoreSupplier);
                }
            }
        }

        return score;
    }

    public int findTrailheads(int i, int j, Supplier<Collection<Coordinate>> positionStoreSupplier) {
        var currentValuePositions = positionStoreSupplier.get();
        currentValuePositions.add(new Coordinate(i, j));
        var nextValuePositions = positionStoreSupplier.get();
        for (int currentValue = 0; currentValue <= 8; currentValue++) {
            for (var position : currentValuePositions) {
                for (var directionChange : DirectionChange.values()) {
                    var nextI = directionChange.getIChange().apply(position.i());
                    var nextJ = directionChange.getJChange().apply(position.j());
                    if (nextCoordinateInMap(nextI, nextJ)) {
                        if (map[nextJ][nextI] == currentValue + 1) {
                            nextValuePositions.add(new Coordinate(nextI, nextJ));
                        }
                    }
                }
            }
            currentValuePositions = nextValuePositions;
            nextValuePositions = positionStoreSupplier.get();
        }
        return currentValuePositions.size();
    }

    private boolean nextCoordinateInMap(int i, int j) {
        return i >= 0
            && i < map[0].length
            && j >= 0
            && j < map.length;
    }
}
