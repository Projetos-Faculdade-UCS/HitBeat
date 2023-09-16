package hitbeat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.Group;

public class Index extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 300, 250);

        scene.getStylesheets().add(
            getClass().getResource("css/index.css").toExternalForm());

        primaryStage.setTitle("HitBeat");
        primaryStage.setScene(scene);
        primaryStage.show();

        String source = getClass().getResource("media/HitBeat.mp4").toString();
        Media media = new Media(source);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        MediaView mediaView = new MediaView(mediaPlayer);
        ((Group) scene.getRoot()).getChildren().add(mediaView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
