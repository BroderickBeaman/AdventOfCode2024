package dec11;

import framework.InputLoaderParent;

import java.util.Arrays;
import java.util.List;

class InputLoader extends InputLoaderParent {
    static List<Long> loadStones() {
        return Arrays.stream(loadLines().get(0).split(" ")).map(Long::valueOf).toList();
    }
}
