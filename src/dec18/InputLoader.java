package dec18;

import framework.InputLoaderParent;
import framework.utils.Coordinate;

import java.util.List;

class InputLoader extends InputLoaderParent {
    static List<Coordinate> loadBytes() {
        List<String> lines = loadLines();
        return lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer row = Integer.valueOf(parts[1]);
            Integer col = Integer.valueOf(parts[0]);
            return new Coordinate(row, col);
        }).toList();
    }
}
