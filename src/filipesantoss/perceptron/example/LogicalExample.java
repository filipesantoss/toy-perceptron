package filipesantoss.perceptron.example;

import filipesantoss.perceptron.Perceptron;
import filipesantoss.perceptron.input.Input;
import filipesantoss.perceptron.input.InputFactory;
import filipesantoss.perceptron.util.Group;

import java.util.LinkedList;
import java.util.List;

public class LogicalExample {

    public static void main(String[] args) {
        LogicalExample example = new LogicalExample();
        example.init(100000);
        example.start(3);
    }

    private List<Input> trainingData;
    private Perceptron perceptron;

    private void init(int inputNumber) {
        perceptron = new Perceptron();
        perceptron.init();

        trainingData = new LinkedList<>();

        for (int i = 0; i < inputNumber; i++) {
            trainingData.add(InputFactory.random());
        }
    }

    private void start(int teachingTimes) {
        predict();

        for (int i = 0; i < teachingTimes; i++) {
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
            perceptron.teach(input.valuesAsList(), input.getGroup());
        }
    }


}
