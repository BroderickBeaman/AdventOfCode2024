package dec05;

import framework.AOCParent;

import java.util.*;

public class Dec05 extends AOCParent {
    @Override
    public void part1() {
        Map<Long, Set<Long>> dependencyMap = InputLoader.loadDependencyMap();
        List<Update> updates = InputLoader.loadUpdates();

        Long solution = updates.stream()
                .filter(update -> isValid(update, dependencyMap))
                .mapToLong(Update::middle)
                .sum();

        printSolution(solution);
    }

    @Override
    public void part2() {
        Map<Long, Set<Long>> dependencyMap = InputLoader.loadDependencyMap();
        List<Update> updates = InputLoader.loadUpdates();

        Long solution = updates.stream()
                .filter(update -> !isValid(update, dependencyMap))
                .mapToLong(update -> reorderAndFindMiddle(update, dependencyMap))
                .sum();

        printSolution(solution);
    }

    private static boolean isValid(Update update, Map<Long, Set<Long>> dependencyMap) {
        Set<Long> seen = new HashSet<>();
        List<Long> operations = update.operations();
        for (Long operation : operations) {
            seen.add(operation);
            for (Long dependency : dependencyMap.getOrDefault(operation, new HashSet<>())) {
                if (operations.contains(dependency) && !seen.contains(dependency)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static long reorderAndFindMiddle(Update update, Map<Long, Set<Long>> dependencyMap) {
        List<Long> toProcess = new ArrayList<>(update.operations());
        List<Long> inOrder = new ArrayList<>();
        while (!toProcess.isEmpty()) {
            for (Long operation : toProcess) {
                Set<Long> dependencies = dependencyMap.getOrDefault(operation, new HashSet<>());
                boolean first = true;
                for (Long dependency : dependencies) {
                    if (toProcess.contains(dependency)) {
                        first = false;
                        break;
                    }
                }
                if (first) {
                    toProcess.remove(operation);
                    inOrder.add(operation);
                    break;
                }
            }
        }

        return new Update(inOrder).middle();
    }
}
