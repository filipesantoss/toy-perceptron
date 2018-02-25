package filipesantoss.toy_perceptron.util;

public class Random {

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
}
