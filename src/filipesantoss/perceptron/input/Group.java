package filipesantoss.perceptron.input;

public enum Group {
    FIRST(1),
    SECOND(-1);

    private int intValue;

    Group(int intValue) {
        this.intValue = intValue;
    }

    public int asInt() {
        return intValue;
    }
}
