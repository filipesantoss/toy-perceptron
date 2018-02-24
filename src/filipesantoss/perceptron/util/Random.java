package filipesantoss.perceptron.util;

public class Random {

    public static int createInt(int minimum, int maximum) {
        return (int) (Math.random() * (maximum - minimum) + minimum);
    }

    public static float createFloat(float minimum, float maximum) {
        return (float) (Math.random() * (maximum - minimum) + minimum);
    }
}
