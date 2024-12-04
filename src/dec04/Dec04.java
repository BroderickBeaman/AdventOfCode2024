package dec04;

import framework.AOCParent;

public class Dec04 extends AOCParent {

    private int rowSize = 0;
    private int colSize = 0;

    @Override
    public void part1() {
        Xmas[][] puzzleGrid = InputLoader.loadPuzzleGrid();
        rowSize = puzzleGrid.length;
        colSize = puzzleGrid[0].length;

        long totalScore = 0;

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (puzzleGrid[row][col] == Xmas.X) {
                    Coordinate current = new Coordinate(row, col);
                    for (Direction direction : Direction.values()) {
                        Coordinate next = current.addDirection(direction);
                        Xmas target = Xmas.M;
                        while (next.isValid(rowSize, colSize) && target != null) {
                            if (puzzleGrid[next.row()][next.col()] == target) {
                                target = target.next();
                                next = next.addDirection(direction);
                            } else {
                                break;
                            }
                        }
                        if (target == null) {
                            totalScore++;
                        }

                    }
                }
            }
        }

        printSolution(totalScore);
    }

    @Override
    public void part2() {

    }
}
