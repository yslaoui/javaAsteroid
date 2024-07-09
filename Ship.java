package asteroid;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Ship extends Character {
    public Ship(double x, double y) {
        super(new Polygon(-5,-5,10,0,-5,5), x,y);
    }
}

