package javaAsteroid;

import javafx.scene.shape.Polygon;

import java.util.Random;

public class Asteroid extends Character {
    Random rnd;

    public Asteroid(double x, double y) {
        super(new PolygonFactory().createPolygon(), x,y);
        // Setting initial random rotation
        this.rnd = new Random();
        super.getCharacter().setRotate(this.rnd.nextDouble(360));
        // calling the accelerate() method a random number of times between 1 and 10.
        int numberOfAccelerations = 1 + rnd.nextInt(10);
        for (int i=0; i< numberOfAccelerations; i++) {
            super.accelerate();
        }
    }

    // Add a small rotation whenever an asteroid moves
    @Override
    public void move() {
        super.move();
        double moveRotation = 0.5 - this.rnd.nextDouble();
        super.getCharacter().setRotate(super.getCharacter().getRotate() + moveRotation);

        if (super.getCharacter().getTranslateX() < 0) {
            super.getCharacter().setTranslateX(AsteroidsApplication.WIDTH);
        }

        if (super.getCharacter().getTranslateX() > AsteroidsApplication.WIDTH) {
            super.getCharacter().setTranslateX(0);
        }

        if (super.getCharacter().getTranslateY() < 0) {
            super.getCharacter().setTranslateY(AsteroidsApplication.HEIGHT);
        }

        if (super.getCharacter().getTranslateY() > AsteroidsApplication.HEIGHT) {
            super.getCharacter().setTranslateY(0);
        }
    }
}
