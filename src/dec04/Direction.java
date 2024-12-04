package dec04;

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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
