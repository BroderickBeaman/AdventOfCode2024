package dec03;

record Instruction(long x, long y) {
    long multiply() {
        return x * y;
    }
}
