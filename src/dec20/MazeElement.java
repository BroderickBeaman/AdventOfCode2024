package dec20;

enum MazeElement {
    WALL('#'),
    EMPTY('.'),
    START('S'),
    END('E');

    private Character value;

    MazeElement(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }

    static MazeElement fromChar(Character c) {
        for (MazeElement mazeElement : values()) {
            if (mazeElement.value.equals(c)) {
                return mazeElement;
            }
        }

        throw new IllegalArgumentException("Unsupported Character: " + c);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
