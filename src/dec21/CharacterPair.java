package dec21;

public record CharacterPair(Character a, Character b) {

    @Override
    public String toString() {
        return "Source: " + a + " Target: " + b;
    }
}
