package dec07;

import framework.AOCParent;

import java.util.List;

public class Dec07 extends AOCParent {
    @Override
    public void part1() {
        List<Equation> equations = InputLoader.loadEquations();
        Long solution = equations.stream().filter(Equation::isValidPart1).mapToLong(Equation::total).sum();
        printSolution(solution);
    }

    @Override
    public void part2() {
        List<Equation> equations = InputLoader.loadEquations();
        Long solution = equations.stream().filter(Equation::isValidPart2).mapToLong(Equation::total).sum();
        printSolution(solution);
    }
}
