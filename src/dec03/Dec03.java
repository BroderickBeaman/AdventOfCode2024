package dec03;

import framework.AOCParent;

import java.util.Map;
import java.util.SortedMap;

public class Dec03 extends AOCParent {
    @Override
    public void part1() {
        long solution = InputLoader.loadPart1Input().stream().mapToLong(Instruction::multiply).sum();
        printSolution(solution);
    }

    @Override
    public void part2() {
        SortedMap<Integer, Object> inputMap = InputLoader.loadPart2Input();
        long solution = 0;
        boolean enabled = true;
        for (Map.Entry<Integer, Object> entry : inputMap.entrySet()) {
            if (entry.getValue() instanceof Boolean) {
                enabled = (boolean) entry.getValue();
            } else if (enabled) {
                Instruction instruction = (Instruction) entry.getValue();
                solution += instruction.multiply();
            }
        }
        printSolution(solution);
    }
}
