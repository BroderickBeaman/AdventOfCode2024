package dec04;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    static final Map<Direction, Direction> OPPOSITES;
    static final Set<Direction> DIAGONALS;
    static final Map<Direction, Set<Direction>> CORNERS;

    static {
        OPPOSITES = new HashMap<>();
        for (Direction direction : Direction.values()) {
            OPPOSITES.put(direction, direction.opposite());
        }

        DIAGONALS = Set.of(NW, NE, SW, SE);

        CORNERS = new HashMap<>();

        for (Direction direction : DIAGONALS) {
            Set<Direction> tempDiagonals = new HashSet<>(DIAGONALS);
            tempDiagonals.remove(direction);
            tempDiagonals.remove(OPPOSITES.get(direction));
            CORNERS.put(direction, tempDiagonals);
        }
    }

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

    private static Direction fromRowCol(int row, int col) {
        for (Direction direction : values()) {
            if (row == direction.getRow() && col == direction.getCol()) {
                return direction;
            }
        }
        throw new RuntimeException("Unrecognized direction");
    }

    private Direction opposite() {
        return fromRowCol(this.row * -1, this.col * -1);
    }
}
