package dec08;

import framework.AOCParent;

import java.util.List;
import java.util.Map;

public class Dec08 extends AOCParent {

    private long solution;
    private Boolean[][] grid;

    @Override
    public void part1() {
        Map<Character, List<Coordinate>> antennaMap = InputLoader.loadAntennaMap();
        grid = InputLoader.loadAntinodeGrid();
        final int rows = grid.length;
        final int cols = grid[0].length;

        solution = 0;

        for (Map.Entry<Character, List<Coordinate>> entry : antennaMap.entrySet()) {
            List<Coordinate> coordinates = entry.getValue();

            // Can't create antinodes where there is only one antenna
            if (coordinates.size() == 1) {
                continue;
            }

            // Generate antinodes for every pair
            for (int i = 0; i < coordinates.size() - 1; i++) {
                for (int j = i + 1; j < coordinates.size(); j++) {
                    Coordinate antenna1 = coordinates.get(i);
                    Coordinate antenna2 = coordinates.get(j);
                    Coordinate antinode1 = antenna1.createAntinode(antenna2);
                    Coordinate antinode2 = antenna2.createAntinode(antenna1);

                    if (antinode1.isInBounds(rows, cols) && !grid[antinode1.row()][antinode1.col()]) {
                        grid[antinode1.row()][antinode1.col()] = true;
                        solution++;
                    }

                    if (antinode2.isInBounds(rows, cols) && !grid[antinode2.row()][antinode2.col()]) {
                        grid[antinode2.row()][antinode2.col()] = true;
                        solution++;
                    }
                }
            }
        }
        printSolution(solution);
    }

    @Override
    public void part2() {
        Map<Character, List<Coordinate>> antennaMap = InputLoader.loadAntennaMap();
        grid = InputLoader.loadAntinodeGrid();
        final int rows = grid.length;
        final int cols = grid[0].length;

        solution = 0;

        for (Map.Entry<Character, List<Coordinate>> entry : antennaMap.entrySet()) {
            List<Coordinate> coordinates = entry.getValue();
            for (Coordinate antenna : coordinates) {
                if (!grid[antenna.row()][antenna.col()]) {
                    grid[antenna.row()][antenna.col()] = true;
                    solution++;
                }
            }

            // Can't create antinodes where there is only one antenna
            if (coordinates.size() == 1) {
                continue;
            }

            // Generate antinodes for every pair
            for (int i = 0; i < coordinates.size() - 1; i++) {
                for (int j = i + 1; j < coordinates.size(); j++) {
                    Coordinate antenna1 = coordinates.get(i);
                    Coordinate antenna2 = coordinates.get(j);

                    Coordinate distance = antenna1.distance(antenna2);
                    Coordinate maybeAntinode = antenna1.createAntinode(antenna2);
                    while (maybeAntinode.isInBounds(rows, cols)) {
                        if (!grid[maybeAntinode.row()][maybeAntinode.col()]) {
                            grid[maybeAntinode.row()][maybeAntinode.col()] = true;
                            solution++;
                        }
                        maybeAntinode = new Coordinate(
                                maybeAntinode.row() - distance.row(),
                                maybeAntinode.col() - distance.col()
                        );
                    }

                    distance = antenna2.distance(antenna1);
                    maybeAntinode = antenna2.createAntinode(antenna1);
                    while (maybeAntinode.isInBounds(rows, cols)) {
                        if (!grid[maybeAntinode.row()][maybeAntinode.col()]) {
                            grid[maybeAntinode.row()][maybeAntinode.col()] = true;
                            solution++;
                        }
                        maybeAntinode = new Coordinate(
                                maybeAntinode.row() - distance.row(),
                                maybeAntinode.col() - distance.col()
                        );
                    }
                }
            }
        }
        printSolution(solution);
    }

    private void incrementSolution(int row, int col) {
        if (!grid[row][col]) {
            grid[row][col] = true;
            solution++;
        }
    }

}
