package dec16;

record Node(MazePosition position, Long score) implements Comparable<Node> {
    @Override
    public int compareTo(Node o) {
        return this.score.compareTo(o.score);
    }
}
