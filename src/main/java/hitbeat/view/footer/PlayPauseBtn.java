package hitbeat.view.footer;

import hitbeat.styles.Styles;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

public class PlayPauseBtn extends MFXButton{
    
    private boolean paused = true;

    public PlayPauseBtn(MediaPlayer mediaPlayer) {
        super("");

        Image imagePlay = new Image(getClass().getResourceAsStream("/hitbeat/static/play-circle.png"));
        ImageView PlayView = new ImageView(imagePlay);
        
        Image imagePause = new Image(getClass().getResourceAsStream("/hitbeat/static/pause-circle.png"));
        ImageView PauseView = new ImageView(imagePause);

        this.setGraphic(PlayView);
        this.setOnAction(event -> {
            if (paused == true) {
                paused = false;
                this.setGraphic(PauseView);
                mediaPlayer.play();
            } else {
                paused = true;
                this.setGraphic(PlayView);
                mediaPlayer.pause();
            }
        });
        this.setStyle(Styles.PLAYER_BUTTONS);
    }
}
