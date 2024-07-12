package asteroid;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Character {
    private Polygon character;
    private Point2D movement;
    private boolean alive;

    public Character(Polygon character, double x, double y) {
        this.character = character;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.movement = new Point2D(0,0);
        alive = true;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean status) {
        this.alive = status;
    }

    public Polygon getCharacter() {
        return character;
    }

    public Point2D getMovement() {
        return movement;
    }

    public void turnRight() {
        this.character.setRotate(this.character.getRotate() + 5);
    }

    public void turnLeft() {
        this.character.setRotate(this.character.getRotate() - 5);
    }

    public void accelerate() {
        double cosDirection = Math.cos(Math.toRadians(this.character.getRotate()));
        double sinDirection = Math.sin(Math.toRadians(this.character.getRotate()));
        this.movement = this.movement.add(cosDirection * 0.05, sinDirection* 0.05);
    }

    public boolean collide(Character other) {
        Shape intersection = Shape.intersect(this.character, other.getCharacter());
        if (intersection.getBoundsInLocal().getWidth() == -1) {
            return false;
        }
        return true;
    }

    public void move() {

        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());


    }
}
