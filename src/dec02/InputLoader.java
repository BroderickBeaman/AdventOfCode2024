package dec02;

import framework.InputLoaderParent;

import java.util.Arrays;
import java.util.List;

class InputLoader extends InputLoaderParent {

    static List<Report> loadReports() {
        return loadLines().stream().map(line -> {
            List<String> parts = Arrays.asList(line.split(" "));
            return new Report(parts.stream().map(Long::valueOf).toList());
        }).toList();
    }

}
