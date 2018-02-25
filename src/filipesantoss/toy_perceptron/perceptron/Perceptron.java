package filipesantoss.toy_perceptron.perceptron;

import filipesantoss.toy_perceptron.data.Group;
import filipesantoss.toy_perceptron.util.Random;

import java.util.LinkedList;
import java.util.List;

public class Perceptron {

    private static final float LEARNING_RATE = 0.01f;

    private List<Float> weights;
    private int numberOfInputs;

    public Perceptron(int numberOfInputs) {
        weights = new LinkedList<>();
        this.numberOfInputs = numberOfInputs;
    }

    /**
     * Initializes random weights.
     */
    public void init() {
        int minimumWeight = -1;
        int maximumWeight = 1;

        for (int i = 0; i < numberOfInputs; i++) {
            weights.add(Random.createFloat(minimumWeight, maximumWeight));
        }
    }

    /**
     * The guessing function.
     * Predicts the group that classifies the data input based on their weighted sum.
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
     * The training function.
     * Adjusts the weights of the input values every time a prediction is wrong,
     * based on given inputs for which the correct prediction is known.
     *
     * @param inputs - the input values.
     * @param answer the known correct prediction.
     */
    public void learn(List<Float> inputs, Group answer) {
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

    /**
     * The activation function.
     * Returns the prediction for which group categorizes the inputs, based on the result of the weighted sum.
     *
     * @param weightedSum - the weighted sum.
     * @return the group that may categorize the inputs.
     */
    private Group activate(float weightedSum) {
        return weightedSum >= 0 ? Group.FIRST : Group.SECOND;
    }
}
