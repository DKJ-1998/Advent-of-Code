package code.of.advent.diveshj21.day6;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Router {
    private static final Logger logger = LoggerFactory.getLogger(Router.class);

    public int routeGuard(char[][] map) {
        var guardState = getStart(map);
        var positionsVisited = 1;
        var inMap = true;
        while (inMap) {
            if (map[guardState.getJ()][guardState.getI()] == '.') {
                positionsVisited++;
                map[guardState.getJ()][guardState.getI()] = 'X';
            }
//            logger.info("Map");
//            Arrays.stream(map).forEach(line -> logger.info("{}", line));
            try {
                if (map[guardState.checkNextJ()][guardState.checkNextI()] == '#') {
                    guardState.turn();
                } else {
                    guardState.move();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                inMap = false;
            }
        }
        return positionsVisited;
    }

    private GuardState getStart(char[][] map) {
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                if (map[j][i] == '^') {
                    return new GuardState(i, j, Direction.UP);
                }
            }
        }
        throw new RuntimeException("Not found security guard (^) in map");
    }

//    public int blockGuard(char[][] map) {
//        var possibleBlocks = 0;
//        var guardState = getStart(map);
//        var inMap = true;
//        var previousDirections = new HashMap<Coordinate, Set<Direction>>();
//        previousDirections.put(new Coordinate(guardState.getI(), guardState.getJ()), new HashSet<>(Set.of(guardState.getDirection())));
//        while (inMap) {
////            logger.info("Check position [{}, {}]", guardState.getI(), guardState.getJ());
//            if (map[guardState.getJ()][guardState.getI()] == '.') {
//                map[guardState.getJ()][guardState.getI()] = guardState.getDirection().getSymbol();
////                map[guardState.getJ()][guardState.getI()] = 'X';
//            }
//            if (previousDirections.containsKey(new Coordinate(guardState.getI(), guardState.getJ()))) {
//                previousDirections.get(new Coordinate(guardState.getI(), guardState.getJ())).add(guardState.getDirection());
//            } else {
//                previousDirections.put(new Coordinate(guardState.getI(), guardState.getJ()), new HashSet<>(Set.of(guardState.getDirection())));
//            }
//            if (checkWhatHappensIfTurnedAndMovedOn(previousDirections, guardState, map)) {
//                if (map[guardState.checkNextJ()][guardState.checkNextI()] != '^') {
////                    logger.info("Blocked at [{}, {}]", guardState.getI(), guardState.getJ());
//                    possibleBlocks++;
//                }
//            }
//            try {
//                if (map[guardState.checkNextJ()][guardState.checkNextI()] == '#') {
//                    guardState.turn();
//                } else {
//                    guardState.move();
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//                inMap = false;
//            }
//        }
////        logger.info("{}", previousDirections);
////        logger.info("Map");
////        logger.info("  [0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]");
////        IntStream.range(0, map.length).forEach(j -> logger.info("{} {}", j, map[j]));
//        return possibleBlocks;
//    }
//
//    private boolean checkWhatHappensIfTurnedAndMovedOn(HashMap<Coordinate, Set<Direction>> previousDirections, GuardState guardState, char[][] map) {
//        var newGuardState = new GuardState(guardState.getI(), guardState.getJ(), guardState.checkNextDirection());
//        while (map[newGuardState.checkNextJ()][newGuardState.checkNextI()] == '#') {
//            newGuardState.turn();
//        }
//        try {
//            while (map[newGuardState.getJ()][newGuardState.getI()] != '#') {
////                logger.info("Does it join a loop at [{}, {}]?", newGuardState.getI(), newGuardState.getJ());
//                var currentPos = new Coordinate(newGuardState.getI(), newGuardState.getJ());
//                if (previousDirections.containsKey(currentPos) && previousDirections.get(currentPos).contains(newGuardState.getDirection())) {
////                    logger.info("Yes");
//                    map[guardState.checkNextJ()][guardState.checkNextI()] = 'O';
//                    return true;
//                }
////                logger.info("No");
//                newGuardState.move();
//            }
//        } catch (ArrayIndexOutOfBoundsException e) {
//            return false;
//        }
//        return false;
//    }

    // brute force
    public int blockGuard(char[][] originalMap) {
        var blocks = 0;
        for (int j = 0; j < originalMap.length; j++) {
            for (int i = 0; i < originalMap[j].length; i++) {
                var map = new char[originalMap.length][originalMap[j].length];
                for (int k = 0; k < originalMap.length; k++) {
                    map[k] = originalMap[k].clone();
                }
                if (map[j][i] == '.') {
                    map[j][i] = 'O';
//                    logger.info("Map");
//                    Arrays.stream(map).forEach(line -> logger.info("{}", line));
                    map[j][i] = '#';
                    if (blockCausesLoop(map)) {
                        blocks++;
                    }
                }
            }
        }
        return blocks;
    }

    private boolean blockCausesLoop(char[][] map) {
        var guardState = getStart(map);
        var previousPositions = new HashSet<PastGuardState>();
        var inMap = true;
        while (inMap) {
//            logger.info("{}", previousPositions);
            if (previousPositions.contains(new PastGuardState(guardState))) {
                return true;
            }
            previousPositions.add(new PastGuardState(guardState));
            if (map[guardState.getJ()][guardState.getI()] == '.') {
                map[guardState.getJ()][guardState.getI()] = 'X';
            }
            try {
                if (map[guardState.checkNextJ()][guardState.checkNextI()] == '#') {
                    guardState.turn();
                } else {
                    guardState.move();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                inMap = false;
            }
        }
        return false;
    }
}
