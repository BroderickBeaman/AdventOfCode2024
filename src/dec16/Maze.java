package dec16;

enum Maze {
    WALL('#'),
    EMPTY('.'),
    SOURCE('S'),
    TARGET('E');

    private final Character value;

    Maze(Character value) {
        this.value = value;
    }

    static Maze fromChar(Character character) {
        for (Maze maze : values()) {
            if (maze.value.equals(character)) {
                return maze;
            }
        }
        throw new IllegalArgumentException("Unsupported Character: " + character);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
