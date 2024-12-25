package dec25;

import framework.AOCParent;

import java.util.List;

public class Dec25 extends AOCParent {
    @Override
    public void part1() {
        List<Schematic> schematics = InputLoader.loadSchematics();

        List<Schematic> locks = schematics.stream().filter(Schematic::isLock).toList();
        List<Schematic> keys = schematics.stream().filter(schematic -> !schematic.isLock()).toList();

        long solution = 0;
        for (Schematic lock : locks) {
            for (Schematic key : keys) {
                if (keyFits(lock, key)) {
                    solution++;
                }
            }
        }
        printSolution(solution);
    }

    private boolean keyFits(Schematic lock, Schematic key) {
        for (int col = 0; col < lock.columns().size(); col++) {
            if (lock.columns().get(col) + key.columns().get(col) > 5) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void part2() {

    }
}
