package dec07;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

record Equation(Long total, List<Long> operands) {

    boolean isValidPart1() {
        return isValidPart1(operands().get(0), 1);
    }

    private boolean isValidPart1(Long runningTotal, int index) {
        if (index == operands.size()) {
            return runningTotal.equals(total);
        }

        if (isValidPart1(runningTotal + operands.get(index), index + 1)) {
            return true;
        }

        return isValidPart1(runningTotal * operands.get(index), index + 1);
    }

    boolean isValidPart2() {
        return isValidPart2(operands().get(0), 1);
    }

    boolean isValidPart2(Long runningTotal, int index) {
        if (index == operands.size()) {
            return runningTotal.equals(total);
        }

        if (isValidPart2(runningTotal + operands.get(index), index + 1)) {
            return true;
        }

        if (isValidPart2(runningTotal * operands.get(index), index + 1)) {
            return true;
        }

        return isValidPart2(Long.parseLong(runningTotal + operands.get(index).toString()), index + 1);
    }

    static Equation fromString(String string) {
        String[] parts = string.split(": ");
        Long total = Long.parseLong(parts[0]);
        List<Long> operands = Arrays.asList(parts[1].split(" ")).stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        return new Equation(total, operands);
    }
}
