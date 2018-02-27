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
        centralPoint = size / 2 * CELL_SIZE;

        //Center text horizontally.
        text = new Text(centralPoint - 2 * PADDING, canvas.getHeight() + 2 * PADDING, "");
    }

    /**
     * Initializes the canvas and the text box and draws the X and Y axis.
     */
    public void init() {
        canvas.setColor(Color.GRAY);
        canvas.fill();

        drawAxis();
        text.draw();
    }

    private void drawAxis() {
        float[] left = {PADDING, centralPoint + PADDING};
        float[] right = {canvas.getWidth() + PADDING, centralPoint + PADDING};

        float[] top = {centralPoint + PADDING, PADDING};
        float[] bottom = {centralPoint + PADDING, canvas.getHeight() + PADDING};

        drawLine(left, right, Color.WHITE);
        drawLine(top, bottom, Color.WHITE);
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
        float[] coordinateRange = {minimum, maximum};
        float[] canvasRange = {canvas.getX(), canvas.getWidth() + PADDING};

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
    private float coordinateToPixelY(float row, float minimum, float maximum) {
        float[] coordinateRange = {minimum, maximum};
        float[] canvasRange = {canvas.getHeight() + PADDING, canvas.getY()};

        return Numbers.mapRange(row, coordinateRange, canvasRange);
    }

    private void drawLine(float[] startingPixel, float[] endingPixel, Color color) {
        Line line = new Line(startingPixel[0], startingPixel[1], endingPixel[0], endingPixel[1]);
        line.setColor(color);
        line.draw();
    }

    // TODO: REFACTOR. POINT VALIDATOR?
    public void drawCartesianLine(float initialPixelX, float finalPixelX, float gradient, float intercept) {
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

        drawLine(new float[]{initialPixelX, initialPixelY}, new float[]{finalPixelX, finalPixelY}, Color.BLACK);
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
