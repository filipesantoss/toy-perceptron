package filipesantoss.toy_perceptron.graphics;

import filipesantoss.toy_perceptron.util.Numbers;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class CartesianCanvas {

    private static final int PADDING = 10;
    public static final int CELL_SIZE = 10;

    private int centralPoint;
    private Rectangle canvas;
    private Text text;

    public CartesianCanvas(int size) {
        canvas = new Rectangle(PADDING, PADDING, size * CELL_SIZE, size * CELL_SIZE);
        centralPoint = size * CELL_SIZE / 2;

        //Center text horizontally.
        text = new Text(centralPoint - 2 * PADDING, canvas.getHeight() + 2 * PADDING, "");
    }

    /**
     * Initializes the canvas and the text box and draws the X and Y axis.
     */
    public void init() {
        canvas.setColor(Color.GRAY);
        canvas.fill();

        text.draw();

        int left = canvas.getX();
        int right = canvas.getWidth() + PADDING;
        int top = canvas.getY();
        int bottom = canvas.getHeight() + PADDING;

        drawLine(left, centralPoint + PADDING, right, centralPoint + PADDING, Color.WHITE);
        drawLine(centralPoint + PADDING, top, centralPoint + PADDING, bottom, Color.WHITE);
    }

    /**
     * Draws the representable object on the canvas.
     *
     * @param representable - the object.
     */
    public void draw(Representable representable) {
        float x = coordinateToPixelX(representable);
        float y = coordinateToPixelY(representable);

        representable.drawAt(x - CartesianCanvas.CELL_SIZE / 2, y - CartesianCanvas.CELL_SIZE / 2);
    }

    private float coordinateToPixelX(Representable representable) {
        return coordinateToPixelX(representable.getX(), representable.getMinimumX(), representable.getMaximumX());
    }

    private float coordinateToPixelY(Representable representable) {
        return coordinateToPixelY(representable.getY(), representable.getMinimumY(), representable.getMaximumY());
    }

    /**
     * Converts a column value to a pixel value.
     *
     * @param column  - the column value.
     * @param minimum - the value for the minimum possible column.
     * @param maximum - the value for the maximum possible column.
     * @return the pixel's X value.
     */
    public float coordinateToPixelX(float column, float minimum, float maximum) {
        float[] coordinateRange = new float[]{
                minimum, maximum
        };

        float[] canvasRange = new float[]{
                canvas.getX(), canvas.getWidth() + PADDING
        };

        return Numbers.mapRange(column, coordinateRange, canvasRange);
    }

    /**
     * Converts a row value to a pixel value.
     *
     * @param row     - the row value.
     * @param minimum - the value for the minimum possible row.
     * @param maximum - the value for the maximum possible row.
     * @return the pixel's Y value.
     */
    public float coordinateToPixelY(float row, float minimum, float maximum) {
        float[] coordinateRange = new float[]{
                minimum, maximum
        };

        float[] canvasRange = new float[]{
                canvas.getHeight() + PADDING, canvas.getY()
        };

        return Numbers.mapRange(row, coordinateRange, canvasRange);
    }

    public void drawLine(float startingX, float startingY, float endingX, float endingY, Color color) {
        Line line = new Line(startingX, startingY, endingX, endingY);
        line.setColor(color);
        line.draw();
    }

    /**
     * Draws a line from one X axis value to the other, with the specified gradient and intercept.
     *
     * @param initialPixelX - the first pixel's X axis value.
     * @param finalPixelX   - the second pixel's X axis value.
     * @param gradient      - the line's gradient.
     * @param intercept     - the line's intercept .
     * @param color         - the line's color.
     */
    public void drawCartesianLine(float initialPixelX, float finalPixelX, float gradient, float intercept, Color color) {
        float initialX = initialPixelX - PADDING - centralPoint;
        float initialY = Numbers.linePointKnowingX(initialX, gradient, intercept);

        if (initialY < -centralPoint) {
            initialX = Numbers.linePointKnowingY(-centralPoint, gradient, intercept);
            initialY = Numbers.linePointKnowingX(initialX, gradient, intercept);
        }

        initialPixelX = cartesianXToPixelX(initialX) + PADDING;
        float initialPixelY = cartesianYToPixelY(initialY) + PADDING;

        float finalX = finalPixelX - PADDING - centralPoint;
        float finalY = Numbers.linePointKnowingX(finalX, gradient, intercept);

        if (finalY > centralPoint) {
            finalX = Numbers.linePointKnowingY(centralPoint, gradient, intercept);
            finalY = Numbers.linePointKnowingX(finalX, gradient, intercept);
        }

        finalPixelX = cartesianXToPixelX(finalX) + PADDING;
        float finalPixelY = cartesianYToPixelY(finalY) + PADDING;

        drawLine(initialPixelX, initialPixelY, finalPixelX, finalPixelY, color);
    }

    private float cartesianXToPixelX(float cartesianX) {
        return cartesianX + centralPoint;
    }

    private float cartesianYToPixelY(float cartesianY) {
        return centralPoint - cartesianY;
    }

    /**
     * Defines the text box's text.
     *
     * @param text - the text.
     */
    public void setText(String text) {
        this.text.setText(text);
    }

    /**
     * Returns the minimum cartesian value.
     *
     * @return - the value.
     */
    public float getMinimumCartesian() {
        return -centralPoint;
    }

    /**
     * Returns the maximum cartesian value.
     *
     * @return - the value.
     */
    public float getMaximumCartesian() {
        return centralPoint;
    }

    /**
     * Paints the specified object with the specified color.
     *
     * @param representable - the object to paint.
     * @param color         - the color.
     */
    public static void paint(Representable representable, Color color) {
        representable.setColor(color);
    }
}
