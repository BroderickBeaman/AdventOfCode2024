package dec25;

import framework.InputLoaderParent;

import java.util.ArrayList;
import java.util.List;

class InputLoader extends InputLoaderParent {
    static List<Schematic> loadSchematics() {
        List<String> lines = loadLines();

        List<String> schematic = new ArrayList<>();
        List<Schematic> schematics = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                schematics.add(Schematic.fromInput(schematic));
                schematic = new ArrayList<>();
            } else {
                schematic.add(line);
            }
        }

        schematics.add(Schematic.fromInput(schematic));

        return schematics;
    }
}
