package dec23;

import framework.InputLoaderParent;

import java.util.*;

class InputLoader extends InputLoaderParent {

    static Map<String, Set<String>> loadComputerMap() {
        List<String> lines = loadLines();
        Map<String, Set<String>> computerMap = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split("-");
            computerMap.computeIfAbsent(parts[0], _ -> new HashSet<>());
            computerMap.get(parts[0]).add(parts[1]);
            computerMap.computeIfAbsent(parts[1], _ -> new HashSet<>());
            computerMap.get(parts[1]).add(parts[0]);
        }

        return computerMap;
    }
}
