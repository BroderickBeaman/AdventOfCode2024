package dec16;

record NodeP1(MazePosition position, Long score) implements Comparable<NodeP1> {
    @Override
    public int compareTo(NodeP1 o) {
        return this.score.compareTo(o.score);
    }
}
