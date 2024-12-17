package dec17;

import java.util.ArrayList;
import java.util.List;

class Program {

    private long registerA;
    private long registerB;
    private long registerC;

    private final List<Integer> program;
    private List<Integer> output = new ArrayList<>();

    private int pointer = 0;

    public Program(long registerA, long registerB, long registerC, List<Integer> program) {
        this.registerA = registerA;
        this.registerB = registerB;
        this.registerC = registerC;
        this.program = program;
    }

    public String runP1() {
        while (getOpCode() != null) {
            int opCode = getOpCode();
            int operand = getOperand();

            switch (opCode) {
                case 0 -> adv(operand);
                case 1 -> bxl(operand);
                case 2 -> bst(operand);
                case 3 -> jnz(operand);
                case 4 -> bxc(operand);
                case 5 -> out(operand);
                case 6 -> bdv(operand);
                case 7 -> cdv(operand);
                default -> throw new RuntimeException("Unexpected opCode: " + opCode);
            }
        }
        return toString();
    }

    private void adv(int operand) {
        registerA = registerA / (long) Math.pow(2, convertToComboOperand(operand));
        incrementPointer();
    }

    private void bxl(int operand) {
        registerB = registerB ^ (long) operand;
        incrementPointer();
    }

    private void bst(int operand) {
        registerB = convertToComboOperand(operand) % 8;
        incrementPointer();
    }

    private void jnz(int operand) {
        if (registerA == 0L) {
            incrementPointer();
        } else {
            pointer = operand;
        }
    }

    private void bxc(int operand) {
        registerB = registerB ^ registerC;
        incrementPointer();
    }

    private void out(int operand) {
        output.add((int) (convertToComboOperand(operand) % 8));
        incrementPointer();
    }

    private void bdv(int operand) {
        registerB = registerA / (long) Math.pow(2, convertToComboOperand(operand));
        incrementPointer();
    }

    private void cdv(int operand) {
        registerC = registerA / (long) Math.pow(2, convertToComboOperand(operand));
        incrementPointer();
    }

    private void incrementPointer() {
        pointer += 2;
    }

    private Integer getOpCode() {
        if (pointer < program.size()) {
            return program.get(pointer);
        }
        return null;
    }

    private Integer getOperand() {
        return program.get(pointer + 1);
    }

    private Long convertToComboOperand(Integer literal) {
        return switch (literal) {
            case 0, 1, 2, 3 -> Long.valueOf(literal);
            case 4 -> registerA;
            case 5 -> registerB;
            case 6 -> registerC;
            default -> throw new RuntimeException("Unexpected operand: " + literal);
        };
    }

    public Long runP2() {
        return trySolution(0L, 0);
    }

    public Long trySolution(final Long regAInput, final Integer depth) {
        registerA = regAInput;
        registerB = 0;
        registerC = 0;
        pointer = 0;
        output = new ArrayList<>();

        runP1();

        if (output.equals(program)) {
            return regAInput;
        }


        for (long i = 0; i < 8; i++) {
            long registerA = (regAInput << 3) + i;
            long registerB = registerA % 8;
            registerB = registerB ^ 2L;
            long registerC = registerA >> registerB;
            registerB = registerB ^ registerC;
            registerB = registerB ^ 3;

            if (registerB % 8L == program.get(program.size() - 1 - depth)) {
                Long next = trySolution(registerA, depth + 1);
                if (next == null) {
                    continue;
                }
                return next;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return String.join(",", output.stream().map(Object::toString).toList());
    }
}
