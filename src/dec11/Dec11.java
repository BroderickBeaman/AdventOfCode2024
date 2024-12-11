package dec11;

import framework.AOCParent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dec11 extends AOCParent {

    private Map<StoneCacheKey, Long> stoneCache;

    @Override
    public void part1() {
        List<Long> stones = InputLoader.loadStones();
        stoneCache = new HashMap<>();
        long solution = 0;
        for (Long stone : stones) {
            solution += countStones(stone, 25);
        }
        printSolution(solution);
    }

    @Override
    public void part2() {
        List<Long> stones = InputLoader.loadStones();
        stoneCache = new HashMap<>();
        long solution = 0;
        for (Long stone : stones) {
            solution += countStones(stone, 75);
        }
        printSolution(solution);
    }

    private Long countStones(Long stone, Integer stepsRemaining) {
        if (stepsRemaining.equals(0)) {
            return 1L;
        }

        StoneCacheKey cacheKey = new StoneCacheKey(stone, stepsRemaining);
        if (stoneCache.containsKey(cacheKey)) {
            return stoneCache.get(cacheKey);
        }

        Long result;

        if (stone.equals(0L)) {
            result = countStones(1L, stepsRemaining - 1);
        } else if (stone.toString().length() % 2 == 0) {
            String stoneString = stone.toString();
            Long leftStone = Long.valueOf(stoneString.substring(0, stoneString.length() / 2));
            Long rightStone = Long.valueOf(stoneString.substring(stoneString.length() / 2));

            result = countStones(leftStone, stepsRemaining - 1) + countStones(rightStone, stepsRemaining - 1);
        } else {
            result = countStones(stone * 2024, stepsRemaining - 1);
        }

        stoneCache.put(cacheKey, result);

        return result;
    }


}
