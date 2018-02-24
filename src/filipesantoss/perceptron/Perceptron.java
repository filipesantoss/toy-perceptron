package filipesantoss.perceptron;

import filipesantoss.perceptron.util.Group;
import filipesantoss.perceptron.util.Random;

import java.util.LinkedList;
import java.util.List;

public class Perceptron {

    private static final int MINIMUM_WEIGHT = -1;
    private static final int MAXIMUM_WEIGHT = 1;
    private static final int TOTAL_WEIGHTS = 2;
    private static final float LEARNING_RATE = 0.02f;

    private List<Float> weights;

    public Perceptron() {
        weights = new LinkedList<>();
    }

    /**
     * Initializes random weights.
     */
    public void init() {
        for (int i = 0; i < TOTAL_WEIGHTS; i++) {
            weights.add(Random.createFloat(MINIMUM_WEIGHT, MAXIMUM_WEIGHT));
        }
    }

    /**
     * Predicts the group that classifies the input values based on their weighted sum.
     * If the weighted sum is a positive value, the inputs are categorized as belonging to group 1.
     * If the weighted sum is a negative value, the inputs are categorized as belonging to group 2.
     *
     * @param inputs - the input values.
     * @return the classifying group.
     */
    public Group predict(List<Float> inputs) {
        float weightedSum = 0;

        for (int i = 0; i < inputs.size(); i++) {
            weightedSum += inputs.get(i) * weights.get(i);
        }

        return activate(weightedSum);
    }

    /**
     * Adjusts the weights of the input values every time a prediction is wrong,
     * based on given inputs for which the correct prediction is known.
     *
     * @param inputs - the input values.
     * @param answer the known correct prediction.
     */
    public void teach(List<Float> inputs, Group answer) {
        Group prediction = predict(inputs);
        int error = answer.asInt() - prediction.asInt();

        if (error == 0) {
            return;
        }

        for (int i = 0; i < weights.size(); i++) {
            float weight = weights.remove(i);

            weight += error * inputs.get(i) * LEARNING_RATE;
            weights.add(i, weight);
        }
    }

    private Group activate(float weightedSum) {
        return weightedSum >= 0 ? Group.FIRST : Group.SECOND;
    }
}