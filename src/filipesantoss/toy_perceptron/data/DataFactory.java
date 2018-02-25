package filipesantoss.toy_perceptron.data;

import filipesantoss.toy_perceptron.graphics.CartesianCanvas;
import filipesantoss.toy_perceptron.util.Numbers;

public class DataFactory {

    /**
     * Returns a Data instance with random values and a defined group that categorizes it.
     *
     * @return the instance.
     */
    public static Data random() {
        Data toReturn = new Data(randomColumn(), randomRow());
        toReturn.defineGroup(Data.findGroup(toReturn));

        return toReturn;
    }

    /**
     * Returns a drawable Data instance with random values and a defined group that categorizes it,
     * based on its' representation on a cartesian grid.
     *
     * @return the instance.
     */
    public static GraphicalData randomRepresentable(CartesianCanvas canvas) {
        GraphicalData toReturn = new GraphicalData(randomColumn(), randomRow());
        toReturn.defineGroup(GraphicalData.findGroup(toReturn, canvas));

        return toReturn;
    }

    private static float randomRow() {
        return Numbers.createFloat(Data.MINIMUM_ROW, Data.MAXIMUM_ROW);
    }

    private static float randomColumn() {
        return Numbers.createFloat(Data.MINIMUM_COLUMN, Data.MAXIMUM_COLUMN);
    }
}
