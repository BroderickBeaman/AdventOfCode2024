package dec12;

import framework.AOCParent;
import framework.utils.Coordinate;
import framework.utils.Direction;
import framework.utils.Grid;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Dec12 extends AOCParent {

    private Set<Coordinate> seen;

    @Override
    public void part1() {
        Grid<Character> garden = InputLoader.loadGarden();
        seen = new HashSet<>();

        long solution = 0;

        for (int row = 0; row < garden.rows(); row++) {
            for (int col = 0; col < garden.cols(); col++) {
                Coordinate current = new Coordinate(row, col);
                if (!seen.contains(current)) {
                    solution += traverseRegionPart1(garden, current);
                }
            }
        }

        printSolution(solution);
    }

    private long traverseRegionPart1(Grid<Character> garden, Coordinate location) {
        final Character target = garden.get(location);
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(location);
        long area = 0;
        long perimeter = 0;

        while (queue.peek() != null) {
            Coordinate current = queue.poll();
            if (seen.contains(current)) {
                continue;
            }
            area++;
            seen.add(current);

            for (Coordinate orthogonal : current.orthogonal()) {
                if (!garden.isInBounds(orthogonal)) {
                    perimeter++;
                } else if (!garden.get(orthogonal).equals(target)) {
                    perimeter++;
                } else {
                    queue.add(orthogonal);
                }
            }
        }

        return area * perimeter;
    }

    @Override
    public void part2() {
        Grid<Character> garden = InputLoader.loadGarden();
        seen = new HashSet<>();

        Set<Region> regions = new HashSet<>();

        // Generate regions
        for (int row = 0; row < garden.rows(); row++) {
            for (int col = 0; col < garden.cols(); col++) {
                Coordinate current = new Coordinate(row, col);
                if (!seen.contains(current)) {
                    regions.add(traverseRegionPart2(garden, current));
                }
            }
        }

        long solution = regions.stream().mapToLong(Region::price).sum();

        printSolution(solution);
    }

    public Region traverseRegionPart2(Grid<Character> garden, Coordinate location) {
        final Character target = garden.get(location);
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(location);
        Set<Coordinate> coordinatesInRegion = new HashSet<>();
        Set<Edge> edges = new HashSet<>();

        while (queue.peek() != null) {
            Coordinate current = queue.poll();
            if (seen.contains(current)) {
                continue;
            }
            seen.add(current);
            coordinatesInRegion.add(current);

            for (Direction direction : Direction.values()) {
                Coordinate coordinateInDirection = current.addCoordinate(direction.toCoordinate());
                if (!garden.isInBounds(coordinateInDirection)) {
                    edges.add(new Edge(Set.of(current, coordinateInDirection), direction));
                } else if (!garden.get(coordinateInDirection).equals(target)) {
                    edges.add(new Edge(Set.of(current, coordinateInDirection), direction));
                } else {
                    queue.add(coordinateInDirection);
                }
            }
        }

        return new Region(coordinatesInRegion, edges);
    }
}
