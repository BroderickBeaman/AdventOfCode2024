package dec13;

record GamePart2(long buttonAX, long buttonAY, long buttonBX, long buttonBY, long prizeX, long prizeY) {
    private static final long BUTTON_A_TOKENS = 3L;
    private static final long BUTTON_B_TOKENS = 1L;

    Long numTokens() {
        long aPresses = (prizeX * buttonBY - prizeY * buttonBX) / (buttonAX * buttonBY - buttonAY * buttonBX);
        long bPresses = (prizeX - buttonAX * aPresses) / buttonBX;

        long xDistance = aPresses * buttonAX + bPresses * buttonBX;
        long yDistance = aPresses * buttonAY + bPresses * buttonBY;

        // Handle rounding errors (if there's a rounding error there is no integer solution, which means no solution)
        if (xDistance != prizeX || yDistance != prizeY) {
            return null;
        }

        return aPresses * BUTTON_A_TOKENS + bPresses * BUTTON_B_TOKENS;
    }
}
