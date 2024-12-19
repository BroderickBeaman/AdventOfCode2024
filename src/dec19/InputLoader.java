package dec19;

import framework.InputLoaderParent;

import java.util.Arrays;
import java.util.List;

class InputLoader extends InputLoaderParent {

    static List<String> loadPatterns() {
        return Arrays.asList(loadLines().getFirst().split(", "));
    }

    static List<String> loadDesigns() {
        List<String> lines = loadLines();
        return lines.subList(2, lines.size());
    }
}
