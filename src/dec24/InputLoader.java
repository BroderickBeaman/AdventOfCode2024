package dec24;

import framework.InputLoaderParent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class InputLoader extends InputLoaderParent {
    static Map<String, Boolean> loadInitialWireStates() {
        Map<String, Boolean> wireStates = new HashMap<>();
        loadLines().stream().filter(line -> line.contains(":")).forEach(line -> {
            String[] parts = line.split(": ");
            String wireName = parts[0];
            Boolean initialValue = parts[1].equals("1");
            wireStates.put(wireName, initialValue);
        });
        return wireStates;
    }

    static Set<Gate> loadGates() {
        return loadLines().stream()
                .filter(line -> line.contains("->"))
                .map(Gate::fromString)
                .collect(Collectors.toSet());
    }
}
