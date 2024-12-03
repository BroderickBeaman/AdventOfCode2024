package dec03;

import framework.InputLoaderParent;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InputLoader extends InputLoaderParent {

    // Matches on "mul(x,y)" where x and y are both whole numbers
    private static final Pattern instructionPattern = Pattern.compile("mul\\((-?\\d+),(-?\\d+)\\)");
    private static final Pattern enabledPattern = Pattern.compile("do\\(\\)");
    private static final Pattern disabledPattern = Pattern.compile("don't\\(\\)");

    static List<Instruction> loadPart1Input() {
        List<String> lines = loadLines();
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line);
        }
        String program = sb.toString();
        List<Instruction> instructions = new ArrayList<>();
        Matcher matcher = instructionPattern.matcher(program);
        while (matcher.find()) {
            String match = matcher.group();
            long x = Long.parseLong(match.substring(match.indexOf('(') + 1, match.indexOf(',')));
            long y = Long.parseLong(match.substring(match.indexOf(',') + 1, match.indexOf(')')));
            instructions.add(new Instruction(x, y));
        }
        return instructions;
    }

    static SortedMap<Integer, Object> loadPart2Input() {
        List<String> lines = loadLines();
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line);
        }
        String program = sb.toString();
        SortedMap<Integer, Object> inputMap = new TreeMap<>();

        // Add instructions to map
        Matcher instructionMatcher = instructionPattern.matcher(program);
        while (instructionMatcher.find()) {
            String match = instructionMatcher.group();
            long x = Long.parseLong(match.substring(match.indexOf('(') + 1, match.indexOf(',')));
            long y = Long.parseLong(match.substring(match.indexOf(',') + 1, match.indexOf(')')));
            inputMap.put(instructionMatcher.start(), new Instruction(x, y));
        }

        // Add enabled flags
        Matcher enabledMatcher = enabledPattern.matcher(program);
        while (enabledMatcher.find()) {
            inputMap.put(enabledMatcher.start(), Boolean.TRUE);
        }

        // Add disabled flags
        Matcher disabledMatcher = disabledPattern.matcher(program);
        while (disabledMatcher.find()) {
            inputMap.put(disabledMatcher.start(), Boolean.FALSE);
        }

        return inputMap;
    }
}
