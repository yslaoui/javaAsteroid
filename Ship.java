package asteroid;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Ship {
    private Polygon character;
    private Point2D movement;

    public  Ship(double x, double y) {
        this.character = new Polygon(-5,-5,10,0,-5,5);
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.movement = new Point2D(0,0);
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
    public void move() {
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());

    }
}


//Polygon ship = new Polygon(-5,-5,10,0,-5,5);
//        ship.setTranslateX(200);
//        ship.setTranslateY(200);
//Point2D movement = new Point2D(0,1);
//ship.setRotate(ship.getRotate() + 5);
//ship.setRotate(ship.getRotate() - 5);
//ship.setTranslateX(ship.getTranslateX() + movement.getX());

//This way you see that you need
//        Attributes
//Polygon
//Point2D initializd at (0,0) which means
//Methods
//turnRight()
//turnLeft()
//accelerate() which will change the direction of the movement. Bu doing that it will als change the magnitude of that acceleration since adding a cos to 0 will also change the magnitude
