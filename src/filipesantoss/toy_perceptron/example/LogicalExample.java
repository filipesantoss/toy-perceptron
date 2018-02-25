package filipesantoss.toy_perceptron.example;

import filipesantoss.toy_perceptron.data.Data;
import filipesantoss.toy_perceptron.perceptron.Perceptron;
import filipesantoss.toy_perceptron.data.DataFactory;
import filipesantoss.toy_perceptron.data.Group;

import java.util.LinkedList;
import java.util.List;

public class LogicalExample {

    public static void main(String[] args) {
        LogicalExample example = new LogicalExample();
        example.init(100000);
        example.start(3);
    }

    private List<Data> trainingData;
    private Perceptron perceptron;

    private void init(int numberOfInputs) {
        perceptron = new Perceptron(3, 0.001f);
        perceptron.init();

        trainingData = new LinkedList<>();

        for (int i = 0; i < numberOfInputs; i++) {
            trainingData.add(DataFactory.random());
        }
    }

    private void start(int numberOfTrainings) {
        predict();

        for (int i = 0; i < numberOfTrainings; i++) {
            teach();
            predict();
        }
    }

    private void predict() {
        System.out.println("Predicting...");
        int rightPredictions = 0;

        for (Data input : trainingData) {
            Group prediction = perceptron.predict(input.getAsList());

            if (prediction.equals(input.getGroup())) {
                rightPredictions++;
            }
        }

        System.out.println(rightPredictions + " out of " + trainingData.size() + " predictions were correct.");
    }

    private void teach() {
        System.out.println("Teaching...");

        for (Data input : trainingData) {
            perceptron.learn(input.getAsList(), input.getGroup());
        }
    }
}
