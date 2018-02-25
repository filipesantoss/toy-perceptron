package filipesantoss.toy_perceptron.graphics;

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
        return coordinateToPixelX(representable.getX(), representable.getMinimumX(), representable.getWidthRange());
    }

    public float coordinateToPixelY(Representable representable) {
        return coordinateToPixelY(representable.getY(), representable.getMinimumY(), representable.getHeightRange());
    }

    public float coordinateToPixelX(float column, float minimum, float rangeSize) {
        float xRatio = (column - minimum) / rangeSize;

        return xRatio * canvas.getWidth() + PADDING;
    }

    public float coordinateToPixelY(float row, float minimum, float rangeSize) {
        float yRatio = (row - minimum) / rangeSize;

        return canvas.getHeight() - yRatio * canvas.getHeight() + PADDING;
    }

    public void drawLine(float startingX, float startingY, float endingX, float endingY, Color color) {
        Line line = new Line(startingX, startingY, endingX, endingY);
        line.setColor(color);
        line.draw();
    }

    public void drawCartesianLine(float initialX, float finalX, float gradient, float intercept, Color color) {
        initialX = pixelXtoCartesianX(initialX - PADDING);
        finalX = pixelXtoCartesianX(finalX - PADDING);

        float initialY = straightLineKnowingX(initialX, gradient, intercept);

        if (initialY < -centralPoint) {
            initialX = straightLineKnowingY(-centralPoint, gradient, intercept);
            initialY = straightLineKnowingX(initialX, gradient, intercept);
        }

        initialX = cartesianXToPixelX(initialX) + PADDING;
        initialY = cartesianYToPixelY(initialY) + PADDING;

        float finalY = straightLineKnowingX(finalX, gradient, intercept);

        if (finalY > centralPoint) {
            finalX = straightLineKnowingY(centralPoint, gradient, intercept);
            finalY = straightLineKnowingX(finalX, gradient, intercept);
        }

        finalX = cartesianXToPixelX(finalX) + PADDING;
        finalY = cartesianYToPixelY(finalY) + PADDING;

        drawLine(initialX, initialY, finalX, finalY, color);
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
}
