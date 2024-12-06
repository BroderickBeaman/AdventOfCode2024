package dec06;

import framework.AOCParent;

import java.util.HashSet;
import java.util.Set;

public class Dec06 extends AOCParent {

    @Override
    public void part1() {
        Cell[][] puzzleGrid = InputLoader.loadPuzzleGrid();

        // The space the guards starts on is visited
        long numVisited = 1;
        Direction direction = Direction.N;

        int row = 0;
        int col = 0;

        // Find the guard
        boolean foundGuard = false;
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[0].length; j++) {
                if (puzzleGrid[i][j] == Cell.VISITED) {
                    row = i; col = j; foundGuard = true; break;
                }
            }
            if (foundGuard)
                break;
        }

        while(isInBounds(puzzleGrid, row, col)) {
            if (puzzleGrid[row][col] == Cell.EMPTY) {
                numVisited++;
                puzzleGrid[row][col] = Cell.VISITED;
            } else if (puzzleGrid[row][col] == Cell.WALL) {
                // Reverse and change directions
                row = row + direction.opposite().getRow();
                col = col + direction.opposite().getCol();
                direction = direction.next();
            }

            row = row + direction.getRow();
            col = col + direction.getCol();
        }

        printSolution(numVisited);
    }


    @Override
    public void part2() {
        Cell[][] puzzleGrid = InputLoader.loadPuzzleGrid();

        Direction direction = Direction.N;

        int guardRow = 0;
        int guardCol = 0;

        // Find the guard
        boolean foundGuard = false;
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[0].length; j++) {
                if (puzzleGrid[i][j] == Cell.VISITED) {
                    guardRow = i; guardCol = j; foundGuard = true; break;
                }
            }
            if (foundGuard)
                break;
        }

        long numObstructions = 0;

        for (int row = 0; row < puzzleGrid.length; row++) {
            for (int col = 0; col < puzzleGrid[0].length; col++) {
                if (puzzleGrid[row][col] == Cell.EMPTY) {
                    puzzleGrid[row][col] = Cell.WALL;
                    if (detectLoop(puzzleGrid, guardRow, guardCol)) {
                        numObstructions++;
                    }
                    puzzleGrid[row][col] = Cell.EMPTY;
                }
            }
        }

        printSolution(numObstructions);
    }

    private boolean isInBounds(Cell[][] puzzleGrid, int row, int col) {
        return row >= 0 && row < puzzleGrid.length && col >= 0 && col < puzzleGrid[0].length;
    }

    private boolean detectLoop(Cell[][] puzzleGrid, int row, int col) {
        Set<Visited> visitedSet = new HashSet<>();
        Direction direction = Direction.N;

        while(isInBounds(puzzleGrid, row, col)) {
            if (puzzleGrid[row][col] == Cell.EMPTY || puzzleGrid[row][col] == Cell.VISITED) {
                Visited visited = new Visited(direction, row, col);
                if (visitedSet.contains(visited)) {
                    return true;
                }
                visitedSet.add(visited);
            } else if (puzzleGrid[row][col] == Cell.WALL) {
                // Reverse and change directions
                row = row + direction.opposite().getRow();
                col = col + direction.opposite().getCol();
                direction = direction.next();
            }

            row = row + direction.getRow();
            col = col + direction.getCol();
        }
        return false;
    }

}
