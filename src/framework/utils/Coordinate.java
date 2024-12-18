package framework.utils;

import java.util.Objects;
import java.util.Set;

/**
 * Class representing the position in a 2-dimensional grid
 */
public class Coordinate {

    /**
     * The row component of the coordinate
     */
    private final Integer row;

    /**
     * The column component of the coordinate
     */
    private final Integer col;

    public Coordinate(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the row component of the coordinate
     * @return The row component of the coordinate
     */
    public Integer row() {
        return row;
    }

    /**
     * Returns the column component of the coordinate
     * @return The column component of the coordinate
     */
    public Integer col() {
        return col;
    }

    /**
     * Generate a new coordinate representing the distance between this coordinate and another
     * @param other The coordinate to compare to
     * @return The coordinate representing the distance between the two coordinates
     */
    public Coordinate distance(Coordinate other) {
        return new Coordinate(other.row - this.row, other.col - this.col);
    }

    /**
     * Generate a new coordinate by taking this coordinate and adding another
     * @param other The other coordinate to add
     * @return The sum of both coordinates
     */
    public Coordinate addCoordinate(Coordinate other) {
        return new Coordinate(this.row + other.row, this.col + other.col);
    }

    /**
     * Generate a new coordinate by moving a single space in the specified direction
     * @param direction The direction to move
     * @return The new coordinate
     */
    public Coordinate addDirection(Direction direction) {
        return addCoordinate(direction.toCoordinate());
    }

    /**
     * Returns a set of coordinates orthogonally adjacent to this coordinate
     * @return The set of orthogonally adjacent coordinates
     */
    public Set<Coordinate> orthogonal() {
        return Set.of(
                new Coordinate(row - 1, col),
                new Coordinate(row + 1, col),
                new Coordinate(row, col - 1),
                new Coordinate(row, col + 1)
        );
    }

    /**
     * Return a new Coordinate with the row value incremented by the specified amount
     * @param row The amount to increment
     * @return The new Coordinate
     */
    public Coordinate addRow(Integer row) {
        return new Coordinate(row() + row, col());
    }

    /**
     * Return a new Coordinate with the col value incremented by the specified amount
     * @param col The amount to increment
     * @return The new Coordinate
     */
    public Coordinate addCol(Integer col) {
        return new Coordinate(row(), col() + col);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(row, that.row) && Objects.equals(col, that.col);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "Row: " + row + " Col: " + col;
    }
}
