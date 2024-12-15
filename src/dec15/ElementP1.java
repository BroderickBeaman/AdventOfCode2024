package dec15;

enum ElementP1 {
    WALL('#'),
    BOX('O'),
    ROBOT('@'),
    EMPTY('.');

    private Character value;

    ElementP1(Character value) {
        this.value = value;
    }

    static ElementP1 fromCharacter(Character character) {
        for (ElementP1 element : values()) {
            if (character.equals(element.value)) {
                return element;
            }
        }

        throw new IllegalArgumentException("Character " + character + " not supported");
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
