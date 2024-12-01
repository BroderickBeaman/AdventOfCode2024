package dec01;

import framework.AOCParent;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Dec01 extends AOCParent {

    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        startPart(1);

        List<Integer> left = InputLoader.loadLeft();
        List<Integer> right = InputLoader.loadRight();

        Collections.sort(left);
        Collections.sort(right);

        long result = 0L;

        for (int i = 0; i < left.size(); i++) {
            result += Math.abs(left.get(i) - right.get(i));
        }

        printSolution(result);

        endPart();
    }

    private static void part2() {
        startPart(2);

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

        endPart();
    }

}
