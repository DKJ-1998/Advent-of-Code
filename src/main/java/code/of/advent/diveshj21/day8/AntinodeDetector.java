package code.of.advent.diveshj21.day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AntinodeDetector {
    private static final Logger logger = LoggerFactory.getLogger(AntinodeDetector.class);

    private final char[][] map;

    public int detect() {
        var antinodes = new HashSet<Coordinate>();
        var positions = getPositionsOfStates();
//        logger.info("{}", positions);
        for (var stateInPositions : positions.entrySet()) {
//            logger.info("{}", stateInPositions.getKey());
            var positionsOfState = stateInPositions.getValue();
            for (int a = 0; a < positionsOfState.size(); a++) {
                for (int b = 0; b < positionsOfState.size(); b++) {
                    if (a != b) {
                        var firstNode = positionsOfState.get(a);
                        var secondNode = positionsOfState.get(b);
//                        logger.info("First: {}", firstNode);
//                        logger.info("Second: {}", secondNode);
                        var possibleAntinode = new Coordinate(firstNode.i() - (secondNode.i() - firstNode.i()), firstNode.j() - (secondNode.j() - firstNode.j()));
                        if (inMap(possibleAntinode)) {
                            antinodes.add(possibleAntinode);
//                            logger.info("Valid antinode: {}", possibleAntinode);
                        }
//                        } else {
//                            logger.info("Invalid antinode: {}", possibleAntinode);
//                        }
                    }
                }
            }
        }
        return antinodes.size();
    }

    public int detectManyAntinodes() {
        var antinodes = new HashSet<Coordinate>();
        var positions = getPositionsOfStates();
        for (var stateInPositions : positions.entrySet()) {
            var positionsOfState = stateInPositions.getValue();
            for (int a = 0; a < positionsOfState.size(); a++) {
                for (int b = 0; b < positionsOfState.size(); b++) {
                    if (a != b) {
                        var firstNode = positionsOfState.get(a);
                        var secondNode = positionsOfState.get(b);
                        var iDiff = (secondNode.i() - firstNode.i());
                        var jDiff = (secondNode.j() - firstNode.j());
                        var possibleAntinode = new Coordinate(firstNode.i(), firstNode.j());
                        while (inMap(possibleAntinode)) {
                            antinodes.add(possibleAntinode);
                            possibleAntinode = new Coordinate(possibleAntinode.i() - iDiff, possibleAntinode.j() - jDiff);
                        }
                    }
                }
            }
        }
        return antinodes.size();
    }

    private HashMap<Character, List<Coordinate>> getPositionsOfStates() {
        var positions  = new HashMap<Character, List<Coordinate>>();
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                var stateAtPosition = map[j][i];
                if (stateAtPosition != '.') {
                    if (!positions.containsKey(stateAtPosition)) {
                        positions.put(map[j][i], new ArrayList<>());
                    }
                    positions.get(stateAtPosition).add(new Coordinate(i, j));
                }
            }
        }
        return positions;
    }

    private boolean inMap(Coordinate coordinate) {
        var mapHeight = map.length;
        var mapWidth = map[0].length;
        return coordinate.i() >= 0
            && coordinate.i() < mapWidth
            && coordinate.j() >= 0
            && coordinate.j() < mapHeight;
    }
}
