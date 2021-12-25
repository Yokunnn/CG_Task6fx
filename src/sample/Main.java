package sample;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int WIDTH = 1400;
    public static final int HEIGHT = 700;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        Circle[][] circles = BCTools.createCircleField();
        for (int i = 0; i < circles.length; i++) {
            for (int j = 0; j < circles[0].length; j++) {
                group.getChildren().add(circles[i][j]);
            }
        }

        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                SequentialTransition sq = new SequentialTransition();
                sq.getChildren().addAll(BCTools.breathe(circles));
                sq.setAutoReverse(true);
                sq.setCycleCount(Animation.INDEFINITE);
                sq.play();
            }
        });

        primaryStage.setTitle("CG_Task6fx_BreathingCircles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
