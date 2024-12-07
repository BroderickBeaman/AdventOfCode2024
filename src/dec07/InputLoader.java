package dec07;

import framework.InputLoaderParent;

import java.util.List;
import java.util.stream.Collectors;

class InputLoader extends InputLoaderParent {
    static List<Equation> loadEquations() {
        List<String> lines = loadLines();
        return lines.stream().map(Equation::fromString).collect(Collectors.toList());
    }
}
