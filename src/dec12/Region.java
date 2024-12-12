package dec12;

import framework.utils.Coordinate;
import framework.utils.Direction;

import java.util.HashSet;
import java.util.Set;

record Region(Set<Coordinate> locations, Set<Edge> edges) {

    public long area() {
        return locations.size();
    }

    public long sides() {
        Set<Edge> seen = new HashSet<>();
        long sides = 0;
        for (Edge edge : edges) {
            if (seen.contains(edge)) {
                continue;
            }

            sides++;

            // Continue in direction adding every edge you see in that direction to seen
            for (Direction direction : edge.facing().ninetyDegrees()) {
                Set<Coordinate> newEdgeCoordinates = new HashSet<>();
                for (Coordinate edgeCoordinate : edge.adjacent()) {
                    newEdgeCoordinates.add(edgeCoordinate.addCoordinate(direction.toCoordinate()));
                }
                Edge nextEdge = new Edge(newEdgeCoordinates, edge.facing());

                while (edges.contains(nextEdge)) {
                    seen.add(nextEdge);
                    newEdgeCoordinates = new HashSet<>();
                    for (Coordinate edgeCoordinate : nextEdge.adjacent()) {
                        newEdgeCoordinates.add(edgeCoordinate.addCoordinate(direction.toCoordinate()));
                    }
                    nextEdge = new Edge(newEdgeCoordinates, edge.facing());
                }
            }
        }

        return sides;
    }

    public long price() {
        return area() * sides();
    }
}
