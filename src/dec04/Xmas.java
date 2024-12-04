package dec04;

enum Xmas {
    X,M,A,S;

    static Xmas fromCharacter(Character c) {
        return switch (c) {
            case 'X' -> X;
            case 'M' -> M;
            case 'A' -> A;
            case 'S' -> S;
            default -> null;
        };
    }

    Xmas next() {
        return switch (this) {
            case X -> M;
            case M -> A;
            case A -> S;
            case S -> null;
        };
    }
}
