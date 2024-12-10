package dec10;

import framework.AOCParent;
import framework.utils.Coordinate;
import framework.utils.Direction;
import framework.utils.Grid;

import java.util.*;

public class Dec10 extends AOCParent {

    private Map<Coordinate, Set<Coordinate>> trailEndMap;
    private Map<Coordinate, Long> possibleTrailMap;

    @Override
    public void part1() {
        Grid<Integer> grid = InputLoader.loadTrailMap();
        trailEndMap = new HashMap<>();

        long solution = 0;
        for (Coordinate trailHead : grid.findValue(0)) {
            solution += findTrailEnds(grid, trailHead, 0).size();
        }
        printSolution(solution);
    }

    private Set<Coordinate> findTrailEnds(Grid<Integer> grid, Coordinate current, Integer target) {
        if (!grid.isInBounds(current)) {
            return Set.of();
        }

        Integer intAtLocation = grid.get(current);

        // Found trail end
        if (target.equals(9) && intAtLocation.equals(9)) {
            return Set.of(current);
        }

        if (!target.equals(intAtLocation)) {
            return Set.of();
        }

        if (trailEndMap.containsKey(current)) {
            return trailEndMap.get(current);
        }

        Set<Coordinate> trailEnds = new HashSet<>();
        for (Direction direction : Direction.values()) {
            trailEnds.addAll(findTrailEnds(grid, current.addCoordinate(direction.toCoordinate()), target + 1));
        }

        trailEndMap.put(current, trailEnds);
        return trailEnds;
    }

    @Override
    public void part2() {
        Grid<Integer> grid = InputLoader.loadTrailMap();
        possibleTrailMap = new HashMap<>();

        long solution = 0;
        for (Coordinate trailHead : grid.findValue(0)) {
            solution += findPossibleTrails(grid, trailHead, 0);
        }

        printSolution(solution);
    }

    private Long findPossibleTrails(Grid<Integer> grid, Coordinate current, Integer target) {
        if (!grid.isInBounds(current)) {
            return 0L;
        }

        Integer intAtLocation = grid.get(current);

        // Found trail end
        if (target.equals(9) && intAtLocation.equals(9)) {
            return 1L;
        }

        if (!target.equals(intAtLocation)) {
            return 0L;
        }

        if (possibleTrailMap.containsKey(current)) {
            return possibleTrailMap.get(current);
        }

        Long trails = 0L;
        for (Direction direction : Direction.values()) {
            trails += findPossibleTrails(grid, current.addCoordinate(direction.toCoordinate()), target + 1);
        }

        possibleTrailMap.put(current, trails);
        return trails;
    }
}
