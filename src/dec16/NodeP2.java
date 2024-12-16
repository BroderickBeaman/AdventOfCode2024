package dec16;

record NodeP2(MazePosition position, MazePosition previous, Long score) implements Comparable<NodeP2> {
    @Override
    public int compareTo(NodeP2 o) {
        return this.score.compareTo(o.score);
    }
}
