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
        Xmas[][] puzzleGrid = InputLoader.loadPuzzleGrid();
        rowSize = puzzleGrid.length;
        colSize = puzzleGrid[0].length;

        long totalScore = 0;

        for (int row = 1; row < rowSize - 1; row++) {
            for (int col = 1; col < colSize - 1; col++) {
               if (checkForCross(puzzleGrid, row, col)) {
                   totalScore++;
               }
            }
        }

        printSolution(totalScore);
    }

    private static boolean checkForCross(Xmas[][] puzzleGrid, int row, int col) {
        if (puzzleGrid[row][col] == Xmas.A) {
            Coordinate aPos = new Coordinate(row, col);
            for (Direction direction : Direction.DIAGONALS) {
                Coordinate mPos = aPos.addDirection(direction);

                if (puzzleGrid[mPos.row()][mPos.col()] != Xmas.M) {
                    continue;
                }

                Coordinate opposite = aPos.addDirection(Direction.OPPOSITES.get(direction));

                if (puzzleGrid[opposite.row()][opposite.col()] != Xmas.S) {
                    continue;
                }

                for (Direction corner : Direction.CORNERS.get(direction)) {
                    Coordinate cornerPos = aPos.addDirection(corner);
                    Coordinate oppositeCornerPos = aPos.addDirection(Direction.OPPOSITES.get(corner));
                    if (puzzleGrid[cornerPos.row()][cornerPos.col()] == Xmas.M
                            && puzzleGrid[oppositeCornerPos.row()][oppositeCornerPos.col()] == Xmas.S) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
