package dec06;

enum Cell {
    VISITED('^'),
    WALL('#'),
    EMPTY('.');

    private Character value;

    Cell(Character value) {
        this.value = value;
    }

    static Cell fromChar(Character charIn) {
        for (Cell cell : values()) {
            if (cell.value.equals(charIn)) {
                return cell;
            }
        }

        throw new RuntimeException("Unsupported Cell: " + charIn);
    }
}
