package javaAsteroid;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AsteroidsApplication extends Application {

    public static int WIDTH = 600;
    public static int HEIGHT = 400;
    public static AtomicInteger score;

    @Override
    public void start(Stage window) throws Exception {
        score = new AtomicInteger(0);
        Pane pane = new Pane();
        pane.setPrefSize(WIDTH,HEIGHT);
        Ship ship = new Ship(WIDTH/3,HEIGHT/2);
        Text scoreText = new Text(10,10,"Points: " + score.intValue());
        List<Asteroid> asteroids = new ArrayList<>();
        List<Projectile> projectiles = new ArrayList<>();
        Random random = new Random();
        // Initial set of asteroids
        for (int i=0; i<5; i++) {
            asteroids.add(new Asteroid(random.nextInt(WIDTH/3), random.nextInt(HEIGHT)));
        }


        Scene scene = new Scene(pane);
        pane.getChildren().add(ship.getCharacter());
        pane.getChildren().add(scoreText);
        for (Asteroid ast: asteroids) {
            pane.getChildren().add(ast.getCharacter());
        }
        // map of keyboard key statuses
        Map<KeyCode, Boolean> keyBoard = new HashMap<>();
        scene.setOnKeyPressed(event->keyBoard.put(event.getCode(), true));
        scene.setOnKeyReleased(event->keyBoard.put(event.getCode(), false));


        // animation timer that rotates the ship based on the keyboard status hashmap
        new AnimationTimer() {
            boolean spacePressed = false;
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
                if (keyBoard.getOrDefault(KeyCode.SPACE, false) && !spacePressed) {
                    Projectile projectile = new Projectile(ship.getCharacter().getTranslateX(),
                                        ship.getCharacter().getTranslateY());
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                    projectiles.add(projectile);
                    for (int i=0; i<30; i++) {
                        projectile.accelerate();
                    }
                    pane.getChildren().add(projectile.getCharacter());
                    spacePressed = true;
                }
                if (!keyBoard.getOrDefault(KeyCode.SPACE, false)) {
                    spacePressed = false;
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

                // Removing projectiles and asteroids when they collide
                    // Setting alive attribute flag to false when for asteroids and projectiles when they collide
                projectiles.forEach(proj->{
                    asteroids.forEach(ast->{
                        if (ast.collide(proj)) {
                            proj.setAlive(false);
                            ast.setAlive(false);
                            scoreText.setText(String.valueOf("Points: " + score.addAndGet(1000)));
                        }
                    });
                });
                    // remove projectiles that are not alive from the pane
                projectiles.forEach(proj->{
                    if (!proj.getAlive()) {
                        pane.getChildren().remove(proj.getCharacter());
                    }
                });
                    // remove projectiles that are not alive from the projectiles list
                projectiles.removeAll(projectiles
                                    .stream()
                                    .filter(proj->!proj.getAlive())
                                    .collect(Collectors.toCollection(ArrayList::new)));
                    // remove asteroids that are not alive from the pane
                asteroids.forEach(ast->{
                    if (!ast.getAlive()) {
                        pane.getChildren().remove(ast.getCharacter());
                    }
                });
                    // remove asteroids that are not alive from the asteroids list
                asteroids.removeAll(asteroids
                                    .stream()
                                    .filter(proj->!proj.getAlive())
                                    .collect(Collectors.toCollection(ArrayList::new)));// Adding new asteroids constinuously
                // Adding asteroids continuously
                Asteroid newAsteroid = new Asteroid(random.nextInt(WIDTH/3), random.nextInt(HEIGHT));
                if (random.nextDouble() < 0.005 && !(ship.collide(newAsteroid))) {
                    asteroids.add(newAsteroid);
                    pane.getChildren().add(newAsteroid.getCharacter());
                }


            }
        }.start();
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(AsteroidsApplication.class);
    }
}





