package hitbeat.view;

import hitbeat.util.HibernateUtil;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp extends Application {
    private IndexView index;
    private LoadingPage loadingPage;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadingPage = new LoadingPage(true);
        setupScene(primaryStage);

        // Inicialização assíncrona
        new Thread(() -> {
            HibernateUtil.init();

            // Carregamento concluído
            Platform.runLater(() -> {
                loadingPage.dispose();
                index = new IndexView();
                scene.setRoot(index);
            });
        }).start();
    }

    private void setupScene(Stage primaryStage) {
        scene = new Scene(loadingPage, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/hitbeat/css/index.css").toExternalForm());

        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);

        Image applicationIcon = new Image(getClass().getResourceAsStream("/hitbeat/images/hitbeat-icon.png"));
        primaryStage.getIcons().add(applicationIcon);

        scene.setFill(Color.TRANSPARENT);

        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        primaryStage.setScene(scene);
        primaryStage.setTitle("HitBeat");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
