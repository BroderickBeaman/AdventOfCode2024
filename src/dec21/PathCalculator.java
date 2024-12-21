package dec21;

import framework.utils.Coordinate;
import framework.utils.Direction;
import framework.utils.Grid;

import java.util.*;
import java.util.stream.Collectors;

class PathCalculator {

    static Map<CharacterPair, List<String>> calculateOptimalPaths(Grid<Character> grid) {
        final int rows = grid.rows();
        final int cols = grid.cols();

        List<Coordinate> allCoordinates = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                allCoordinates.add(new Coordinate(row, col));
            }
        }

        Map<CharacterPair, List<String>> optimalPaths = new HashMap<>();

        for (Coordinate source : allCoordinates) {
            if (grid.get(source).equals(' ')) {
                continue;
            }
            for (Coordinate target : allCoordinates) {
                if (grid.get(target).equals(' ')) {
                    continue;
                }
                optimalPaths.put(new CharacterPair(grid.get(source), grid.get(target)), computePaths(grid, source, target));
            }
        }

        return optimalPaths;
    }

    private static List<String> computePaths(Grid<Character> grid, Coordinate source, Coordinate target) {
        List<String> paths = new ArrayList<>();
        long optimalPathLength = Long.MAX_VALUE;

        PriorityQueue<PathState> q = new PriorityQueue<>();
        q.add(new PathState(source, ""));
        while (!q.isEmpty()) {
            PathState current = q.poll();

            if (current.location().equals(target)) {
                String path = current.pathSoFar() + "A";
                if (path.length() > optimalPathLength) {
                    return paths;
                }
                paths.add(path);
                optimalPathLength = path.length();
            }

            for (Direction direction : Direction.values()) {
                Coordinate nextLocation = current.location().addDirection(direction);
                if (!grid.isInBounds(nextLocation) || grid.get(nextLocation).equals(' ')) {
                    continue;
                }
                q.add(new PathState(nextLocation, current.pathSoFar() + charFromDirection(direction)));
            }
        }

        return paths;
    }

    private static Character charFromDirection(Direction direction) {
        return switch (direction) {
            case N -> '^';
            case E -> '>';
            case S -> 'v';
            case W -> '<';
        };
    }

    static List<String> optimalPathsForCode(
            Map<CharacterPair, List<String>> pathsBetweenCharacters,
            String code
    ) {
        List<String> paths = new ArrayList<>();
        optimalPathsForCode(pathsBetweenCharacters, code, paths, "", 0);
        return paths;
    }

    private static void optimalPathsForCode(
            Map<CharacterPair, List<String>> pathsBetweenCharacters,
            String code,
            List<String> paths,
            String pathSoFar,
            int depth
    ) {
        CharacterPair pair = new CharacterPair(code.charAt(depth), code.charAt(depth + 1));
        for (String pairPath : pathsBetweenCharacters.get(pair)) {
            String tempPath = pathSoFar + pairPath;
            if (depth == code.length() - 2) {
                paths.add(tempPath);
            } else {
                optimalPathsForCode(pathsBetweenCharacters, code, paths, tempPath, depth + 1);
            }
        }
    }

    static Map<CharacterPair, Integer> calculatePathLength(Map<CharacterPair, List<String>> inputMap) {
        return inputMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getFirst().length()));
    }
}
