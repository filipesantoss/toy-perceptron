package filipesantoss.perceptron.graphics;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class CartesianCanvas {

    public static final int PADDING = 10;
    public static final int SIZE = 10;

    private int centerX;
    private int centerY;
    private Rectangle canvas;
    private Text text;

    public CartesianCanvas(int width, int height) {
        canvas = new Rectangle(PADDING, PADDING, width * SIZE, height * SIZE);
        centerX = canvas.getWidth() / 2;
        centerY = canvas.getHeight() / 2;

        text = new Text(centerX - 2 * PADDING, canvas.getHeight() + 2 * PADDING, "");
    }

    public void init() {
        canvas.setColor(Color.GRAY);
        canvas.fill();

        text.draw();

        int left = canvas.getX();
        int right = canvas.getWidth() + PADDING;
        int top = canvas.getY();
        int bottom = canvas.getHeight() + PADDING;

        drawLine(left, centerY + PADDING, right, centerY + PADDING, Color.WHITE);
        drawLine(centerX + PADDING, top, centerX + PADDING, bottom, Color.WHITE);
    }

    public void draw(Representable representable) {
        float x = fromCartesianX(representable);
        float y = fromCartesianY(representable);

        representable.drawAt(x - CartesianCanvas.SIZE / 2, y - CartesianCanvas.SIZE / 2);
    }

    public float fromCartesianX(Representable representable) {
        return fromCartesianX(representable.getX(), representable.getMinimumX(), representable.getWidthRange());
    }

    public float fromCartesianY(Representable representable) {
        return fromCartesianY(representable.getY(), representable.getMinimumY(), representable.getHeightRange());
    }

    public float fromCartesianX(float x, float minimum, float rangeSize) {
        float yRatio = x - minimum;
        float wantedRange = canvas.getWidth();
        float result = yRatio * wantedRange / rangeSize;

        return result < 0 ? centerX + result : result + PADDING;
    }

    public float fromCartesianY(float y, float minimum, float rangeSize) {
        float yRatio = y - minimum;
        float wantedRange = canvas.getHeight();
        float result = yRatio * wantedRange / rangeSize;

        return result < 0 ? centerY + result : canvas.getHeight() - result + PADDING;
    }

    public void drawLine(float startingX, float startingY, float endingX, float endingY, Color color) {
        Line line = new Line(startingX, startingY, endingX, endingY);
        line.setColor(color);
        line.draw();
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public static void paint(Representable representable, Color color) {
        representable.setColor(color);
    }
}
