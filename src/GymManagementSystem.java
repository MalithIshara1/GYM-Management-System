import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GymManagementSystem extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(parent);

        parent.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });

        parent.setOnMouseDragged((MouseEvent event) ->{
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

            primaryStage.setOpacity(0.8);
        });

        parent.setOnMouseReleased((MouseEvent event) ->{
            primaryStage.setOpacity(1);
        });

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("");
        primaryStage.show();


    }
}
