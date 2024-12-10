package framework.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Utility class to assist in grid based problems
 * @param <T> The type of the values to insert into the grid
 */
public class Grid<T> {

    private T[][] grid;

    public Grid(T[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("grid must not be null");
        }
        this.grid = grid;
    }

    @SuppressWarnings("unchecked")
    public Grid(Class<? extends T> clazz, Integer rows, Integer cols) {
        if (rows == null || cols == null) {
            throw new IllegalArgumentException("rows and cols must not be null");
        }

        if (rows < 1 || cols < 1) {
            throw new IllegalArgumentException("rows and cols must be at least 1");
        }

        this.grid = (T[][]) Array.newInstance(clazz, rows, cols);
    }

    /**
     * Returns the number of rows in the grid
     * @return The number of rows in the grid
     */
    public Integer rows() {
        return grid.length;
    }

    /**
     * Returns the number of columns in the grid
     * @return The number of columns in the grid
     */
    public Integer cols() {
        return grid[0].length;
    }

    /**
     * If required, return the underlying 2-dimensional array
     * @return The 2-dimensional array this class wraps
     */
    public T[][] grid() {
        return grid;
    }

    /**
     * Place some default value in every position in the grid
     * @param defaultValue The value to insert
     */
    public void initWithDefault(T defaultValue) {
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                grid[row][col] = defaultValue;
            }
        }
    }

    /**
     * Determines if the provided coordinate resides within the bounds of the grid
     * @param coordinate The coordinate to validate
     * @return true if the coordinate resides within the bounds of the grid
     */
    public Boolean isInBounds(Coordinate coordinate) {
        return isInBounds(coordinate.row(), coordinate.col());
    }

    /**
     * Determines if the provided coordinate resides within the bounds of the grid
     * @param row The row portion of the coordinate
     * @param col The column portion of the coordinate
     * @return true if the coordinate resides within the bounds of the grid
     */
    public Boolean isInBounds(Integer row, Integer col) {
        return row >= 0 && row < rows() && col >= 0 && col < cols();
    }

    /**
     * Returns the object stored at the specified location in the grid
     * @param coordinate The location to look up
     * @throws IndexOutOfBoundsException Thrown when the location is out of bounds
     * @return The object store at the specified location in the grid
     */
    public T get(Coordinate coordinate) throws IllegalArgumentException {
        return get(coordinate.row(), coordinate.col());
    }

    /**
     * Returns the object stored at the specified location in the grid
     * @param row The row portion of the coordinate
     * @param col The column portion of the coordinate
     * @throws IndexOutOfBoundsException Thrown when the location is out of bounds
     * @return The object store at the specified location in the grid
     */
    public T get(Integer row, Integer col) throws IllegalArgumentException {
        if (isInBounds(row, col)) {
            return grid[row][col];
        }
        throw new IndexOutOfBoundsException("(" + row + "," + col + ") does not reside within the grid");
    }

    /**
     * Inserts a value into the grid at a specified location
     * @param value The value to insert
     * @param coordinate The location to insert the value
     * @throws IndexOutOfBoundsException Thrown when the location is out of bounds
     */
    public void set(T value, Coordinate coordinate) {
        set(value, coordinate.row(), coordinate.col());
    }

    /**
     * Inserts a value into the grid at a specified location
     * @param value The value to insert
     * @param row The row to insert the value into
     * @param col The column to insert the value into
     * @throws IndexOutOfBoundsException Thrown when the location is out of bounds
     */
    public void set(T value, Integer row, Integer col) {
        if (isInBounds(row, col)) {
            grid[row][col] = value;
            return;
        }
        throw new IndexOutOfBoundsException("(" + row + "," + col + ") does not reside within the grid");
    }

    /**
     * Finds all locations where a specified value is located
     * @param value The value to search for
     * @return All locations where that value is located
     */
    public List<Coordinate> findValue(T value) {
        List<Coordinate> locations = new ArrayList<>();
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                if (Objects.equals(get(row, col), value)) {
                    locations.add(new Coordinate(row, col));
                }
            }
        }
        return locations;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Grid<?> grid1 = (Grid<?>) o;
        return Objects.deepEquals(grid, grid1.grid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }
}
