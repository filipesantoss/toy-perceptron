package filipesantoss.perceptron.input;

import filipesantoss.perceptron.util.Random;

public class InputFactory {

    public static Input random() {
        return new Input(randomColumn(), randomRow());
    }
    
    private static float randomRow() {
        return Random.createFloat(Input.MINIMUM_ROW, Input.MAXIMUM_ROW);
    }

    private static float randomColumn() {
        return Random.createFloat(Input.MINIMUM_COLUMN, Input.MAXIMUM_COLUMN);
    }
}