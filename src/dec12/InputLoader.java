package dec12;

import framework.InputLoaderParent;
import framework.utils.Grid;

import java.util.List;

class InputLoader extends InputLoaderParent {

    static Grid<Character> loadGarden() {
        List<String> lines = loadLines();
        int rows = lines.size();
        int cols = lines.get(0).length();
        Character[][] grid = new Character[rows][cols];
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                grid[row][col] = line.charAt(col);
            }
        }

        return new Grid<>(grid);
    }
}
