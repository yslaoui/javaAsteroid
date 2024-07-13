package javaAsteroid;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Ship extends Character {
    public Ship(double x, double y) {
        super(new Polygon(-5,-5,10,0,-5,5), x,y);
    }

    @Override
    public void move() {
        super.move();
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

