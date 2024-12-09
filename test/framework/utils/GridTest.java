package framework.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GridTest {

    @Test
    void rowsAndCols() {
        Integer[][] intArrays = {{1,2,3}, {4,5,6}};
        Grid<Integer> intGrid = new Grid<>(intArrays);
        assertEquals(Integer.valueOf(2), intGrid.rows());
        assertEquals(Integer.valueOf(3), intGrid.cols());
    }

    @Test
    void initWithDefault() {
        Grid<Long> grid = new Grid<>(Long.class, 4, 2);
        grid.initWithDefault(7L);
        for (int row = 0; row < grid.rows(); row++) {
            for (int col = 0; col < grid.cols(); col++) {
                assertEquals(7L, grid.get(row, col));
            }
        }
    }

    @Test
    void isInBounds() {
        Grid<Boolean> grid = new Grid<>(Boolean.class, 5, 6);
        assertTrue(grid.isInBounds(3, 4));
        assertTrue(grid.isInBounds(0, 4));
        assertTrue(grid.isInBounds(4, 4));
        assertTrue(grid.isInBounds(3, 0));
        assertTrue(grid.isInBounds(3, 5));
        assertFalse(grid.isInBounds(-1, 3));
        assertFalse(grid.isInBounds(5, 3));
        assertFalse(grid.isInBounds(3, -1));
        assertFalse(grid.isInBounds(3, 6));
    }

    @Test
    void get() {
        Integer[][] intArrays = {{1,2,3}, {4,5,6}};
        Grid<Integer> grid = new Grid<>(intArrays);
        assertEquals(3, grid.get(0, 2));
    }

    @Test()
    void getOutOfBounds() {
        Integer[][] intArrays = {{1,2,3}, {4,5,6}};
        Grid<Integer> grid = new Grid<>(intArrays);
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(-1, 2));
    }

    @Test
    void set() {
        Integer[][] intArrays = {{1,2,3}, {4,5,6}};
        Grid<Integer> grid = new Grid<>(intArrays);
        grid.set(69, 1, 1);
        assertEquals(69, grid.get(1, 1));
        assertEquals(2, grid.get(0, 1));
    }

    @Test
    void setOutOfBounds() {
        Integer[][] intArrays = {{1,2,3}, {4,5,6}};
        Grid<Integer> grid = new Grid<>(intArrays);
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(69, -1, 1));
    }
}