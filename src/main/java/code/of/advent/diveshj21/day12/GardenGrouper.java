package code.of.advent.diveshj21.day12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GardenGrouper {
    private static final Logger logger = LoggerFactory.getLogger(GardenGrouper.class);

    private final char[][] map;

    public int getPrice() {
        var coordinatesAdded = new HashSet<Coordinate>();
        var price = 0;
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                if (!coordinatesAdded.contains(new Coordinate(i, j))) {
                    logger.info("Checking ({}, {}), region \"{}\"", i, j, map[j][i]);
                    coordinatesAdded.add(new Coordinate(i, j));

                    var region = map[j][i];
                    var area = 1;
                    var perimeter = 0;

                    var newCoordinatesToCheck = new HashSet<>(Set.of(new Coordinate(i, j)));

                    while (!newCoordinatesToCheck.isEmpty()) {
                        var coordinatesToCheck = newCoordinatesToCheck;
                        newCoordinatesToCheck = new HashSet<>();
                        for (var coordinateToCheck : coordinatesToCheck) {
                            for (var direction : DirectionChange.values()) {
                                var adjacentCoordinate = new Coordinate(direction.getIChange().apply(coordinateToCheck.i()), direction.getJChange().apply(coordinateToCheck.j()));
                                if (coordinateInMap(adjacentCoordinate)) {
                                    var regionOfAdjacentCoordinate = map[adjacentCoordinate.j()][adjacentCoordinate.i()];
                                    if (regionOfAdjacentCoordinate == region) {
                                        if (!coordinatesAdded.contains(adjacentCoordinate)) {
                                            area++;
                                            coordinatesAdded.add(adjacentCoordinate);
                                            newCoordinatesToCheck.add(adjacentCoordinate);
                                        }
                                    } else {
                                        perimeter++;
                                    }
                                } else {
                                    perimeter++;
                                }
                            }
                        }
                    }

                    logger.info("Area = {}, Perimeter = {}", area, perimeter);

                    price += area * perimeter;
                }
            }
        }
        return price;
    }

    private boolean coordinateInMap(Coordinate coordinate) {
        return coordinate.i() >= 0
            && coordinate.i() < map[0].length
            && coordinate.j() >= 0
            && coordinate.j() < map.length;
    }

    // broken
    public int getDiscountedPrice() {
        var coordinatesAdded = new HashSet<Coordinate>();
        var price = 0;
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                if (!coordinatesAdded.contains(new Coordinate(i, j))) {
                    logger.info("Checking ({}, {}), region \"{}\"", i, j, map[j][i]);
                    coordinatesAdded.add(new Coordinate(i, j));

                    var region = map[j][i];
                    var area = 1;
                    var perimeter = 0;

                    var newCoordinatesToCheck = new HashSet<>(Set.of(new Coordinate(i, j)));
                    var fencesAroundCoordinate = new HashMap<Coordinate, Set<DirectionChange>>();

                    while (!newCoordinatesToCheck.isEmpty()) {
                        var coordinatesToCheck = newCoordinatesToCheck;
                        newCoordinatesToCheck = new HashSet<>();
                        for (var coordinateToCheck : coordinatesToCheck) {
                            logger.info("  Checking {}", coordinateToCheck);
                            var fencesAdded = new HashSet<DirectionChange>();
                            for (var direction : DirectionChange.values()) {
                                var adjacentCoordinate = new Coordinate(direction.getIChange().apply(coordinateToCheck.i()), direction.getJChange().apply(coordinateToCheck.j()));
                                if (coordinateInMap(adjacentCoordinate)) {
                                    var regionOfAdjacentCoordinate = map[adjacentCoordinate.j()][adjacentCoordinate.i()];
                                    if (regionOfAdjacentCoordinate == region) {
                                        if (!coordinatesAdded.contains(adjacentCoordinate)) {
                                            area++;
                                            coordinatesAdded.add(adjacentCoordinate);
                                            newCoordinatesToCheck.add(adjacentCoordinate);
                                        }
                                    } else {
                                        var fenceAddsToPerimeter = true;
                                        for (var directionChangeToCheckFences : directionChangesToCheck(direction)) {
                                            var adjacentCoordinateToCheckFence = new Coordinate(directionChangeToCheckFences.getIChange().apply(coordinateToCheck.i()), directionChangeToCheckFences.getJChange().apply(coordinateToCheck.j()));
                                            logger.info("    Checking {} fence for {}", direction, adjacentCoordinateToCheckFence);
                                            if (fencesAroundCoordinate.containsKey(adjacentCoordinateToCheckFence)) {
                                                logger.info("    Fences: {}", fencesAroundCoordinate.get(adjacentCoordinateToCheckFence));
                                            }
                                            if (fencesAroundCoordinate.containsKey(adjacentCoordinateToCheckFence) && fencesAroundCoordinate.get(adjacentCoordinateToCheckFence).contains(direction)) {
                                                fenceAddsToPerimeter = false;
                                                break;
                                            }
                                        }
                                        if (fenceAddsToPerimeter) {
                                            perimeter++;
                                            logger.info("    Fence increases price");
                                        } else {
                                            logger.info("    Fence already added");
                                        }
                                        fencesAdded.add(direction);
                                    }
                                } else {
                                    var fenceAddsToPerimeter = true;
                                    for (var directionChangeToCheckFences : directionChangesToCheck(direction)) {
                                        var adjacentCoordinateToCheckFence = new Coordinate(directionChangeToCheckFences.getIChange().apply(coordinateToCheck.i()), directionChangeToCheckFences.getJChange().apply(coordinateToCheck.j()));
                                        logger.info("    Checking {} fence for {}", direction, adjacentCoordinateToCheckFence);
                                        if (fencesAroundCoordinate.containsKey(adjacentCoordinateToCheckFence)) {
                                            logger.info("    Fences: {}", fencesAroundCoordinate.get(adjacentCoordinateToCheckFence));
                                        }
                                        if (fencesAroundCoordinate.containsKey(adjacentCoordinateToCheckFence) && fencesAroundCoordinate.get(adjacentCoordinateToCheckFence).contains(direction)) {
                                            fenceAddsToPerimeter = false;
                                            break;
                                        }
                                    }
                                    if (fenceAddsToPerimeter) {
                                        perimeter++;
                                        logger.info("    Fence increases price");
                                    } else {
                                        logger.info("    Fence already added");
                                    }
                                    fencesAdded.add(direction);
                                }
                            }
                            fencesAroundCoordinate.put(coordinateToCheck, fencesAdded);
                        }
                    }

                    logger.info("Area = {}, Perimeter = {}", area, perimeter);

                    price += area * perimeter;
                }
            }
        }
        return price;
    }

    private Set<DirectionChange> directionChangesToCheck(DirectionChange directionChange) {
        var directionChanges = new HashSet<DirectionChange>();
        switch (directionChange) {
            case UP:
                directionChanges.add(DirectionChange.LEFT);
                directionChanges.add(DirectionChange.RIGHT);
                break;
            case RIGHT:
                directionChanges.add(DirectionChange.UP);
                directionChanges.add(DirectionChange.DOWN);
                break;
            case LEFT:
                directionChanges.add(DirectionChange.UP);
                directionChanges.add(DirectionChange.DOWN);
                break;
            case DOWN:
                directionChanges.add(DirectionChange.LEFT);
                directionChanges.add(DirectionChange.RIGHT);
                break;
        }
        return directionChanges;
    }
}
