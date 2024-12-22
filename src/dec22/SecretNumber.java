package dec22;

import java.util.Objects;

class SecretNumber {

    private long value;

    SecretNumber(long value) {
        this.value = value;
    }

    SecretNumber(String value) {
        this(Long.parseLong(value));
    }

    long getValue() {
        return value;
    }

    int getCurrentPrice() {
        return (int) (value % 10);
    }

    void nextValue() {
        step1();
        step2();
        step3();
    }

    private void step1() {
        mix(value * 64L);
        prune();
    }

    private void step2() {
        mix(value / 32L);
        prune();
    }

    private void step3() {
        mix(value * 2048);
        prune();
    }

    private void mix(long given) {
        value = given ^ value;
    }

    private void prune() {
        value = value % 16777216L;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SecretNumber that = (SecretNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
