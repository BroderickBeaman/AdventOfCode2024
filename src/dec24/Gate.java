package dec24;

import java.util.Set;

record Gate(String input1, String input2, String output, Operator operator) {
    static Gate fromString(String input) {
        String[] parts = input.split(" ");
        String input1 = parts[0];
        String input2 = parts[2];
        String output = parts[4];
        Operator operator = Operator.fromString(parts[1]);

        return new Gate(input1, input2, output, operator);
    }
}
