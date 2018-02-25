package filipesantoss.toy_perceptron.graphics;

import org.academiadecodigo.simplegraphics.graphics.Color;

public interface Representable {

    float getX();

    float getY();

    void setColor(Color color);

    void drawAt(float x, float y);

    int getMinimumX();

    int getMaximumX();

    int getMinimumY();

    int getMaximumY();
}
