package framework;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class to assist in loading AOC input
 */
public class InputLoaderParent {

    /**
     * Loads all lines from the default input file into a List of strings
     * @return Each line from the input as an entry in a List of Strings
     */
    public static List<String> loadLines() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines;
    }

    /**
     * Loads all lines from the specified input file into a List of Strings
     * @param fileName The name of the file to load from
     * @return Each line from the input as an entry in a List of Strings
     */
    public static List<String> loadLines(String fileName) {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/" + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines;
    }

}
