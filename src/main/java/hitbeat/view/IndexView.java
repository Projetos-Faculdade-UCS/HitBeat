package hitbeat.view;

import hitbeat.styles.Styles;
import hitbeat.view.footer.Footer;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IndexView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        // Sidebar
        VBox sidebar = new VBox(10);
        MFXButton button1 = new MFXButton("Sidebar Item 1");
        MFXButton button2 = new MFXButton("Sidebar Item 2");
        // button colors
        button1.setMinWidth(200);
        button2.setMinWidth(200);

        button1.setStyle(Styles.SIDEBAR_BUTTONS); 
        button2.setStyle(Styles.SIDEBAR_BUTTONS); 
        
        sidebar.getChildren().addAll(button1, button2);

        // Content area
        CenterOne content = new CenterOne();

        Footer footer = new Footer();

        root.setLeft(sidebar);
        root.setCenter(content);
        root.setBottom(footer);

        Scene scene = new Scene(root, 800, 700);
        scene.getStylesheets().add(
            getClass().getResource("/hitbeat/css/index.css").toExternalForm());
        MFXThemeManager.addOn(scene, Themes.DEFAULT);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Index Page");
        primaryStage.show();

        System.out.println(scene.getStylesheets());
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
