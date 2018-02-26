package filipesantoss.toy_perceptron.data;

public enum Group {
    FIRST(100f),
    SECOND(-100f);

    /**
     * A numeric representation of the group used by the perceptron to calculate the error when in training.
     * This value equals the limit values for the inputs of that group.
     */
    private float value;

    Group(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
