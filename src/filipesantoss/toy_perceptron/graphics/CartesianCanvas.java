package filipesantoss.toy_perceptron.graphics;

import filipesantoss.toy_perceptron.util.Numbers;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class CartesianCanvas {

    public static final int PADDING = 10;
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

    public void draw(Representable representable) {
        float x = coordinateToPixelX(representable);
        float y = coordinateToPixelY(representable);

        representable.drawAt(x - CartesianCanvas.CELL_SIZE / 2, y - CartesianCanvas.CELL_SIZE / 2);
    }

    public float coordinateToPixelX(Representable representable) {
        return coordinateToPixelX(representable.getX(), representable.getMinimumX(), representable.getMaximumX());
    }

    public float coordinateToPixelY(Representable representable) {
        return coordinateToPixelY(representable.getY(), representable.getMinimumY(), representable.getMaximumY());
    }

    public float coordinateToPixelX(float column, float minimum, float maximum) {
        float[] coordinateRange = new float[]{
                minimum, maximum
        };

        float[] canvasRange = new float[]{
            canvas.getX(), canvas.getWidth() + PADDING
        };

        return Numbers.mapRange(column, coordinateRange, canvasRange);
    }

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

    // TODO: REFACTOR THIS
    public void drawCartesianLine(float initialPixelX, float finalPixelX, float gradient, float intercept, Color color) {
        initialPixelX = pixelXtoCartesianX(initialPixelX - PADDING);
        finalPixelX = pixelXtoCartesianX(finalPixelX - PADDING);

        float initialY = straightLineKnowingX(initialPixelX, gradient, intercept);

        if (initialY < -centralPoint) {
            initialPixelX = straightLineKnowingY(-centralPoint, gradient, intercept);
            initialY = straightLineKnowingX(initialPixelX, gradient, intercept);
        }

        initialPixelX = cartesianXToPixelX(initialPixelX) + PADDING;
        initialY = cartesianYToPixelY(initialY) + PADDING;


        float finalY = straightLineKnowingX(finalPixelX, gradient, intercept);

        if (finalY > centralPoint) {
            finalPixelX = straightLineKnowingY(centralPoint, gradient, intercept);
           finalY = straightLineKnowingX(finalPixelX, gradient, intercept);
        }

        finalPixelX = cartesianXToPixelX(finalPixelX) + PADDING;
        finalY = cartesianYToPixelY(finalY) + PADDING;

        drawLine(initialPixelX, initialY, finalPixelX, finalY, color);
    }

    private float straightLineKnowingX(float x, float gradient, float intercept) {
        return gradient * x + intercept;
    }

    private float straightLineKnowingY(float y, float gradient, float intercept) {
        return (y - intercept) / gradient;
    }

    public float pixelXtoCartesianX(float pixelX) {
        return pixelX - centralPoint;
    }

    private float cartesianXToPixelX(float cartesianX) {
        return cartesianX + centralPoint;
    }

    private float cartesianYToPixelY(float cartesianY) {
        return centralPoint - cartesianY;
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public static void paint(Representable representable, Color color) {
        representable.setColor(color);
    }

    public float getMinimum() {
        return -centralPoint;
    }

    public float getMaximum() {
        return centralPoint;
    }
}
