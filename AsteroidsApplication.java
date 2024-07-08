package asteroid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class AsteroidsApplication extends Application {

    @Override
    public void start(Stage window) throws Exception {
        Pane pane = new Pane();
        pane.getChildren().add(new Circle(30,50,10));
        Scene scene = new Scene(pane, 600,400);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(AsteroidsApplication.class);
    }

}
