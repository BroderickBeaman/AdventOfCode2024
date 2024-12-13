package dec13;

import framework.utils.Coordinate;

import java.util.HashMap;
import java.util.Map;

record GamePart1(Coordinate buttonA, Coordinate buttonB, Coordinate prize) {

    private static final long BUTTON_A_TOKENS = 3L;
    private static final long BUTTON_B_TOKENS = 1L;

    Long numTokens() {
        Map<ClawState, Long> seen = new HashMap<>();
        return minTokens(seen, new ClawState(0L, 0L), new Coordinate(0, 0));
    }

    private Long minTokens(Map<ClawState, Long> seen, ClawState presses, Coordinate clawPosition) {
        // Too many presses, not a valid state
        if (presses.a() > 100 || presses.b() > 100) {
            return null;
        }

        // Claw moved too far, not a valid state
        if (clawPosition.row() > prize.row() || clawPosition.col() > prize.col()) {
            return null;
        }

        // Found the prize
        if (clawPosition.equals(prize)) {
            return presses.a() * BUTTON_A_TOKENS + presses.b() * BUTTON_B_TOKENS;
        }

        // Already been in this state
        if (seen.containsKey(presses)) {
            return seen.get(presses);
        }

        Long pressA = minTokens(seen, presses.pressA(), clawPosition.addCoordinate(buttonA));
        Long pressB = minTokens(seen, presses.pressB(), clawPosition.addCoordinate(buttonB));

        Long min = min(pressA, pressB);
        seen.put(presses, min);
        return min;
    }

    private Long min(Long a, Long b) {
        if (a == null && b == null) {
            return null;
        }

        if (b == null) {
            return a;
        }

        if (a == null) {
            return b;
        }

        return Long.min(a, b);
    }
}
