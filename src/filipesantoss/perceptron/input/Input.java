package filipesantoss.perceptron.input;

import filipesantoss.perceptron.util.Group;

import java.util.LinkedList;
import java.util.List;

public class Input {

    private float column;
    private float row;
    private Group bias;
    private Group group;

    public Input(float row, float column) {
        this.row = row;
        this.column = column;
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

    public Group getGroup() {
        return group;
    }
}
