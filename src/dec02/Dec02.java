package dec02;

import framework.AOCParent;

import java.util.List;

public class Dec02 extends AOCParent {
    @Override
    public void part1() {
        List<Report> reports = InputLoader.loadReports();
        long solution = reports.stream().filter(Report::isSafePart1).count();
        printSolution(solution);
    }

    @Override
    public void part2() {
        List<Report> reports = InputLoader.loadReports();
        long solution = reports.stream().filter(Report::isSafePart2).count();
        printSolution(solution);
    }
}
