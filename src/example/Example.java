package example;

import framework.AOCParent;

import java.util.List;

public class Example extends AOCParent {

    @Override
    public void part1() {
        List<String> lines = InputLoaderExample.loadLines();
        for (String line : lines) {
            printSolution(line);
        }
    }

    @Override
    public void part2() {
        List<String> lines = InputLoaderExample.loadLines("example.txt");
        for (String line : lines) {
            printSolution(line);
        }
    }
}
