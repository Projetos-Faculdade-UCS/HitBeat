package hitbeat;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Index extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.DEFAULT);

        scene.getStylesheets().add(
            getClass().getResource("css/index.css").toExternalForm());

        primaryStage.setTitle("HitBeat");
        primaryStage.setScene(scene);
        primaryStage.show();


        MFXButton button = new MFXButton("Play");
        root.getChildren().add(button);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
