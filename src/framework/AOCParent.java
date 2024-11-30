package framework;

/**
 * Class to assist in timing AOC execution time
 */
public class AOCParent {
    private static long start;

    /**
     * Starts a timer for one of the parts
     * @param partNumber The part number
     */
    public static void startPart(int partNumber) {
        System.out.println("=== Part " + partNumber + " ===");
        start = System.currentTimeMillis();
    }

    /**
     * Ends the timer for one of the parts
     */
    public static void endPart() {
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end - start) + "ms");
        System.out.println();
    }
}
