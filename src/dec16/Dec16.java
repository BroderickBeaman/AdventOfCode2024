package dec16;

import framework.AOCParent;
import framework.utils.Coordinate;
import framework.utils.Direction;
import framework.utils.Grid;

import java.util.*;
import java.util.stream.Collectors;

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

        Coordinate startLocation = maze.findValue(Maze.SOURCE).getFirst();
        Direction startDirection = Direction.E;
        MazePosition startPosition = new MazePosition(startLocation, startDirection);

        Map<MazePosition, Long> lowestScores = new HashMap<>();
        Map<MazePosition, Set<MazePosition>> backtrackMap = new HashMap<>();

        long bestScore = Long.MAX_VALUE;
        Set<MazePosition> endStates = new HashSet<>();

        nodeMinHeap.add(new NodeP2(startPosition, null,  0L));

        while(!nodeMinHeap.isEmpty()) {
            NodeP2 node = nodeMinHeap.poll();
            MazePosition currentPosition = node.position();

            lowestScores.computeIfAbsent(currentPosition, _ -> node.score());
            if (node.score() > lowestScores.get(currentPosition)) {
                continue;
            }

            Coordinate currentLocation = node.position().location();
            Direction currentFacing = node.position().facing();

            if (maze.get(currentLocation).equals(Maze.TARGET)) {
                // Done finding solutions that reach the end optimally
                if (node.score() > bestScore) {
                    break;
                }
                bestScore = node.score();
                endStates.add(currentPosition);
            }

            Set<NodeP2> nextStates = new HashSet<>();
            nextStates.add(new NodeP2(new MazePosition(currentLocation.addDirection(currentFacing), currentFacing), node.position(), node.score() + 1));
            for (Direction direction : currentFacing.ninetyDegrees()) {
                nextStates.add(new NodeP2(new MazePosition(currentLocation, direction), node.position(), node.score() + 1000));
            }

            for (NodeP2 nextState : nextStates) {
                MazePosition nextPosition = nextState.position();
                Coordinate nextLocation = nextPosition.location();
                if (maze.get(nextLocation).equals(Maze.WALL)) {
                    continue;
                }
                long lowestScore = lowestScores.getOrDefault(nextPosition, Long.MAX_VALUE);
                if (nextState.score() > lowestScore) {
                    continue;
                }
                if (nextState.score() < lowestScore) {
                    backtrackMap.put(nextPosition, new HashSet<>());
                    lowestScores.put(nextPosition, nextState.score());
                }
                backtrackMap.get(nextPosition).add(node.position());
                nodeMinHeap.add(nextState);
            }
        }

        Queue<MazePosition> backtrackQueue = new LinkedList<>(endStates);
        Set<MazePosition> seenNodes = new HashSet<>();
        while (!backtrackQueue.isEmpty()) {
            MazePosition current = backtrackQueue.poll();
            seenNodes.add(current);
            Set<MazePosition> nextPositions = backtrackMap.get(current);
            if (nextPositions != null) {
                for (MazePosition next : nextPositions) {
                    if (!seenNodes.contains(next)) {
                        backtrackQueue.add(next);
                    }
                }
            }
        }

        return (long) seenNodes.stream().map(MazePosition::location).collect(Collectors.toSet()).size();
    }
}
