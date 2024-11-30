package example;

import framework.AOCParent;

import java.util.List;

class Example extends AOCParent {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        startPart(1);

        List<String> lines = InputLoaderExample.loadLines();
        for (String line : lines) {
            System.out.println(line);
        }

        endPart();
    }

    private static void part2() {
        startPart(2);

        List<String> lines = InputLoaderExample.loadLines("example.txt");
        for (String line : lines) {
            System.out.println(line);
        }

        endPart();
    }
}
