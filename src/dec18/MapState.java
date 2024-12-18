package dec18;

import framework.utils.Coordinate;

public record MapState(Coordinate position, Integer depth) implements Comparable<MapState> {
    @Override
    public int compareTo(MapState o) {
        return this.depth.compareTo(o.depth);
    }
}
