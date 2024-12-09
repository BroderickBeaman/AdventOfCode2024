package framework.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void rowAndCol() {
        Coordinate coordinate = new Coordinate(5, 6);
        assertEquals(5, coordinate.row());
        assertEquals(6, coordinate.col());
    }

    @Test
    void distance() {
        Coordinate first = new Coordinate(2, 3);
        Coordinate second = new Coordinate(6, 10);
        assertEquals(new Coordinate(4, 7), first.distance(second));
    }

    @Test
    void addCoordinate() {
        Coordinate first = new Coordinate(7, 3);
        Coordinate second = new Coordinate(1, 2);
        assertEquals(new Coordinate(8, 5), first.addCoordinate(second));
    }

}