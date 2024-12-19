package dec19;

import framework.AOCParent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dec19 extends AOCParent {

    Map<String, Boolean> designCache;
    int maxPatternLength;

    @Override
    public void part1() {
        final List<String> patterns = InputLoader.loadPatterns();
        final List<String> designs = InputLoader.loadDesigns();
        designCache = new HashMap<>();
        maxPatternLength = patterns.stream().mapToInt(String::length).max().getAsInt();

        long count = designs.stream().filter(design -> isPossible(patterns, design)).count();
        printSolution(count);
    }

    private boolean isPossible(final List<String> patterns, String design) {

        if (patterns.contains(design) || design.isEmpty()) {
            return true;
        }

        if (designCache.containsKey(design)) {
            return designCache.get(design);
        }

        for (int i = 1; i <= Math.min(maxPatternLength, design.length()); i++) {
            String toTest = design.substring(0, i);
            String nextDesign = design.substring(i);
            if (patterns.contains(toTest)) {
                boolean result = isPossible(patterns, nextDesign);
                designCache.put(nextDesign, result);
                if (result) {
                    return true;
                }
            }
        }

        designCache.put(design, false);
        return false;

    }

    @Override
    public void part2() {

    }
}
