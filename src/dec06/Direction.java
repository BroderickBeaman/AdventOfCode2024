package dec06;

enum Direction {
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

    Direction next() {
        return switch(this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }

    Direction opposite() {
        return switch(this) {
            case N -> S;
            case E -> W;
            case S -> N;
            case W -> E;
        };
    }
}
