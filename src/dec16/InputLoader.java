package dec16;

import framework.InputLoaderParent;
import framework.utils.Grid;

import java.util.List;

class InputLoader extends InputLoaderParent {
    static Grid<Maze> loadMaze() {
        List<String> lines = loadLines();
        final int rows = lines.size();
        final int cols = lines.getFirst().length();

        Maze[][] maze = new Maze[rows][cols];

        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                maze[row][col] = Maze.fromChar(line.charAt(col));
            }
        }

        return new Grid<>(maze);
    }
}
