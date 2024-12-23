package dec23;

import framework.AOCParent;
import java.util.*;

public class Dec23 extends AOCParent {
    @Override
    public void part1() {
        final Map<String, Set<String>> computerMap = InputLoader.loadComputerMap();

        Set<Set<String>> connectedTrios = new HashSet<>();

        computerMap.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("t"))
                .forEach(entry -> {
                    String c1 = entry.getKey();
                    List<String> otherComputers = new ArrayList<>(entry.getValue());

                    if (otherComputers.size() == 1) {
                        return;
                    }

                    for (int i = 0; i < otherComputers.size() - 1; i++) {
                        for (int j = i + 1; j < otherComputers.size(); j++) {
                            String c2 = otherComputers.get(i);
                            String c3 = otherComputers.get(j);
                            if (computerMap.get(c2).contains(c3)) {
                                connectedTrios.add(Set.of(c1, c2, c3));
                            }
                        }
                    }
                });

        printSolution("Computer trios starting with the letter 't': " + connectedTrios.size());
    }

    @Override
    public void part2() {
        final Map<String, Set<String>> computerMap = InputLoader.loadComputerMap();

        Set<String> largestNetwork = new HashSet<>();

        for (Map.Entry<String, Set<String>> entry : computerMap.entrySet()) {
            String c1 = entry.getKey();
            for (String c2 : entry.getValue()) {
                Set<String> networkInProgress = new HashSet<>();
                networkInProgress.add(c1);
                networkInProgress.add(c2);
                Set<String> otherComputers = new HashSet<>(entry.getValue());
                otherComputers.remove(c2);
                for (String c3 : otherComputers) {
                    if (computerMap.get(c3).containsAll(networkInProgress)) {
                        networkInProgress.add(c3);
                    }
                }

                if (networkInProgress.size() > largestNetwork.size()) {
                    largestNetwork = networkInProgress;
                }
            }
        }

        List<String> largestNetworkList = new ArrayList<>(largestNetwork);
        Collections.sort(largestNetworkList);
        String solution = String.join(",", largestNetworkList);
        printSolution("Largest network: " + solution);
    }
}
