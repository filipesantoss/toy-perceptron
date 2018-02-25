package filipesantoss.toy_perceptron.data;

public enum Group {
    FIRST(10),
    SECOND(-10);

    /**
     * A numeric representation of the group used by the perceptron to calculate the error while learning.
     * This value is arbitrary, as long as it's unique for every group.
     */
    private int intValue;

    Group(int intValue) {
        this.intValue = intValue;
    }

    public int asInt() {
        return intValue;
    }
}
