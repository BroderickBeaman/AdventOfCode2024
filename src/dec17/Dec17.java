package dec17;

import framework.AOCParent;

public class Dec17 extends AOCParent {
    @Override
    public void part1() {
        Program program = InputLoader.loadProgram();
        printSolution(program.runP1());
    }

    @Override
    public void part2() {
        Program program = InputLoader.loadProgram();
        printSolution(program.runP2());
    }
}
