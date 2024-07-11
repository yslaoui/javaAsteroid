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

import java.util.*;

public class AsteroidsApplication extends Application {

    @Override
    public void start(Stage window) throws Exception {
        Pane pane = new Pane();
        pane.setPrefSize(600,400);
        Ship ship = new Ship(200,200);
        Asteroid asteroid = new Asteroid(100,100);
        List<Asteroid> asteroids = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<5; i++) {
            asteroids.add(new Asteroid(random.nextInt(100), random.nextInt(100)));
        }

        Scene scene = new Scene(pane);
        pane.getChildren().add(ship.getCharacter());
        for (Asteroid ast: asteroids) {
            pane.getChildren().add(ast.getCharacter());
        }
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
                asteroids.forEach(ast->ast.move());
                asteroids.forEach(ast -> {
                    if (ast.collide(ship)) {
                        stop();
                    }
                });

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

