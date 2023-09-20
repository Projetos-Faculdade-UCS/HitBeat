package hitbeat.view.footer;

import hitbeat.styles.Styles;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class PlayPauseBtn extends MFXButton{
    
    private boolean paused = true;

    public PlayPauseBtn(MediaPlayer mediaPlayer) {
        super("");

        SVGWidget svgPlay = new SVGWidget("/hitbeat/svg/play.svg", 25, Color.WHITE);
        SVGWidget svgPause = new SVGWidget("/hitbeat/svg/pause.svg", 25, Color.WHITE);

        this.setGraphic(svgPlay.build());
        this.setStyle(Styles.PLAYER_BUTTONS);
        this.setOnAction(event -> {
            if (paused == true) {
                paused = false;
                this.setGraphic(svgPause.build());
                mediaPlayer.play();
            } else {
                paused = true;
                this.setGraphic(svgPlay.build());
                mediaPlayer.pause();
            }
        });
        this.setStyle(Styles.PLAYER_BUTTONS);
    }
}