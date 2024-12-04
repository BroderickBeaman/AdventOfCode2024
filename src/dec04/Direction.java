package dec04;

import java.util.Set;

enum Direction {
    N(-1,0),
    S(1, 0),
    E(0, 1),
    W(0, -1),
    NW(-1, -1),
    NE(-1, 1),
    SW(1, -1),
    SE(1, 1);

    private int row;
    private int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    Direction fromRowCol(int row, int col) {
        for (Direction direction : values()) {
            if (row == this.row && col == this.col) {
                return direction;
            }
        }
        throw new RuntimeException("Unrecognized direction");
    }

    Direction opposite() {
        return fromRowCol(this.row * -1, this.col * -1);
    }

    Set<Direction> ninetyDegrees() {
        //TODO fix this
        return null;
    }
}
