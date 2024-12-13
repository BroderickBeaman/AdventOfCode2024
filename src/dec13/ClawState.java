package dec13;

record ClawState(Long a, Long b) {

    ClawState pressA() {
        return new ClawState(a + 1, b);
    }

    ClawState pressB() {
        return new ClawState(a, b + 1);
    }
}
