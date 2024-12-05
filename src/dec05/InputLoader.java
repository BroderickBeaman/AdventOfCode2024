package dec05;

import framework.InputLoaderParent;

import java.util.*;
import java.util.stream.Collectors;

class InputLoader extends InputLoaderParent {

    static Map<Long, Set<Long>> loadDependencyMap() {
        Map<Long, Set<Long>> dependencyMap = new HashMap<>();
        for (String line : loadLines("updateRules.txt")) {
            String[] parts = line.split("\\|");
            long left = Long.parseLong(parts[0]);
            long right = Long.parseLong(parts[1]);
            dependencyMap.computeIfAbsent(right, k -> new HashSet<>());
            dependencyMap.get(right).add(left);
        }
        return dependencyMap;
    }

    static List<Update> loadUpdates() {
        return loadLines("updates.txt").stream().map(line -> {
            String[] parts = line.split(",");
            List<Long> numbers = Arrays.asList(parts).stream().map(Long::parseLong).collect(Collectors.toList());
            return new Update(numbers);
        }).collect(Collectors.toList());
    }
}
