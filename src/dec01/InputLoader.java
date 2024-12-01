package dec01;

import framework.InputLoaderParent;

import java.util.List;
import java.util.stream.Collectors;

class InputLoader extends InputLoaderParent {

    static List<Integer> loadLeft() {
        return loadLines().stream().map(line -> Integer.valueOf(line.split("   ")[0]))
                .collect(Collectors.toList());
    }

    static List<Integer> loadRight() {
        return loadLines().stream().map(line -> Integer.valueOf(line.split("   ")[1]))
                .collect(Collectors.toList());
    }
}
