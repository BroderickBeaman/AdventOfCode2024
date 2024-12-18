package dec18;

import framework.AOCParent;
import framework.utils.Coordinate;
import framework.utils.Direction;
import framework.utils.Grid;

import java.util.*;

public class Dec18 extends AOCParent {
    @Override
    public void part1() {
        List<Coordinate> bytes = InputLoader.loadBytes();
        final int NUM_BYTES_TO_FALL = 1024;
        final int ROWS = 71;
        final int COLS = 71;

        Grid<Boolean> safeGrid = new Grid<>(Boolean.class, ROWS, COLS);
        safeGrid.initWithDefault(true);

        for (int i = 0; i < NUM_BYTES_TO_FALL; i++) {
            safeGrid.set(false, bytes.get(i));
        }

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(ROWS - 1, COLS - 1);

        Integer solution = shortestPathLength(safeGrid, start, end);
        printSolution(solution);
    }

    @Override
    public void part2() {
        List<Coordinate> bytes = InputLoader.loadBytes();
        final int NUM_BYTES_TO_START = 1024;
        final int ROWS = 71;
        final int COLS = 71;

        Grid<Boolean> safeGrid = new Grid<>(Boolean.class, ROWS, COLS);
        safeGrid.initWithDefault(true);

        for (int i = 0; i < NUM_BYTES_TO_START; i++) {
            safeGrid.set(false, bytes.get(i));
        }

        final Coordinate start = new Coordinate(0, 0);
        final Coordinate end = new Coordinate(ROWS - 1, COLS - 1);

        int byteToFall = NUM_BYTES_TO_START;

        safeGrid.set(false, bytes.get(byteToFall));
        while (shortestPathLength(safeGrid, start, end) != null) {
            byteToFall++;
            safeGrid.set(false, bytes.get(byteToFall));
        }

        printSolution(bytes.get(byteToFall));
    }

    private Integer shortestPathLength(Grid<Boolean> safeGrid, final Coordinate start, final Coordinate end) {
        Set<Coordinate> seen = new HashSet<>();
        PriorityQueue<MapState> minHeap = new PriorityQueue<>();
        minHeap.add(new MapState(start, 0));

        while (!minHeap.isEmpty()) {
            MapState current = minHeap.poll();
            if (current.position().equals(end)) {
                return current.depth();
            }

            if (seen.contains(current.position())) {
                continue;
            }

            seen.add(current.position());

            for (Direction direction : Direction.values()) {
                Coordinate next = current.position().addDirection(direction);
                if (!safeGrid.isInBounds(next) || !safeGrid.get(next)) {
                    continue;
                }
                minHeap.add(new MapState(next, current.depth() + 1));
            }
        }

        return null;
    }
}
