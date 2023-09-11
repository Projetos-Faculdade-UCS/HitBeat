package hitbeat.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Index extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 300, 250);

        // scene.getStylesheets().add(
        //     getClass().getResource("index.css").toExternalForm());

        primaryStage.setTitle("HitBeat");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
