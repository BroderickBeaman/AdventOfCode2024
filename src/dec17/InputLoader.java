package dec17;

import framework.InputLoaderParent;

import java.util.Arrays;
import java.util.List;

class InputLoader extends InputLoaderParent {
    static Program loadProgram() {
        List<String> lines = loadLines();

        Long registerA = Long.valueOf(lines.get(0).split(" ")[2]);
        Long registerB = Long.valueOf(lines.get(1).split(" ")[2]);
        Long registerC = Long.valueOf(lines.get(2).split(" ")[2]);

        List<Integer> program = Arrays.stream(lines.get(4).split(" ")[1].split(",")).map(Integer::valueOf).toList();

        return new Program(registerA, registerB, registerC, program);
    }
}
