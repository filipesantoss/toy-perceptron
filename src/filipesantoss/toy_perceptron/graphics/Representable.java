package filipesantoss.toy_perceptron.graphics;

import org.academiadecodigo.simplegraphics.graphics.Color;

public interface Representable {

    /**
     * Returns the value to be used when calculating the X axis value for representation.
     *
     * @return - the value to be used.
     */
    float getX();

    /**
     * Returns the value to be used when calculating the Y axis value for representation.
     *
     * @return - the value to be used.
     */
    float getY();

    /**
     * Defines the representation's color.
     *
     * @param color
     */
    void setColor(Color color);

    /**
     * Draws the representation at the specified X and Y axis values.
     *
     * @param x - the X axis value.
     * @param y - the Y axis value.
     */
    void drawAt(float x, float y);

    /**
     * Returns the value used to calculate the minimum possible X axis value.
     *
     * @return - the value to be used.
     */
    int getMinimumX();

    /**
     * Returns the value used to calculate the maximum possible X axis value.
     *
     * @return - the value to be used.
     */
    int getMaximumX();

    /**
     * Returns the value used to calculate the minimum possible Y axis value.
     *
     * @return - the value to be used.
     */
    int getMinimumY();

    /**
     * Returns the value used to calculate the maximum possible Y axis value.
     *
     * @return - the value to be used.
     */
    int getMaximumY();
}
