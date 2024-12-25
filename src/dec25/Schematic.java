package dec25;

import java.util.ArrayList;
import java.util.List;

record Schematic(List<Integer> columns, boolean isLock) {
    static Schematic fromInput(List<String> lines) {
        List<Integer> columns = new ArrayList<>();
        for (int col = 0; col < lines.getFirst().length(); col++) {
            int height = -1;

            for (int row = 0; row < lines.size(); row++) {
                if (lines.get(row).charAt(col) == '#') {
                    height++;
                }
            }

            columns.add(height);
        }

        boolean isLock = lines.getFirst().charAt(0) == '#';

        return new Schematic(columns, isLock);
    }
}
