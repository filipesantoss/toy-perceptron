package filipesantoss.perceptron.input;

import java.util.LinkedList;
import java.util.List;

public class Input {

    static final int MINIMUM_ROW = -100;
    static final int MAXIMUM_ROW = 100;
    static final int MINIMUM_COLUMN = -100;
    static final int MAXIMUM_COLUMN = 100;

    private float column;
    private float row;
    private Group bias;
    private Group group;

    Input(float column, float row) {
        this.column = column;
        this.row = row;
        bias = Group.FIRST;
    }

    public List<Float> valuesAsList() {
        List<Float> values = new LinkedList<>();
        values.add(row);
        values.add(column);
        values.add(getBiasAsFloat());

        return values;
    }

    private float getBiasAsFloat() {
        return Integer.valueOf(bias.asInt()).floatValue();
    }

    float getColumn() {
        return column;
    }

    float getRow() {
        return row;
    }

    public Group getGroup() {
        return group;
    }

    public void defineGroup(Group group) {
        if (this.group == null) {
            this.group = group;
        }
    }

    public static Group findGroup(Input input) {
        return input.column >= input.row ? Group.FIRST : Group.SECOND;
    }
}
