package dec20;

import framework.AOCParent;
import framework.utils.Coordinate;
import framework.utils.Direction;
import framework.utils.Grid;

import java.util.HashMap;
import java.util.Map;

public class Dec20 extends AOCParent {
    @Override
    public void part1() {
        Grid<MazeElement> maze = InputLoader.loadMaze();

        Coordinate start = maze.findValue(MazeElement.START).getFirst();
        Coordinate end = maze.findValue(MazeElement.END).getFirst();

        Map<Coordinate, Long> secondsAtLocation = buildSecondsAtLocation(start, end, maze);
        final Long targetSavings = 100L;
        Long numCheats = getNumCheatsP1(secondsAtLocation, targetSavings);

        printSolution(numCheats);
    }

    private Map<Coordinate, Long> buildSecondsAtLocation(Coordinate start, Coordinate end, Grid<MazeElement> maze) {
        Map<Coordinate, Long> secondsAtLocation = new HashMap<>();
        Coordinate current = start;
        Long seconds = 0L;
        secondsAtLocation.put(start, 0L);

        while (!current.equals(end)) {
            for (Direction direction : Direction.values()) {
                Coordinate temp = current.addDirection(direction);
                if (maze.isInBounds(temp) && !maze.get(temp).equals(MazeElement.WALL) && !secondsAtLocation.containsKey(temp)) {
                    current = temp;
                    seconds++;
                    secondsAtLocation.put(current, seconds);
                    break;
                }
            }
        }

        return secondsAtLocation;
    }

    private static Long getNumCheatsP1(Map<Coordinate, Long> secondsAtLocation, Long targetSavings) {
        Long numCheats = 0L;

        for (Map.Entry<Coordinate, Long> entry : secondsAtLocation.entrySet()) {
            Coordinate current = entry.getKey();
            for (Direction direction : Direction.values()) {
                Coordinate toTest = current.addCoordinate(new Coordinate(direction.getRow() * 2, direction.getCol() * 2));
                if (secondsAtLocation.containsKey(toTest)) {
                    Long secondsToBeat = secondsAtLocation.get(toTest);
                    Long savings = secondsToBeat - (entry.getValue() + 2);
                    if (savings >= targetSavings) {
                        numCheats++;
                    }
                }
            }
        }
        return numCheats;
    }

    @Override
    public void part2() {
        Grid<MazeElement> maze = InputLoader.loadMaze();

        Coordinate start = maze.findValue(MazeElement.START).getFirst();
        Coordinate end = maze.findValue(MazeElement.END).getFirst();

        Map<Coordinate, Long> secondsAtLocation = buildSecondsAtLocation(start, end, maze);
        final Long targetSavings = 100L;
        Long numCheats = getNumCheatsP2(secondsAtLocation, targetSavings);

        printSolution(numCheats);
    }

    private static Long getNumCheatsP2(Map<Coordinate, Long> secondsAtLocation, Long targetSavings) {
        Long numCheats = 0L;
        final long maxDistance = 20;

        for (Map.Entry<Coordinate, Long> currentEntry : secondsAtLocation.entrySet()) {
            for(Map.Entry<Coordinate, Long> toCompare : secondsAtLocation.entrySet()) {
                int distance = currentEntry.getKey().distanceValue(toCompare.getKey());
                if (distance == 0 || distance > maxDistance) {
                    continue;
                }
                long timeToBeat = toCompare.getValue();
                long savings = timeToBeat - (currentEntry.getValue() + distance);
                if (savings >= targetSavings) {
                    numCheats++;
                }
            }
        }
        return numCheats;
    }
}
