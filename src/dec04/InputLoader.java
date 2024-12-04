package dec04;

import framework.InputLoaderParent;

import java.util.List;

class InputLoader extends InputLoaderParent {

    static Xmas[][] loadPuzzleGrid() {
        List<String> lines = loadLines();

        Xmas[][] puzzleGrid = new Xmas[lines.size()][lines.get(0).length()];

        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length() ; col++) {

                puzzleGrid[row][col] = Xmas.fromCharacter(line.charAt(col));
            }
        }

        return puzzleGrid;
    }
}
