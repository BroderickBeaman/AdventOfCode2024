package dec21;

import framework.AOCParent;
import framework.utils.Grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dec21 extends AOCParent {

    Grid<Character> numPad = InputLoader.loadNumberGrid();
    Grid<Character> arrowPad = InputLoader.loadDirectionalGrid();
    Map<CharacterPair, List<String>> numPadPaths = PathCalculator.calculateOptimalPaths(numPad);
    Map<CharacterPair, List<String>> arrowPadPaths = PathCalculator.calculateOptimalPaths(arrowPad);
    Map<CharacterPair, Integer> arrowPadPathsLength = PathCalculator.calculatePathLength(arrowPadPaths);
    List<String> codes = InputLoader.loadCodes();

    Map<RobotCacheKey, Long> robotCache;

    @Override
    public void part1() {
        Long solution = 0L;

        for (String code : codes) {
            List<String> optimalNumPaths = PathCalculator.optimalPathsForCode(numPadPaths, code);
            robotCache = new HashMap<>();
            Long minPathLength = Long.MAX_VALUE;
            for (String path : optimalNumPaths) {
                path = "A" + path;
                Long tempLength = 0L;
                for (CharacterPair nextPair : pairsFromPath(path)) {
                    tempLength += computeBestLengthAtDepth(nextPair, 2);
                }
                if (tempLength < minPathLength) {
                    minPathLength = tempLength;
                }
            }

            solution += (minPathLength * Long.valueOf(code.substring(1, 4)));
        }

        printSolution(solution);
    }

    @Override
    public void part2() {
        Long solution = 0L;

        for (String code : codes) {
            List<String> optimalNumPaths = PathCalculator.optimalPathsForCode(numPadPaths, code);
            robotCache = new HashMap<>();
            Long minPathLength = Long.MAX_VALUE;
            for (String path : optimalNumPaths) {
                path = "A" + path;
                Long tempLength = 0L;
                for (CharacterPair nextPair : pairsFromPath(path)) {
                    tempLength += computeBestLengthAtDepth(nextPair, 25);
                }
                if (tempLength < minPathLength) {
                    minPathLength = tempLength;
                }
            }

            solution += (minPathLength * Long.valueOf(code.substring(1, 4)));
        }

        printSolution(solution);
    }

    private Long computeBestLengthAtDepth(CharacterPair pair, int depth) {
        if (depth == 1) {
            return (long) arrowPadPathsLength.get(pair);
        }
        RobotCacheKey cacheKey = new RobotCacheKey(pair, depth);
        if (robotCache.containsKey(cacheKey)) {
            return robotCache.get(cacheKey);
        }

        Long minPathLength = Long.MAX_VALUE;
        for (String path : arrowPadPaths.get(pair)) {
            path = "A" + path;
            Long tempLength = 0L;
            for (CharacterPair nextPair : pairsFromPath(path)) {
                tempLength += computeBestLengthAtDepth(nextPair, depth - 1);
            }
            if (tempLength < minPathLength) {
                minPathLength = tempLength;
            }
        }

        robotCache.put(cacheKey, minPathLength);
        return minPathLength;
    }

    private List<CharacterPair> pairsFromPath(String path) {
        List<CharacterPair> pairs = new ArrayList<>();
        for (int i = 0; i <= path.length() - 2; i++) {
            pairs.add(new CharacterPair(path.charAt(i), path.charAt(i+1)));
        }
        return pairs;
    }
}
