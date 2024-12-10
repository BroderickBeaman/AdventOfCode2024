package dec10;

import framework.InputLoaderParent;
import framework.utils.Grid;

import java.util.List;

class InputLoader extends InputLoaderParent {
    static Grid<Integer> loadTrailMap() {
        List<String> lines = loadLines();
        Integer[][] grid = new Integer[lines.size()][lines.get(0).length()];
        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                grid[row][col] = Integer.parseInt(String.valueOf(line.charAt(col)));
            }
        }
        return new Grid<Integer>(grid);
    }
}
