package filipesantoss.perceptron.util;

public class Random {

    public static float createFloat(float minimum, float maximum) {
        return (float) (Math.random() * (maximum - minimum) + minimum);
    }
}
