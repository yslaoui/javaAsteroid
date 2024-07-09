package asteroid;

import javafx.scene.shape.Polygon;

public class Asteroid extends Character {
    public Asteroid(double x, double y) {
        super(new Polygon(20,20,40,20,40,40, 20,40), x,y);
    }
}
