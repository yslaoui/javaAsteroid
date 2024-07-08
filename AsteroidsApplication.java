package asteroid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class AsteroidsApplication extends Application {

    @Override
    public void start(Stage window) throws Exception {
        Pane pane = new Pane();
        pane.setPrefSize(600,400);
        Polygon ship = new Polygon(-5,-5,10,0,-5,5);
        pane.getChildren().add(ship);
        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(AsteroidsApplication.class);
    }

}
