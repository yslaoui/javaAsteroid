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
//        Polygon ship = new Polygon(-5,-5,10,0,-5,5);
//        ship.setTranslateX(200);
//        ship.setTranslateY(200);
//

        Scene scene = new Scene(pane);
        // map of keyboard key statuses
        Map<KeyCode, Boolean> keyBoard = new HashMap<>();
        scene.setOnKeyPressed(event->keyBoard.put(event.getCode(), true));
        scene.setOnKeyReleased(event->keyBoard.put(event.getCode(), false));
        pane.getChildren().add(ship.getCharacter());
        Point2D movement = new Point2D(0,1);
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

            }
        }.start();

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(AsteroidsApplication.class);
    }

}



