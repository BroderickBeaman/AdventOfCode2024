package dec14;

import framework.utils.Coordinate;

record Robot(Coordinate position, Coordinate velocity) {

    Coordinate computeNewPosition(long numCycles, int gridRows, int gridCols) {

        long rowTravel = (velocity.row() * numCycles) % gridRows;
        long colTravel = (velocity.col() * numCycles) % gridCols;

        long rowPos = position().row() + rowTravel;
        if (rowPos < 0) {
            rowPos = gridRows + rowPos;
        }
        rowPos = rowPos % gridRows;

        long colPos = position().col() + colTravel;
        if (colPos < 0) {
            colPos = gridCols + colPos;
        }
        colPos = colPos % gridCols;

        return new Coordinate((int) rowPos, (int) colPos);
    }
}
