package asteroid;

import javafx.scene.shape.Polygon;

public class Asteroid extends Character {
    public Asteroid(double x, double y) {
        super(new PolygonFactory().createPolygon(), x,y);
    }
}
