package framework;

/**
 * Class to assist in timing AOC execution time
 */
public abstract class AOCParent {
    private long start;

    public abstract void part1();
    public abstract void part2();


    /**
     * Starts a timer for one of the parts
     * @param partNumber The part number
     */
    private void startPart(int partNumber) {
        System.out.println("=== Part " + partNumber + " ===");
        start = System.currentTimeMillis();
    }

    /**
     * Ends the timer for one of the parts
     */
    private void endPart() {
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end - start) + "ms");
        System.out.println();
    }

    /**
     * Prints the solution to the console
     * @param solution the solution
     */
    public static void printSolution(Object solution) {
        System.out.println("Solution: " + solution);
    }

    /**
     * Runs both part 1 and part 2
     */
    public void run() {
        startPart(1);
        part1();
        endPart();

        startPart(2);
        part2();
        endPart();
    }
}
