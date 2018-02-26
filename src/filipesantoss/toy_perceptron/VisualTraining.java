package filipesantoss.toy_perceptron;

import filipesantoss.toy_perceptron.graphics.GraphicalData;
import filipesantoss.toy_perceptron.graphics.CartesianCanvas;
import filipesantoss.toy_perceptron.data.DataFactory;
import filipesantoss.toy_perceptron.data.Group;
import filipesantoss.toy_perceptron.perceptron.Perceptron;
import org.academiadecodigo.simplegraphics.graphics.Color;

import java.util.LinkedList;
import java.util.List;

public class VisualTraining {

    public static void main(String[] args) {
        try {
            VisualTraining example = new VisualTraining(50);
            example.init(400);
            example.start(30);

        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static final Color FIRST_GROUP_COLOR = Color.GREEN;
    private static final Color SECOND_GROUP_COLOR = Color.RED;
    private static final Color WRONG_ANSWER_COLOR = Color.BLACK;
    private static final Color GUESSING_COLOR = Color.ORANGE;

    private CartesianCanvas canvas;
    private List<GraphicalData> trainingData;
    private Perceptron perceptron;

    private VisualTraining(int size) {
        this.canvas = new CartesianCanvas(size);
    }

    private void init(int numberOfInputs) throws InterruptedException {
        perceptron = new Perceptron(3, 0.1f);
        perceptron.init();

        canvas.init();
        GraphicalData.drawGroupDelimiter(canvas);
        canvas.setText("Initializing...");

        trainingData = new LinkedList<>();

        for (int i = 0; i < numberOfInputs; i++) {
            trainingData.add(DataFactory.randomRepresentable(canvas));
            canvas.draw(trainingData.get(i));

            Thread.sleep(10);
        }
    }

    private void start(int numberOfTrainings) throws InterruptedException {
        guess();

        for (int i = 0; i < numberOfTrainings; i++) {
            train();
            guess();
        }

        canvas.setText("Simulation ended.");
    }

    private void guess() throws InterruptedException {
        canvas.setText("Guessing...");

        for (GraphicalData input : trainingData) {
            CartesianCanvas.paint(input, GUESSING_COLOR);
            Thread.sleep(10);

            Group prediction = perceptron.predict(input.getAsList());

            if (prediction != input.getGroup()) {
                CartesianCanvas.paint(input, WRONG_ANSWER_COLOR);
                continue;
            }

            Color color = prediction == Group.FIRST ? FIRST_GROUP_COLOR : SECOND_GROUP_COLOR;
            CartesianCanvas.paint(input, color);

            Thread.sleep(5);
        }
    }

    private void train() throws InterruptedException {
        canvas.setText("Training...");

        for (GraphicalData input : trainingData) {
            perceptron.learn(input.getAsList(), input.getGroup());

            Thread.sleep(10);
        }

    }
}
