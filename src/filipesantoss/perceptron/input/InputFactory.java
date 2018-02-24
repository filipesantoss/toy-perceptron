package filipesantoss.perceptron.input;

import filipesantoss.perceptron.graphics.CartesianCanvas;
import filipesantoss.perceptron.util.Random;

public class InputFactory {

    public static Input random() {
        Input toReturn = new Input(randomColumn(), randomRow());
        toReturn.defineGroup(Input.findGroup(toReturn));

        return toReturn;
    }

    public static GraphicalInput randomRepresentable(CartesianCanvas canvas) {
        GraphicalInput toReturn = new GraphicalInput(randomColumn(), randomRow());
        toReturn.defineGroup(GraphicalInput.findGroup(toReturn, canvas));

        return toReturn;
    }

    private static float randomRow() {
        return Random.createFloat(Input.MINIMUM_ROW, Input.MAXIMUM_ROW);
    }

    private static float randomColumn() {
        return Random.createFloat(Input.MINIMUM_COLUMN, Input.MAXIMUM_COLUMN);
    }
}
