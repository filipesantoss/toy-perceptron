package filipesantoss.toy_perceptron.data;

public enum Group {
    FIRST(0.01f),
    SECOND(-0.01f);

    /**
     * A numeric representation of the group used by the perceptron to calculate the error when in training.
     * This value is arbitrary, as long as one is positive and the other is negative.
     *
     * Numbers with high absolute value result in high input weight oscillation during perceptron training,
     * which may cause the perceptron to fail when categorizing input that was previously categorized correctly.
     */
    private float value;

    Group(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
