package hitbeat.view.footer;

import hitbeat.styles.Styles;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.SVGPath;
import javafx.scene.paint.Color;

public class PlayPauseBtn extends MFXButton{
    
    private boolean paused = true;

    public PlayPauseBtn(MediaPlayer mediaPlayer) {
        super("");

        SVGPath circulo = new SVGPath();
        circulo.setContent("M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z");
        SVGPath play = new SVGPath();
        play.setContent("M6.271 5.055a.5.5 0 0 1 .52.038l3.5 2.5a.5.5 0 0 1 0 .814l-3.5 2.5A.5.5 0 0 1 6 10.5v-5a.5.5 0 0 1 .271-.445z");
        
        SVGPath circulo2 = new SVGPath();
        circulo2.setContent("M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z");
        SVGPath pause = new SVGPath();
        pause.setContent("M5 6.25a1.25 1.25 0 1 1 2.5 0v3.5a1.25 1.25 0 1 1-2.5 0v-3.5zm3.5 0a1.25 1.25 0 1 1 2.5 0v3.5a1.25 1.25 0 1 1-2.5 0v-3.5z");

        play.setFill(Color.WHITE);
        circulo.setFill(Color.WHITE);
        pause.setFill(Color.WHITE);
        circulo2.setFill(Color.WHITE);

        StackPane playImage = new StackPane();
        playImage.getChildren().addAll(circulo, play);
        
        StackPane pauseImage = new StackPane();
        pauseImage.getChildren().addAll(circulo2, pause);

        this.setGraphic(playImage);
        this.setStyle(Styles.PLAYER_BUTTONS);
        this.setOnAction(event -> {
            if (paused == true) {
                paused = false;
                this.setGraphic(pauseImage);
                mediaPlayer.play();
            } else {
                paused = true;
                this.setGraphic(playImage);
                mediaPlayer.pause();
            }
        });
        this.setStyle(Styles.PLAYER_BUTTONS);
    }
}
