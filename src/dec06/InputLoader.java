package dec06;

import framework.InputLoaderParent;

import java.util.List;

class InputLoader extends InputLoaderParent {

    static Cell[][] loadPuzzleGrid() {
        List<String> lines = loadLines();
        int rows = lines.size();
        int cols = lines.get(0).length();

        Cell[][] grid = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                grid[row][col] = Cell.fromChar(line.charAt(col));
            }
        }

        return grid;
    }
}
