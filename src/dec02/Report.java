package dec02;

import java.util.ArrayList;
import java.util.List;

record Report(List<Long> levels) {

    boolean isSafePart1() {
        return isSafe(levels);
    }

    boolean isSafePart2() {
        if (isSafe(levels)) {
            return true;
        }

        for (int i = 0; i < levels.size(); i++) {
            List<Long> tempLevels = new ArrayList<>(levels);
            tempLevels.remove(i);
            if (isSafe(tempLevels)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSafe(List<Long> levels) {
        long diff = levels.get(1) - levels.get(0);
        if (diff == 0) {
            return false;
        }
        // 1 if increasing, -1 if decreasing
        boolean increasing = diff > 0;

        for (int i = 0; i < levels.size() - 1; i++) {
            diff = levels.get(i + 1) - levels.get(i);

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }

            if (increasing && diff < 0) {
                return false;
            } else if (!increasing && diff > 0) {
                return false;
            }
        }
        return true;
    }
}
