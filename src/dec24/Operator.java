package dec24;

enum Operator {
    OR,
    AND,
    XOR;

    static Operator fromString(String input) {
        for (Operator operator : values()) {
            if (operator.name().equals(input)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Unsupported operator: " + input);
    }
}
