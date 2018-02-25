package filipesantoss.toy_perceptron.data;

public enum Group {
    FIRST(0.1f),
    SECOND(-0.1f);

    /**
     * A numeric representation of the group used by the perceptron to calculate the error while learning.
     * This value is arbitrary, as long as one is positive and the other is negative.
     * Values with high absolute value result in high input weight oscillation during perceptron training.
     */
    private float value;

    Group(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
