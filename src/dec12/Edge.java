package dec12;

import framework.utils.Coordinate;
import framework.utils.Direction;

import java.util.Set;

record Edge(Set<Coordinate> adjacent, Direction facing) {
    Edge {
        if (adjacent.size() != 2) {
            throw new IllegalArgumentException("An edge must have two adjacent coordinates");
        }
    }
}
