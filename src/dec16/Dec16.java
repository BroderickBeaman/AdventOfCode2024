package dec16;

import framework.AOCParent;
import framework.utils.Coordinate;
import framework.utils.Direction;
import framework.utils.Grid;

import java.util.*;

public class Dec16 extends AOCParent {

    @Override
    public void part1() {
        Grid<Maze> maze = InputLoader.loadMaze();
        printSolution(computeScoreP1(maze));
    }

    private long computeScoreP1(Grid<Maze> maze) {
        PriorityQueue<Node> nodeMinHeap = new PriorityQueue<>();
        Map<MazePosition, Long> minValues = new HashMap<>();
        Set<MazePosition> explored = new HashSet<>();

        Coordinate startLocation = maze.findValue(Maze.SOURCE).getFirst();
        Direction startDirection = Direction.E;
        MazePosition startPosition = new MazePosition(startLocation, startDirection);

        nodeMinHeap.add(new Node(startPosition, 0L));
        minValues.put(startPosition, 0L);

        while(!nodeMinHeap.isEmpty()) {
            Node node = nodeMinHeap.poll();

            if (explored.contains(node.position())) {
                continue;
            }

            explored.add(node.position());

            Coordinate currentLocation = node.position().location();

            if (maze.get(currentLocation).equals(Maze.TARGET)) {
                return node.score();
            }

            Direction currentFacing = node.position().facing();
            Coordinate nextInDirection = currentLocation.addDirection(currentFacing);
            MazePosition newPosition = new MazePosition(nextInDirection, currentFacing);
            if (!explored.contains(newPosition)) {
                if (!maze.get(nextInDirection).equals(Maze.WALL)) {
                    long newScore = node.score() + 1;
                    minValues.computeIfAbsent(newPosition, key -> newScore);
                    if (newScore <= minValues.get(newPosition)) {
                        minValues.put(newPosition, newScore);
                        nodeMinHeap.add(new Node(newPosition, newScore));
                    }
                }
            }

            for (Direction direction : currentFacing.ninetyDegrees()) {
                newPosition = new MazePosition(currentLocation, direction);
                if (!explored.contains(newPosition)) {
                    long newScore = node.score() + 1000;
                    minValues.computeIfAbsent(newPosition, key -> newScore);
                    if (newScore <= minValues.get(newPosition)) {
                        minValues.put(newPosition, newScore);
                        nodeMinHeap.add(new Node(newPosition, newScore));
                    }
                }
            }

        }

        throw new RuntimeException("Didn't find maze end");
    }

    @Override
    public void part2() {
        Grid<Maze> maze = InputLoader.loadMaze();
        printSolution(computeScoreP2(maze));
    }

    private long computeScoreP2(Grid<Maze> maze) {
        PriorityQueue<Node> nodeMinHeap = new PriorityQueue<>();
        Map<MazePosition, Long> minValues = new HashMap<>();
        Set<MazePosition> explored = new HashSet<>();
        Map<Coordinate, Set<Coordinate>> connectedLocations = new HashMap<>();

        Coordinate startLocation = maze.findValue(Maze.SOURCE).getFirst();
        Direction startDirection = Direction.E;
        MazePosition startPosition = new MazePosition(startLocation, startDirection);

        nodeMinHeap.add(new Node(startPosition, 0L));
        minValues.put(startPosition, 0L);

        while(!nodeMinHeap.isEmpty()) {
            Node node = nodeMinHeap.poll();

            if (explored.contains(node.position())) {
                continue;
            }

            explored.add(node.position());

            Coordinate currentLocation = node.position().location();

            if (maze.get(currentLocation).equals(Maze.TARGET)) {
                break;
            }

            Direction currentFacing = node.position().facing();
            Coordinate nextInDirection = currentLocation.addDirection(currentFacing);
            MazePosition newPosition = new MazePosition(nextInDirection, currentFacing);
            if (!explored.contains(newPosition)) {
                if (!maze.get(nextInDirection).equals(Maze.WALL)) {
                    Long newScore = node.score() + 1;
                    minValues.computeIfAbsent(newPosition, key -> newScore);
                    if (newScore.equals(minValues.get(newPosition))) {
                        minValues.put(newPosition, newScore);
                        nodeMinHeap.add(new Node(newPosition, newScore));
                        connectedLocations.computeIfAbsent(nextInDirection, key -> new HashSet<>());
                        connectedLocations.get(nextInDirection).add(currentLocation);
                    } else if (newScore < minValues.get(newPosition)) {
                        minValues.put(newPosition, newScore);
                        nodeMinHeap.add(new Node(newPosition, newScore));
                        connectedLocations.put(nextInDirection, new HashSet<>());
                        connectedLocations.get(nextInDirection).add(currentLocation);
                    }
                }
            }

            for (Direction direction : currentFacing.ninetyDegrees()) {
                newPosition = new MazePosition(currentLocation, direction);
                if (!explored.contains(newPosition)) {
                    long newScore = node.score() + 1000;
                    minValues.computeIfAbsent(newPosition, key -> newScore);
                    if (newScore <= minValues.get(newPosition)) {
                        minValues.put(newPosition, newScore);
                        nodeMinHeap.add(new Node(newPosition, newScore));
                    }
                }
            }

        }

        Coordinate targetCoordinate = maze.findValue(Maze.TARGET).getFirst();
        Set<Coordinate> visited = new HashSet<>();
        traversePaths(connectedLocations, targetCoordinate, visited);

        return visited.size();
    }

    private void traversePaths(Map<Coordinate, Set<Coordinate>> paths, Coordinate current, Set<Coordinate> visited) {
        visited.add(current);
        if (paths.containsKey(current)) {
            for (Coordinate next : paths.get(current)) {
                if (!visited.contains(next))
                traversePaths(paths, next, visited);
            }
        }
    }
}
