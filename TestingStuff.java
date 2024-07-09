package asteroid;


import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class TestingStuff extends Application {
    @Override
    public void start(Stage window) throws Exception {
        Pane pane = new Pane();
        pane.setPrefSize(600,400);
        Polygon parallelogram = new Polygon(0,0,100,0,100,50,0,50);
        parallelogram.setTranslateX(200);
        parallelogram.setTranslateY(200);
        pane.getChildren().add(parallelogram);
        Scene scene = new Scene(pane);

        // hashmap: true is key is pressed, false if key is released
        Map<KeyCode, Boolean> keyBoard = new HashMap<>();
        scene.setOnKeyPressed(event -> keyBoard.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keyBoard.put(event.getCode(), false));

        // animationTimer: increase angle by 5 if RIGHT key is pressed.
        // Decrease angle by 5 if LEFT key is pressed
        // use getOrDefault to return false if the pressed key is not in the map
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (keyBoard.getOrDefault(KeyCode.RIGHT, false)) {
                    parallelogram.setRotate(parallelogram.getRotate() + 5);
                }
                if (keyBoard.getOrDefault(KeyCode.LEFT, false)) {
                    parallelogram.setRotate(parallelogram.getRotate() - 5);
                }
            }
        }.start();

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(TestingStuff.class);
    }
}
