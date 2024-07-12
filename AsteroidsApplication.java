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

    public static int WIDTH = 600;
    public static int HEIGHT = 400;

    @Override
    public void start(Stage window) throws Exception {
        Pane pane = new Pane();
        pane.setPrefSize(WIDTH,HEIGHT);
        Ship ship = new Ship(WIDTH/3,HEIGHT/2);
        List<Asteroid> asteroids = new ArrayList<>();
        List<Projectile> projectiles = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<5; i++) {
            asteroids.add(new Asteroid(random.nextInt(WIDTH/3), random.nextInt(HEIGHT)));
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
                if (keyBoard.getOrDefault(KeyCode.SPACE, false)) {
                    Projectile projectile = new Projectile(ship.getCharacter().getTranslateX(),
                                        ship.getCharacter().getTranslateY());
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                    projectiles.add(projectile);
                    for (int i=0; i<30; i++) {
                        projectile.accelerate();
                    }

                    pane.getChildren().add(projectile.getCharacter());
                }

                // Movement
                ship.move();
                asteroids.forEach(ast->ast.move());
                asteroids.forEach(ast -> {
                    if (ast.collide(ship)) {
                        stop();
                    }
                });
                projectiles.forEach(proj->{
                    proj.move();
                });

            }
        }.start();
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(AsteroidsApplication.class);
    }
}





