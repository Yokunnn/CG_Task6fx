package sample;

import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static sample.Main.HEIGHT;
import static sample.Main.WIDTH;

public class BCTools {

    public static final int SIZE = 100;

    public static Circle getCenter(Circle[][] circles) {
        return circles[circles.length / 2][circles[0].length / 2];
    }

    public static List<Transition> breathe(Circle[][] circles) {
        List<Transition> transitions = new ArrayList<>();
        transitions.add(new PauseTransition(Duration.millis(500)));
        ParallelTransition pt = new ParallelTransition();
        Circle central = getCenter(circles);
        double x = central.getTranslateX();
        double y = central.getTranslateY();
        for (int i = 0; i < circles.length; i++) {
            for (int j = 0; j < circles[0].length; j++) {
                double xMove = (circles[i][j].getTranslateX() - x) / 2;
                double yMove = (circles[i][j].getTranslateY() - y) / 2;
                TranslateTransition t = new TranslateTransition();
                t.setDuration(Duration.millis(1000));
                t.setNode(circles[i][j]);
                t.setByX(-xMove);
                t.setByY(-yMove);
                pt.getChildren().add(t);
            }
        }
        transitions.add(pt);
        return transitions;
    }

    public static Circle[][] createCircleField() {
        Circle[][] circles = new Circle[(int) (HEIGHT / SIZE * Math.sqrt(3) + 1) * 2][(WIDTH / SIZE + 1) * 2];
        double x, y;
        y = -circles.length * SIZE / 4;
        y += -2 * SIZE + SIZE / 2;
        for (int i = 0; i < circles.length; i++) {
            x = -circles[0].length * SIZE / 4;
            if (i % 2 == 1) {
                x += -SIZE / 2;
            }
            for (int j = 0; j < circles[0].length; j++) {
                Circle circle = new Circle(SIZE / 2);
                circle.setFill(Color.TRANSPARENT);
                circle.setStroke(Color.WHITE);
                circle.setStrokeWidth(1);
                circle.translateXProperty().set(x);
                circle.translateYProperty().set(y);
                circles[i][j] = circle;

                x += SIZE;
            }
            y += SIZE * Math.sqrt(3) / 2;
        }
        return circles;
    }
}
