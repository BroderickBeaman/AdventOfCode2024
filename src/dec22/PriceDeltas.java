package dec22;

public record PriceDeltas(int d1, int d2, int d3, int d4) {

    @Override
    public String toString() {
        return d1 + "," + d2 + "," + d3 + "," + d4;
    }
}
