package dec21;

import framework.utils.Coordinate;

record PathState(Coordinate location, String pathSoFar) implements Comparable<PathState> {
    @Override
    public int compareTo(PathState o) {
        return Integer.compare(pathSoFar.length(), o.pathSoFar.length());
    }
}
