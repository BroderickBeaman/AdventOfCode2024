package dec04;

record Coordinate(int row, int col) {

    Coordinate addDirection(Direction direction) {
        return new Coordinate(row + direction.getRow(), col + direction.getCol());
    }

    boolean isValid(int maxRowBound, int maxColBound) {
        return row >= 0 && row < maxRowBound && col >= 0 && col < maxColBound;
    }
}
