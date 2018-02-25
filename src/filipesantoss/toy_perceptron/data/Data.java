package filipesantoss.toy_perceptron.data;

import java.util.LinkedList;
import java.util.List;

public class Data {

    // Limit values used to make other proportional representation of data.
    static final int MINIMUM_ROW = -100;
    static final int MAXIMUM_ROW = 100;
    static final int MINIMUM_COLUMN = -100;
    static final int MAXIMUM_COLUMN = 100;

    private float column;
    private float row;
    private Group bias;
    private Group group;

    /**
     * Data represents a point in a grid that can be categorized in two groups.
     * Each instance of Data will have a bias to a group that will be used by a perceptron when trying to classify it.
     * The value of the bias is arbitrary.
     *
     * @param column - the column occupied in the grid.
     * @param row    - the row occupied in the grid.
     */
    Data(float column, float row) {
        this.column = column;
        this.row = row;
        bias = Group.FIRST;
    }

    /**
     * Returns a list containing all the information in the object.
     *
     * @return the list of values.
     */
    public List<Float> getAsList() {
        List<Float> values = new LinkedList<>();
        values.add(row);
        values.add(column);
        values.add(getBiasAsFloat());

        return values;
    }

    private float getBiasAsFloat() {
        return Integer.valueOf(bias.asInt()).floatValue();
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

    /**
     * Defines the group that categorizes the object, if it's not yet defined.
     *
     * @param group - the group that should categorize the object.
     */
    public void defineGroup(Group group) {
        if (this.group == null) {
            this.group = group;
        }
    }

    /**
     * Defines the group that should categorize the specified object, based on whatever wanted function.
     *
     * @param data - the object.
     * @return the group that should categorize the specified object.
     */
    public static Group findGroup(Data data) {
        return data.column >= data.row ? Group.FIRST : Group.SECOND;
    }
}
