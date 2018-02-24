package filipesantoss.perceptron.input;

import filipesantoss.perceptron.graphics.CartesianCanvas;
import filipesantoss.perceptron.graphics.Representable;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

public class GraphicalInput extends Input implements Representable {

    public static final float GRADIENT = 1f;
    public static final float INTERCEPT = 0f;

    private Ellipse representation;

    public GraphicalInput(float column, float row) {
        super(column, row);
    }

    @Override
    public void drawAt(float x, float y) {
        representation = new Ellipse(x, y, CartesianCanvas.SIZE, CartesianCanvas.SIZE);
        representation.fill();
    }

    @Override
    public int getMinimumX() {
        return MINIMUM_COLUMN;
    }

    @Override
    public int getWidthRange() {
        return MAXIMUM_COLUMN - MINIMUM_COLUMN;
    }

    @Override
    public int getMinimumY() {
        return MINIMUM_ROW;
    }

    @Override
    public int getHeightRange() {
        return MAXIMUM_ROW - MINIMUM_ROW;
    }

    @Override
    public float getX() {
        return getColumn();
    }

    @Override
    public float getY() {
        return getRow();
    }

    @Override
    public void setColor(Color color) {
        representation.setColor(color);
    }

    public static Group findGroup(GraphicalInput input, CartesianCanvas canvas) {
        return defineByStraightLine(canvas.fromCartesianX(input)) > canvas.fromCartesianY(input) ? Group.FIRST : Group.SECOND;
    }

    public static float defineByStraightLine(float column) {
        return column * GRADIENT + INTERCEPT;
    }

    public static void drawGroupDelimiter(CartesianCanvas canvas) {
        float columnRange = MAXIMUM_COLUMN - MINIMUM_COLUMN;
        float rowRange = MAXIMUM_ROW - MINIMUM_ROW;

        float startingX = canvas.fromCartesianX(MINIMUM_COLUMN, MINIMUM_COLUMN, columnRange);
        float startingY = defineByStraightLine(canvas.fromCartesianY(MAXIMUM_ROW, MINIMUM_ROW, rowRange));
        float endingX = canvas.fromCartesianX(MAXIMUM_COLUMN, MINIMUM_COLUMN, columnRange);
        float endingY = defineByStraightLine(canvas.fromCartesianY(MINIMUM_ROW, MINIMUM_ROW, rowRange));

        canvas.drawLine(startingX, startingY, endingX, endingY, Color.ORANGE);
    }
}
