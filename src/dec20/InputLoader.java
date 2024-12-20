package dec20;

import framework.InputLoaderParent;
import framework.utils.Grid;

import java.util.List;

class InputLoader extends InputLoaderParent {
    static Grid<MazeElement> loadMaze() {
        List<String> lines = loadLines();
        final int rows = lines.size();
        final int cols = lines.getFirst().length();
        Grid<MazeElement> maze = new Grid<>(MazeElement.class, rows, cols);

        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                MazeElement me = MazeElement.fromChar(line.charAt(col));
                maze.set(me, row, col);
            }
        }

        return maze;
    }
}
