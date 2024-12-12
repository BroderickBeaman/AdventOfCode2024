package framework.utils;

import java.util.Set;

public enum Direction {
    N(-1, 0),
    E(0, 1),
    S(1, 0),
    W(0, -1);

    private int row;
    private int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Set<Direction> ninetyDegrees() {
        return switch (this) {
            case N, S -> Set.of(E, W);
            case E, W -> Set.of(N, S);
        };
    }

    public Coordinate toCoordinate() {
        return new Coordinate(row, col);
    }
}
