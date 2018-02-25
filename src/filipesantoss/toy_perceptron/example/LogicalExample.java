package filipesantoss.toyperceptron.example;

import filipesantoss.toyperceptron.perceptron.Perceptron;
import filipesantoss.toyperceptron.input.Input;
import filipesantoss.toyperceptron.input.InputFactory;
import filipesantoss.toyperceptron.input.Group;

import java.util.LinkedList;
import java.util.List;

public class LogicalExample extends AbstractExample {

    public static void main(String[] args) {
        LogicalExample example = new LogicalExample();
        example.init(100000);
        example.start(3);
    }

    private List<Input> trainingData;
    private Perceptron perceptron;

    @Override
    public void init(int numberOfInputs) {
        perceptron = new Perceptron(3);
        perceptron.init();

        trainingData = new LinkedList<>();

        for (int i = 0; i < numberOfInputs; i++) {
            trainingData.add(InputFactory.random());
        }
    }

    @Override
    public void start(int numberOfTrainings) {
        predict();

        for (int i = 0; i < numberOfTrainings; i++) {
            teach();
            predict();
        }
    }

    private void predict() {
        System.out.println("Predicting...");
        int rightPredictions = 0;

        for (Input input: trainingData) {
            Group prediction = perceptron.predict(input.valuesAsList());

            if (prediction.equals(input.getGroup())) {
                rightPredictions++;
            }
        }

        System.out.println(rightPredictions + " out of " + trainingData.size() + " predictions were correct.");
    }

    private void teach() {
        System.out.println("Teaching...");

        for (Input input : trainingData) {
            perceptron.learn(input.valuesAsList(), input.getGroup());
        }
    }
}
