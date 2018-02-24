package filipesantoss.perceptron.input;

import filipesantoss.perceptron.util.Group;

import java.util.LinkedList;
import java.util.List;

public class Input {

    public static final int MINIMUM_ROW = -100;
    public static final int MAXIMUM_ROW = 100;
    public static final int MINIMUM_COLUMN = -100;
    public static final int MAXIMUM_COLUMN = 100;

    private float column;
    private float row;
    private Group bias;
    private Group group;

    public Input(float column, float row) {
        this.column = column;
        this.row = row;
        bias = Group.FIRST;
        group = row < column ? Group.FIRST : Group.SECOND;
    }

    public List<Float> valuesAsList() {
        float biasAsFloat = Integer.valueOf(bias.asInt()).floatValue();

        List<Float> values = new LinkedList<>();
        values.add(row);
        values.add(column);
        values.add(biasAsFloat);

        return values;
    }

    public float getColumn() {
        return column;
    }

    public float getRow() {
        return row;
    }

    public Group getGroup() {
        return group;
    }
}
