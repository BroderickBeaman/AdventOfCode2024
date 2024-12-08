package dec08;

import framework.InputLoaderParent;

import java.util.*;

class InputLoader extends InputLoaderParent {
    static Map<Character, List<Coordinate>> loadAntennaMap() {
        List<String> lines = loadLines();
        Map<Character, List<Coordinate>> antennaMap = new HashMap<>();
        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                Character character = line.charAt(col);
                if (!character.equals('.')) {
                    antennaMap.computeIfAbsent(character, c -> new ArrayList<>());
                    antennaMap.get(character).add(new Coordinate(row, col));
                }
            }
        }

        return antennaMap;
    }

    static Boolean[][] loadAntinodeGrid() {
        List<String> lines = loadLines();
        Boolean[][] grid = new Boolean[lines.size()][lines.get(0).length()];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = false;
            }
        }

        return grid;
    }
}
