package filipesantoss.toy_perceptron.util;

public class Numbers {

    /**
     * Returns a random number between the specified values.
     *
     * @param minimum - the minimum value.
     * @param maximum - the maximum value.
     * @return the random number.
     */
    public static float createFloat(float minimum, float maximum) {
        return (float) (Math.random() * (maximum - minimum) + minimum);
    }

    /**
     * Maps a given value within a range to the corresponding value in another range.
     *
     * @param value         - the value to map.
     * @param originalRange - the original value's range.
     * @param newRange      - the new value's range.
     * @return the mapped value.
     */
    public static float mapRange(float value, float[] originalRange, float[] newRange) {
        if (originalRange.length != 2 || newRange.length != 2) {
            throw new IllegalArgumentException("Ranges should contain two values.");
        }

        float valueRatio = (value - originalRange[0]) / (originalRange[1] - originalRange[0]);

        return valueRatio * (newRange[1] - newRange[0]) + newRange[0];
    }

    /**
     * Returns the Y axis value of a point with the X axis value in a line with the specified gradient and intercept.
     * y = m * x + b
     *
     * @param x         - the X axis value.
     * @param gradient  - the gradient.
     * @param intercept - the intercept.
     * @return the Y axis value.
     */
    public static float linePointKnowingX(float x, float gradient, float intercept) {
        return gradient * x + intercept;
    }

    /**
     * Returns the X axis value of a point with the Y axis value in a line with the specified gradient and intercept.
     * x = (y - b) / m
     *
     * @param y         - the Y axis value.
     * @param gradient  - the gradient.
     * @param intercept - the intercept.
     * @return the Y axis value.
     */
    public static float linePointKnowingY(float y, float gradient, float intercept) {
        return (y - intercept) / gradient;
    }
}
