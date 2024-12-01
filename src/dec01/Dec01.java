package dec01;

import framework.AOCParent;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dec01 extends AOCParent {

    @Override
    public void part1() {
        List<Integer> left = InputLoader.loadLeft();
        List<Integer> right = InputLoader.loadRight();

        Collections.sort(left);
        Collections.sort(right);

        long result = 0L;

        for (int i = 0; i < left.size(); i++) {
            result += Math.abs(left.get(i) - right.get(i));
        }

        printSolution(result);
    }

    @Override
    public void part2() {
        List<Integer> left = InputLoader.loadLeft();
        List<Integer> right = InputLoader.loadRight();

        Map<Integer, Integer> rightFrequencyMap = new HashMap<>();

        for (Integer currentInt : right) {
            rightFrequencyMap.putIfAbsent(currentInt, 0);
            rightFrequencyMap.replace(currentInt, rightFrequencyMap.get(currentInt) + 1);
        }

        long result = 0L;

        for (Integer currentInt : left) {
            result += (long) currentInt * rightFrequencyMap.getOrDefault(currentInt, 0);
        }

        printSolution(result);
    }

}
