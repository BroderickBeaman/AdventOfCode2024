package dec08;

record Coordinate(int row, int col) {

    boolean isInBounds(int maxRowBound, int maxColBound) {
        return row >= 0 && row < maxRowBound && col >= 0 && col < maxColBound;
    }

    Coordinate createAntinode(Coordinate other) {
        Coordinate distance = this.distance(other);
        return new Coordinate(this.row() - distance.row, this.col() - distance.col);
    }

    Coordinate distance(Coordinate other) {
        int rowDiff = other.row() - this.row();
        int colDiff = other.col() - this.col();
        return new Coordinate(rowDiff, colDiff);
    }
}
