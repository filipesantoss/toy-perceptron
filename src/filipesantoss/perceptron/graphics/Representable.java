package filipesantoss.perceptron.graphics;

import org.academiadecodigo.simplegraphics.graphics.Color;

public interface Representable {

    float getX();

    float getY();

    void setColor(Color color);

    void drawAt(float x, float y);

    int getMinimumX();

    int getWidthRange();

    int getMinimumY();

    int getHeightRange();
}
