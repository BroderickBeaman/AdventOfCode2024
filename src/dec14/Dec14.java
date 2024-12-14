package dec14;

import framework.AOCParent;
import framework.utils.Coordinate;
import framework.utils.Grid;

import java.util.List;

public class Dec14 extends AOCParent {
    @Override
    public void part1() {
        List<Robot> robots = InputLoader.loadRobots();

        final int rows = 103;
        final int cols = 101;
        final long numCycles = 100L;

        List<Coordinate> newPositions = robots.stream().map(robot -> robot.computeNewPosition(numCycles, rows, cols)).toList();

        long quad1 = newPositions.stream().filter(pos -> pos.row() < (rows / 2) && pos.col() < (cols / 2)).count();
        long quad2 = newPositions.stream().filter(pos -> pos.row() < (rows / 2) && pos.col() > (cols / 2)).count();
        long quad3 = newPositions.stream().filter(pos -> pos.row() > (rows / 2) && pos.col() < (cols / 2)).count();
        long quad4 = newPositions.stream().filter(pos -> pos.row() > (rows / 2) && pos.col() > (cols / 2)).count();
        printSolution(quad1 * quad2 * quad3 * quad4);
    }

    @Override
    public void part2() {
        List<Robot> robots = InputLoader.loadRobots();

        final int rows = 103;
        final int cols = 101;

        // Arbitrarily chosen solution from part 1
        long minSafetyFactor = 232589280L;
        long minSafetyFactorCycle = 0L;

        for (long numCycles = 1; numCycles <= 10000; numCycles++) {
            long finalNumCycles = numCycles;
            List<Coordinate> newPositions = robots.stream().map(robot -> robot.computeNewPosition(finalNumCycles, rows, cols)).toList();

            long quad1 = newPositions.stream().filter(pos -> pos.row() < (rows / 2) && pos.col() < (cols / 2)).count();
            long quad2 = newPositions.stream().filter(pos -> pos.row() < (rows / 2) && pos.col() > (cols / 2)).count();
            long quad3 = newPositions.stream().filter(pos -> pos.row() > (rows / 2) && pos.col() < (cols / 2)).count();
            long quad4 = newPositions.stream().filter(pos -> pos.row() > (rows / 2) && pos.col() > (cols / 2)).count();

            long safetyFactor = quad1 * quad2 * quad3 * quad4;
            if (safetyFactor < minSafetyFactor) {
                minSafetyFactor = safetyFactor;
                minSafetyFactorCycle = finalNumCycles;
            }
        }

        Grid<Character> characterGrid = new Grid<>(Character.class, rows, cols);
        characterGrid.initWithDefault('.');

        long finalMinSafetyFactorCycle = minSafetyFactorCycle;
        List<Coordinate> newPositions = robots.stream().map(robot -> robot.computeNewPosition(finalMinSafetyFactorCycle, rows, cols)).toList();

        for (Coordinate coordinate : newPositions) {
            characterGrid.set('#', coordinate);
        }

        System.out.println(characterGrid);

        printSolution(finalMinSafetyFactorCycle);
    }
}
