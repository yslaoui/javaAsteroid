package asteroid;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class AsteroidsApplication extends Application {

    @Override
    public void start(Stage window) throws Exception {
        Pane pane = new Pane();
        pane.setPrefSize(600,400);
        Ship ship = new Ship(200,200);
        Asteroid asteroid = new Asteroid(100,100);
        Scene scene = new Scene(pane);
        pane.getChildren().addAll(ship.getCharacter(), asteroid.getCharacter());
        // map of keyboard key statuses
        Map<KeyCode, Boolean> keyBoard = new HashMap<>();
        scene.setOnKeyPressed(event->keyBoard.put(event.getCode(), true));
        scene.setOnKeyReleased(event->keyBoard.put(event.getCode(), false));

        // animation timer that rotates the ship based on the keyboard status hashmap
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (keyBoard.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.turnRight();
                }
                if (keyBoard.getOrDefault(KeyCode.LEFT, false)) {
                    ship.turnLeft();
                }
                if (keyBoard.getOrDefault(KeyCode.UP, false)) {
                    ship.accelerate();
                }
                ship.move();
                asteroid.move();

            }
        }.start();
        asteroid.turnRight();
        asteroid.turnRight();
        asteroid.accelerate();
        asteroid.accelerate();
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(AsteroidsApplication.class);
    }

}



