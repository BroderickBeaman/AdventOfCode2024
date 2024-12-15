package dec15;

enum ElementP2 {
    WALL('#'),
    BOX_LEFT('['),
    BOX_RIGHT(']'),
    ROBOT('@'),
    EMPTY('.');

    private Character value;

    ElementP2(Character value) {
        this.value = value;
    }

    static ElementP2 fromCharacter(Character character) {
        for (ElementP2 element : values()) {
            if (character.equals(element.value)) {
                return element;
            }
        }

        throw new IllegalArgumentException("Character " + character + " not supported");
    }

    boolean isBox() {
        return this.equals(BOX_LEFT) || this.equals(BOX_RIGHT);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
