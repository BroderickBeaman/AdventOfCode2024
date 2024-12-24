package dec24;

import framework.AOCParent;

import java.util.*;

public class Dec24 extends AOCParent {
    @Override
    public void part1() {
        Map<String, Boolean> wires = InputLoader.loadInitialWireStates();
        Set<Gate> gates = InputLoader.loadGates();

        for (Set<Gate> gatesToProcess = loadGatesToProcess(wires, gates); !gatesToProcess.isEmpty(); gatesToProcess = loadGatesToProcess(wires, gates)) {
            for (Gate gate : gatesToProcess) {
                switch (gate.operator()) {
                    case OR -> wires.put(gate.output(), wires.get(gate.input1()) || wires.get(gate.input2()));
                    case AND -> wires.put(gate.output(), wires.get(gate.input1()) && wires.get(gate.input2()));
                    case XOR -> wires.put(gate.output(), wires.get(gate.input1()) ^ wires.get(gate.input2()));
                }
            }
        }

        List<Map.Entry<String, Boolean>> outputs = wires.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("z"))
                .sorted(new WireComparator())
                .toList();

        long solution = 0L;
        for (int i = 0; i < outputs.size(); i++) {
            if (outputs.get(i).getValue()) {
                solution += (long) Math.pow(2, i);
            }
        }

        printSolution(solution);
    }

    private Set<Gate> loadGatesToProcess(Map<String, Boolean> wiresStates, Set<Gate> gatesLeft) {
        Set<Gate> gatesToProcess = new HashSet<>();
        for (Gate gate : gatesLeft) {
            if (wiresStates.containsKey(gate.input1()) && wiresStates.containsKey(gate.input2())) {
                gatesToProcess.add(gate);
            }
        }

        gatesLeft.removeAll(gatesToProcess);

        return gatesToProcess;
    }

    @Override
    public void part2() {
        printSolution("Solved on paper");
    }
}
