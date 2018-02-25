package filipesantoss.toy_perceptron.data;

import filipesantoss.toy_perceptron.graphics.CartesianCanvas;
import filipesantoss.toy_perceptron.graphics.Representable;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

public class GraphicalData extends Data implements Representable {

    private static final float GRADIENT = 2f;
    private static final float INTERCEPT = 0f;

    private Ellipse representation;

    public GraphicalData(float column, float row) {
        super(column, row);
    }

    /**
     * Creates a graphic representation in a two-dimensional canvas for the data in the specified position.
     *
     * @param x - the X axis value.
     * @param y - the Y axis value.
     */
    @Override
    public void drawAt(float x, float y) {
        representation = new Ellipse(x, y, CartesianCanvas.CELL_SIZE, CartesianCanvas.CELL_SIZE);
        representation.fill();
    }

    /**
     * Returns the minimum possible value used to calculate the representation's X axis value.
     *
     * @return - the minimum value.
     */
    @Override
    public int getMinimumX() {
        return MINIMUM_COLUMN;
    }

    /**
     * Returns the total number of values that can be used to calculate the representation's X axis value.
     *
     * @return - the number of values.
     */
    @Override
    public int getWidthRange() {
        return MAXIMUM_COLUMN - MINIMUM_COLUMN;
    }

    /**
     * Returns the minimum possible value used to calculate the representation's Y axis value.
     *
     * @return - the minimum value.
     */
    @Override
    public int getMinimumY() {
        return MINIMUM_ROW;
    }

    /**
     * Returns the total number of values that can be used to calculate the representation's Y axis value.
     *
     * @return - the number of values.
     */
    @Override
    public int getHeightRange() {
        return MAXIMUM_ROW - MINIMUM_ROW;
    }

    /**
     * Returns the value used to calculate the real representation's X axis value.
     *
     * @return - the value to be used.
     */
    @Override
    public float getX() {
        return getColumn();
    }

    /**
     * Returns the value used to calculate the real representation's Y axis value.
     *
     * @return - the value to be used.
     */
    @Override
    public float getY() {
        return getRow();
    }

    @Override
    public void setColor(Color color) {
        representation.setColor(color);
    }

    /**
     * Defines the group that categorizes the data based on its representation's coordinates on the canvas.
     *
     * @param input  - the data to be categorized.
     * @param canvas - the canvas used to calculate the coordinates.
     * @return the group that categorizes the data.
     */
    public static Group findGroup(GraphicalData input, CartesianCanvas canvas) {
        return input.getRow() > defineByStraightLine(input.getColumn()) ? Group.FIRST : Group.SECOND;
    }

    /**
     * Returns the Y axis value corresponding to the X axis value in a straight line.
     * The equation for a straight line is commonly known as y = mx + b.
     *
     * @param x - the X axis value.
     * @return - the Y axis value.
     */
    public static float defineByStraightLine(float x) {
        return GRADIENT * x + INTERCEPT;
    }

    /**
     * Creates a line representing the groups' threshold values in a canvas.
     *
     * @param canvas - the canvas to be used.
     */
    public static void drawGroupDelimiter(CartesianCanvas canvas) {
        float columnRange = MAXIMUM_COLUMN - MINIMUM_COLUMN;

        float startingX = canvas.coordinateToPixelX(MINIMUM_COLUMN, MINIMUM_COLUMN, columnRange);
        float endingX = canvas.coordinateToPixelX(MAXIMUM_COLUMN, MINIMUM_COLUMN, columnRange);

        canvas.drawCartesianLine(startingX, endingX, GRADIENT, INTERCEPT, Color.BLACK);
    }
}
