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
        PriorityQueue<NodeP1> nodeMinHeap = new PriorityQueue<>();
        Set<MazePosition> explored = new HashSet<>();

        Coordinate startLocation = maze.findValue(Maze.SOURCE).getFirst();
        Direction startDirection = Direction.E;
        MazePosition startPosition = new MazePosition(startLocation, startDirection);

        nodeMinHeap.add(new NodeP1(startPosition, 0L));

        while(!nodeMinHeap.isEmpty()) {
            NodeP1 node = nodeMinHeap.poll();
            explored.add(node.position());
            Coordinate currentLocation = node.position().location();
            Direction currentFacing = node.position().facing();

            if (maze.get(currentLocation).equals(Maze.TARGET)) {
                return node.score();
            }

            Set<NodeP1> nextStates = new HashSet<>();
            nextStates.add(new NodeP1(new MazePosition(currentLocation.addDirection(currentFacing), currentFacing), node.score() + 1));
            for (Direction direction : currentFacing.ninetyDegrees()) {
                nextStates.add(new NodeP1(new MazePosition(currentLocation, direction), node.score() + 1000));
            }

            for (NodeP1 nextState : nextStates) {
                Coordinate nextLocation = nextState.position().location();
                if (maze.get(nextLocation).equals(Maze.WALL)) {
                    continue;
                }
                if (explored.contains(nextState.position())) {
                    continue;
                }
                nodeMinHeap.add(nextState);
            }
        }

        throw new RuntimeException("Didn't find maze end");
    }

    @Override
    public void part2() {
        Grid<Maze> maze = InputLoader.loadMaze();
        printSolution(computeScoreP2(maze));
    }

    private Long computeScoreP2(Grid<Maze> maze) {
        PriorityQueue<NodeP2> nodeMinHeap = new PriorityQueue<>();
//        Set<MazePosition> explored = new HashSet<>();

        Coordinate startLocation = maze.findValue(Maze.SOURCE).getFirst();
        Direction startDirection = Direction.E;
        MazePosition startPosition = new MazePosition(startLocation, startDirection);
        Map<Coordinate, Set<Coordinate>> backtrackMap = new HashMap<>();
        Map<MazePosition, Long> lowestCostMap = new HashMap<>();

        nodeMinHeap.add(new NodeP2(startPosition, null, 0L));

        MazePosition endPosition = null;

        long bestEndCost = 10000000000000L;

        while(!nodeMinHeap.isEmpty()) {
            NodeP2 node = nodeMinHeap.poll();
            MazePosition currentPosition = node.position();
            lowestCostMap.computeIfAbsent(currentPosition, k -> node.score());
            if (node.score() > lowestCostMap.get(currentPosition)) {
                continue;
            }
            lowestCostMap.put(currentPosition, node.score());

            Coordinate currentLocation = node.position().location();
            Direction currentFacing = node.position().facing();

            if (maze.get(currentLocation).equals(Maze.TARGET)) {
                if (node.score() > bestEndCost) {
                    break;
                }
                bestEndCost = node.score();
            }

            backtrackMap.computeIfAbsent(currentLocation, k -> new HashSet<>());
            if (node.previous() != null && node.previous().location() != currentLocation) {
                backtrackMap.get(currentLocation).add(node.previous().location());
            }


            Set<NodeP2> nextStates = new HashSet<>();
            nextStates.add(new NodeP2(new MazePosition(currentLocation.addDirection(currentFacing), currentFacing), node.position(), node.score() + 1));
            for (Direction direction : currentFacing.ninetyDegrees()) {
                nextStates.add(new NodeP2(new MazePosition(currentLocation, direction), node.position(), node.score() + 1000));
            }

            for (NodeP2 nextState : nextStates) {
                Coordinate nextLocation = nextState.position().location();
                if (maze.get(nextLocation).equals(Maze.WALL)) {
                    continue;
                }
                if (lowestCostMap.containsKey(nextState.position()) && nextState.score() > lowestCostMap.get(nextState.position())) {
                    continue;
                }
                nodeMinHeap.add(nextState);
            }
        }

        Set<Coordinate> visited = new HashSet<>();
        Coordinate endLocation = maze.findValue(Maze.TARGET).getFirst();


        traversePaths(backtrackMap, endLocation, visited);
        return (long) visited.size();
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
